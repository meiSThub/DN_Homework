package com.mei.test.optimize.bitmap;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

import com.mei.test.BuildConfig;
import com.mei.test.optimize.disk.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mei on 2018/1/25.
 * Description:Bitmap缓存工具类
 */

public class ImageCache {

    private Context mContext;
    private static ImageCache instance;
    private LruCache<String, Bitmap> memoryCache;
    private Set<WeakReference<Bitmap>> reusablePool;
    private ReferenceQueue referenceQueue;
    private boolean shutDown;
    private Thread clearReferenceQueue;
    private DiskLruCache diskLruCache;
    BitmapFactory.Options options = new BitmapFactory.Options();

    public static ImageCache getInstance() {
        if (null == instance) {
            synchronized (ImageCache.class) {
                if (null == instance) {
                    instance = new ImageCache();
                }
            }
        }
        return instance;
    }

    public void init(Context context, String dir) {
        this.mContext = context.getApplicationContext();
        //复用池
        reusablePool = Collections.synchronizedSet(new HashSet<WeakReference<Bitmap>>());
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获得程序最大内存 M
        int memoryClass = am.getMemoryClass();
        //lrucache 能够缓存的内存最大数
//        memoryClass / 8 * 1024 * 1024,可以先获取一张图片的内存大小，在来计算缓存大小,如缓存十张
        //设置缓存的大小 如：19404*10
        memoryCache = new LruCache<String, Bitmap>(memoryClass / 8 * 1024 * 1024) {

            /**
             *
             * @param key
             * @param value
             * @return value占用的内存
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    return value.getAllocationByteCount();
                }
                return value.getByteCount();
            }

            /**
             * 当bitmap从lru中移除时 回调
             * @param evicted
             * @param key
             * @param oldValue
             * @param newValue
             */
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                if (oldValue.isMutable()) {
                    //3.0以下 Bitmap 内存在 native中
                    //3.0 Java
                    //8.0 Bitmap native
                    Log.i("Adapter", "加入复用池");
                    reusablePool.add(new WeakReference<Bitmap>(oldValue, getReferenceQueue()));
                } else {
                    oldValue.recycle();//在native层管理的内存，需要手动释放
                }
            }
        };

