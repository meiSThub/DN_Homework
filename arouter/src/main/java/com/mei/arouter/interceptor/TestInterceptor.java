package com.mei.arouter.interceptor;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * @author mxb
 * @date 2022/5/31
 * @desc
 * @desired
 */

// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(priority = 8, name = "测试用拦截器")
public class TestInterceptor implements IInterceptor {

    private static final String TAG = "TestInterceptor";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        // 1. 处理拦截业务
        if (!TextUtils.isEmpty(postcard.getPath())) {
            Log.i(TAG, "process:拦截器 url 合法");
        }
        // 2. 处理完成，交还控制权，即继续请求
        callback.onContinue(postcard);
        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));      // 觉得有问题，中断路由流程
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
        Log.i(TAG, "init: 拦截器初始化");
    }
}
