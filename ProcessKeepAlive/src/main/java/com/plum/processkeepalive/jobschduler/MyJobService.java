package com.plum.processkeepalive.jobschduler;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

@SuppressLint("NewApi")
public class MyJobService extends JobService {

    public static void startJob(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(10, new ComponentName(context.getPackageName(), MyJobService.class.getName()))
                .setPersisted(true);
        //setPersisted 在设备重启依然执行

        //小于7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            //每隔1s执行一次job
            builder.setPeriodic(1_000);
        } else {
            //延迟1s执行任务，7.0及以上这样才会轮询
            builder.setMinimumLatency(1000);
        }
        jobScheduler.schedule(builder.build());
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("MyJobService", "开启job");
        //如果7.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJob(this);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