        //valueCout 表示一个key对应 valueCout个 文件
        try {
            diskLruCache = DiskLruCache.open(new File(dir),
                    BuildConfig.VERSION_CODE, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ReferenceQueue<Bitmap> getReferenceQueue() {
        if (null == referenceQueue) {
            //引用队列 当弱引用需要被回收时  会放入到队列当中
            referenceQueue = new ReferenceQueue<>();
            clearReferenceQueue = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!shutDown) {
                            Reference<Bitmap> reference = referenceQueue.remove();
                            Bitmap bitmap = reference.get();
                            if (null != bitmap && !bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return referenceQueue;
    }

    public void putBitmap2Memory(String key, Bitmap bitmap) {
        memoryCache.put(key, bitmap);
    }

    public Bitmap getBitmapFromMemory(String key) {
        return memoryCache.get(key);
    }

    /**
     * 加入磁盘缓存
     *
     * @param key
     * @param bitmap
     */
    public void putBitMap2Disk(String key, Bitmap bitmap) {
        DiskLruCache.Snapshot snapshot = null;
        OutputStream os = null;
        try {
            snapshot = diskLruCache.get(key);
            // 如果缓存有对应key的文件 那么不管 （也可以替换）
            if (null == snapshot) {
                DiskLruCache.Editor edit = diskLruCache.edit(key);
                if (null != edit) {
                    os = edit.newOutputStream(0);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
                    edit.commit();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != snapshot) {
                snapshot.close();
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 从磁盘缓存获取 对应key缓存的图片
     *
     * @param key
     * @return
     */
    public Bitmap getBitmapFromDisk(String key, Bitmap reusable) {
        DiskLruCache.Snapshot snapshot = null;
        Bitmap bitmap = null;
        try {
            snapshot = diskLruCache.get(key);
            if (null == snapshot) {//如果没有对应key的缓存文件
                return null;
            }
            //获得文件输入流 读取bitmap
            InputStream is = snapshot.getInputStream(0);
            //为了能够被复用内存
            options.inMutable = true;
            options.inBitmap = reusable;
            Log.i("Adapter", "使用复用内存：" + reusable);
            bitmap = BitmapFactory.decodeStream(is, null, options);
            if (null != bitmap) {//加入到内存缓存中
//                memoryCache.put(key, bitmap);
                putBitmap2Memory(key, bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != snapshot) {
                snapshot.close();
            }
        }
        return bitmap;
    }

    public void clearMemory() {
        memoryCache.evictAll();
    }

    /**
     * 从复用池中获得可被复用的Bitmap
     * <p>Bitmap可被复用的条件：
     * 可被复用的Bitmap必须设置inMutable为true；
     * Android4.4(API 19)之前只有格式为jpg、png，同等宽高（要求苛刻），
     * inSampleSize为1的Bitmap才可以复用；
     * Android4.4(API 19)之前被复用的Bitmap的inPreferredConfig
     * 会覆盖待分配内存的Bitmap设置的inPreferredConfig；
     * Android4.4(API 19)之后被复用的Bitmap的内存
     * 必须大于等于需要申请内存的Bitmap的内存；
     *
     * @param w
     * @param h
     * @param inSampleSize
     * @return
     */
    public Bitmap getReusable(int w, int h, int inSampleSize) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return null;
        }
        Bitmap reusable = null;
        Iterator<WeakReference<Bitmap>> iterator = reusablePool.iterator();
        //迭代查找符合复用条件的Bitmap
        while (iterator.hasNext()) {
            Bitmap bitmap = iterator.next().get();
            if (null != bitmap) {
                //可以被复用
                if (checkInBitmap(bitmap, w, h, inSampleSize)) {
                    reusable = bitmap;
                    //移出复用池
                    iterator.remove();
                    break;
                }
            } else {
                iterator.remove();
            }
        }
        return reusable;
    }

    /**
     * 检查Bitmap是否可以被复用
     *
     * @param bitmap       被复用的Bitmap
     * @param w            新图片的宽度
     * @param h            新图片的高度
     * @param inSampleSize 新图片的缩放系数
     * @return
     */
    boolean checkInBitmap(Bitmap bitmap, int w, int h, int inSampleSize) {
        /**
         * Android4.4(API 19)之前只有格式为jpg、png，同等宽高（要求苛刻），
         * inSampleSize为1的Bitmap才可以复用；
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//
            return bitmap.getWidth() == w && bitmap.getHeight() == h
                    && inSampleSize == 1;
        }

        /**
         * Android4.4(API 19)之后被复用的Bitmap的内存
         * 必须大于等于需要申请内存的Bitmap的内存；
         */
        //如果缩放系数大于1 获得缩放后的宽与高
        if (inSampleSize > 1) {
            w /= inSampleSize;
            h /= inSampleSize;
        }
        //图片需要申请占用的内存
        int byteCout = w * h * getPixelsCout(bitmap.getConfig());
        /**
         * 一般情况下getByteCount()与getAllocationByteCount()是相等的；
         *通过复用Bitmap来解码图片，如果被复用的Bitmap的内存比待分配内存的Bitmap大,
         *那么getByteCount()表示新解码图片占用内存的大小（并非实际内存大小,实际大小
         *是复用的那个Bitmap的大小），getAllocationByteCount()表示被复用Bitmap占
         *用的内存大小。所以可能allocation比bytecount大。
         */
        return byteCout <= bitmap.getAllocationByteCount();
    }

    /**
     * 根据像素格式参数,获取每个像素占用的内存大小，单位字节
     *
     * @param config
     * @return
     */
    int getPixelsCout(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        return 2;
    }

}
