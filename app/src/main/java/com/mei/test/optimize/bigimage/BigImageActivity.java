package com.mei.test.optimize.bigimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mei.test.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * 长图加载
 */
public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        BigView bigview = (BigView) findViewById(R.id.big_view);

        InputStream is = null;
        try {
            is = getAssets().open("big.png");
            bigview.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
