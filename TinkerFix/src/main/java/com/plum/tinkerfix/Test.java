package com.plum.tinkerfix;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class Test {

    public  void testFix(Context context){
        int i = 10;
        int a = 0;
        Toast.makeText(context, "shit:"+i/a, Toast.LENGTH_SHORT).show();
    }

}
