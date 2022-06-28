package com.mei.bitmap.imageloader;

import com.mei.bitmap.R;
import com.mei.bitmap.imageloader.disk.DiskLruCache;
import com.mei.bitmap.utils.MyUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ubt on 2018/3/1.
 *
 * @description:图片加载
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    public static final int MESSAGE_POST_RESULT = 1;

    //CPU核心数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //线程池核心线程数为当前设备CPU核心数加1
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //线程池最大容量为CPU核心数的2倍加1
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    //线程闲置超时时长
    private static final long KEEP_ALIVE = 10L;

    private static final int TAG_KEY_URI = R.id.imageloader_uri;

    //磁盘缓存的大小
    private final long DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private final int DISK_CACHE_INDEX = 0;
    //IO读取时设置的内存缓存大小,满了的时候就往磁盘写,即批量写入
    private final int IO_BUFFER_SIZE = 8 * 1024;
    //是否创建了磁盘缓存
    private boolean mIsDiskLruCacheCreated;


    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private ImageResize mImageResize = new ImageResize();


    private static final ThreadFactory sThreadFactory = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + mCount.getAndIncrement());
        }
    };

    //线程池
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(), sThreadFactory);

    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            imageView.setImageBitmap(result.bitmap);
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (TextUtils.equals(uri, result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                Log.w(TAG, "set image bitmap,but url has change ,ignored!");
            }
        }
    };

    private ImageLoader(Context context) {
        mContext = context.getApplicationContext();

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;//缓存为应用内存的1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一张图片占用内存的大小，单位要跟缓存大小（cacheSize）的一样
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }

        //判断磁盘空间是否足够，空间足够的情况下才创建磁盘缓存
        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }

    /**
     * 加入内存缓存
     *
     * @param key
     * @param bitmap
     */
    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从内存缓存中，读取图片
     *
     * @param url
     * @return
     */
    public Bitmap loadBitmapFromMemoryCache(String url) {
        final String key = hashKeyFromUrl(url);
        return getBitmapFromMemCache(key);
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public void bindBitmap(String uri, ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    /**
     * 异步加载图片
     *
     * @param uri
     * @param imageView
     * @param reqWidth
     * @param reqHeight
     */
    public void bindBitmap(final String uri, final ImageView imageView, final int reqWidth, final int reqHeight) {
        imageView.setTag(TAG_KEY_URI, uri);
        Bitmap bitmap = loadBitmapFromMemoryCache(uri);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(uri, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, uri, bitmap);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    /**
     * load bitmap from memory cache or disk cache or network
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        //1、首先从内存缓存中加载图片
        Bitmap bitmap = loadBitmapFromMemoryCache(url);
        if (bitmap != null) {
            Log.d(TAG, "loadBitmapFromMemoryCache,url:" + url);
            return bitmap;
        }
        try {
            //2、从磁盘缓存中加载图片
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
            if (bitmap != null) {
                Log.d(TAG, "loadBitmapFromDiskCache,url:" + url);
                return bitmap;
            }
            //3、磁盘缓存加载失败时，从网络中下载图片并放入到磁盘缓存中,然后从磁盘缓存中加载出来
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
            Log.d(TAG, "loadBitmapFromHttp,url:" + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4、如果磁盘缓存创建失败时，直接从网络下载图片（此时加载的图片就不在加入到磁盘缓存中，因为没有创建磁盘缓存）
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            Log.w(TAG, "encounter error,DiskLruCache is not created");
            bitmap = downloadBitmapFromUrl(url);
        }

        return bitmap;
    }

    /**
     * 从网络加载图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread.");
        }
        if (mDiskLruCache == null) return null;

        String key = hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            /**
             * 在DiskLruCache调用open方法实例化的时候，设置了一个节点只能有一个数据，因此
             * DISK_CACHE_INDEX常量直接设为0即可
             */
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            //加载网络图片，放入磁盘缓存
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                //图片下载过程发生了异常，可以通过abort方法来回退整个操作。
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    /**
     * 从磁盘缓存中加载图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "load bitmap from UI Thread,it's not recommended!");
        }
        if (mDiskLruCache == null) return null;

        Bitmap bitmap = null;
        String key = hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if (snapshot != null) {
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = mImageResize.decodeSampledBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
            if (bitmap != null) {//加入内存缓存
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;
    }

    /**
     * 根据图片url，下载图片,并放入指定的磁盘缓存文件中
     *
     * @param urlStr
     * @param outputStream
     * @return
     */
    private boolean downloadUrlToStream(String urlStr, OutputStream outputStream) {

        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);//写入磁盘缓存
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "downloadBitmap failed." + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            MyUtils.close(out);
            MyUtils.close(in);
        }
        return false;
    }

    /**
     * 根据url，从网络中，下载图片
     *
     * @param urlStr
     * @return
     */
    private Bitmap downloadBitmapFromUrl(String urlStr) {

        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            //根据输入流，把图片文件转化成bitmap
            bitmap = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error in downloadBitmpa:" + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            MyUtils.close(in);
        }
        return bitmap;
    }


    /**
     * 把图片的url通过md5加密后的字符串当做key值，
     * 原因：url中可能有特殊字符，直接当key有可能会不准确
     *
     * @param url
     * @return
     */
    private String hashKeyFromUrl(String url) {
        String cacheKey = MD5Utils.encodeMd5(url);
        return cacheKey;
    }

    /**
     * 获取缓存路径
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取设备磁盘可用的存储空间大小
     *
     * @param path
     * @return
     */
    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs statFs = new StatFs(path.getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    private class LoaderResult {
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }
}
