package ubt.com.test.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

import java.util.Date;

import ubt.com.test.R;

/**
 * 通知
 */
public class NotificationActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID_BASIC = 1;
    private static final int NOTIFICATION_ID_COLLAPSE = 2;
    private static final int NOTIFICATION_ID_HEADSUP = 3;
    private static final int NOTIFICATION_ID_VISIBILITY = 4;
    private static final String CHANNEL_ID = "notification";
    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mRadioGroup = findViewById(R.id.visibility_radio_group);
    }

    /**
     * 普通的通知，在状态栏上显示图标，下拉后显示横条消息
     *
     * @param view
     */
    public void basicNotify(View view) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        //构造PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Basic Notification");
        builder.setContentText("I am a basic notification");
        builder.setSubText("it is really basic");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(NOTIFICATION_ID_BASIC, builder.build());
    }

    /**
     * 折叠式Notification，本例没看出效果，需要继续调试
     * 此类通知显示在状态栏上，需要下拉才能看到消息相关信息
     *
     * @param view
     */
    public void collapsedNotify(View view) {
        //用于点击跳转的intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sina.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        // 通过RemoteViews来创建自定义的Notification视图
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        contentView.setTextViewText(R.id.textView, "show me when collapsed");
        // 通过RemoteViews来创建自定义的Notification视图
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        Notification notification = builder.build();
        notification.contentView = contentView;
        notification.bigContentView = expandedView;

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID_COLLAPSE, builder.build());

    }

    /**
     * 悬挂式Notification
     * 显示在用户界面之上，不用下拉就可以看到消息了
     *
     * @param view
     */
    public void headsupNotify(View view) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("content title");
        builder.setContentText("content info");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setWhen(new Date().getTime());

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //通过设置setFullScreenIntent方法就可以把通知变成悬挂式的通知了，不管用户在什么界面，都会显示在界面之上的通知
        builder.setFullScreenIntent(pendingIntent, true);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID_HEADSUP, builder.build());
    }

    /**
     * 显示等级Notification，在通知栏可见,即下拉可见
     *
     * @param view
     */
    public void visibilityNotify(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Notification for visibility test");
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_button_private:
                //setVisibility方法，设置Notification显示等级
                builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                builder.setContentText("private");
                builder.setSmallIcon(R.drawable.ic_private);
                break;
            case R.id.radio_button_public:
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                builder.setSmallIcon(R.drawable.ic_public);
                break;
            case R.id.radio_button_secret:
                builder.setVisibility(NotificationCompat.VISIBILITY_SECRET);
                builder.setContentText("secret");
                builder.setSmallIcon(R.drawable.ic_secret);
                break;
        }
        builder.setWhen(new Date().getTime());
        builder.setAutoCancel(true);
        builder.setCategory(Notification.CATEGORY_MESSAGE);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID_VISIBILITY, builder.build());
    }

}
