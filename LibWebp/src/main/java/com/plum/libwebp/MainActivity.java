package com.plum.libwebp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.webp.libwebp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    static {
        System.loadLibrary("webp");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long l = System.currentTimeMillis();
        BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg);
        Log.e(TAG, "解码webp图片耗时：" + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_jpeg);
        Log.e(TAG, "解码jpeg图片耗时:" + (System.currentTimeMillis() - l));


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg_png);
        l = System.currentTimeMillis();
        compressBitmap(bitmap, Bitmap.CompressFormat.WEBP, Environment
                .getExternalStorageDirectory() + "/test.webp");
        Log.e(TAG, "编码webp图片耗时:" + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        compressBitmap(bitmap, Bitmap.CompressFormat.JPEG, Environment
                .getExternalStorageDirectory() + "/test.jpeg");
        Log.e(TAG, "编码jpeg图片耗时:" + (System.currentTimeMillis() - l));

        l = System.currentTimeMillis();
        encodeWebp(bitmap);
        Log.e(TAG, "libwebp编码图片耗时:" + (System.currentTimeMillis() - l));

        ImageView test = (ImageView) findViewById(R.id.test);
        test.setImageBitmap(decodeWebp());
    }

    byte[] stream2Bytes(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len;
        try {
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    /**
     * libwebp解码webp图片
     */
    private Bitmap decodeWebp() {
        InputStream is = getResources().openRawResource(R.drawable.splash_bg);
        byte[] bytes = stream2Bytes(is);
        //将webp格式的数据转成ARGB
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] width = new int[1];
        int[] height = new int[1];
        byte[] argb = libwebp.WebPDecodeARGB(bytes, bytes.length, width, height);
        //将argb byte数组转成int数组
        int[] pixels = new int[argb.length / 4];
        ByteBuffer.wrap(argb).asIntBuffer().get(pixels);
        //获得bitmap
        Bitmap bitmap = Bitmap.createBitmap(pixels, width[0], height[0], Bitmap.Config.ARGB_8888);
        return bitmap;
    }


    private void compressBitmap(Bitmap bitmap, Bitmap.CompressFormat format, String file) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            bitmap.compress(format, 75, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将bitmap 使用libwebp编码为 webp图片
     *
     * @param bitmap
     */
    private void encodeWebp(Bitmap bitmap) {
        //获取bitmap的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //获取bitmap中的ARGB数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        //编码 获得webp格式文件数据
        byte[] bytes = libwebp.WebPEncodeRGBA(byteBuffer.array(), width, height, width * 4, 75);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/libwebp.webp");
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
