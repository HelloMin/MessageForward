package com.messageforward;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {
    private Uri SMS_INBOX = Uri.parse("content://sms/");
    public String TAG = "Hellomin";
    private SmsObserver smsObserver;

    public Handler smsHandler = new Handler() {
        public void handleMessage(Message msg) {
            System.out.println(TAG+"---mHanlder----");
            switch (msg.what) {
                case 2:
                    String outbox = (String) msg.obj;
                    System.out.println(TAG+"new message:"+outbox);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(TAG+"---onCreate----");
        smsObserver = new SmsObserver(this, smsHandler);
        getContentResolver().registerContentObserver(SMS_INBOX, true,
                smsObserver);
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "MessageForward";
    }
}
