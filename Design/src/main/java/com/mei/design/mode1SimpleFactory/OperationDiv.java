package com.mei.design.mode1SimpleFactory;

import android.util.Log;

/**
 * Created by ubt on 2018/1/8.
 *
 * @description:除法运算
 */

public class OperationDiv extends Operation {
    @Override
    public float operate() {
        if (number2 == 0) {
            Log.e("plum", "被除数number2!=0");
        }
        return number1 / number2;
    }
}
