package com.messageforward;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsObserver extends ContentObserver {
    public String TAG = "Hellomin";
    private Uri SMS_INBOX = Uri.parse("content://sms/");
    private int MSG_OUTBOXCONTENT = 2;

    private Context mContext;
    private Handler mHandler;   //更新UI线程

    public SmsObserver(Context context, Handler handler) {
        super(handler);
        mContext = context;
        mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        //每当有新短信到来时，使用我们获取短消息的方法
        Log.i(TAG, "the sms table has changed");
        getSmsFromPhone();
    }

    public void getSmsFromPhone() {
        ContentResolver cr = mContext.getContentResolver();
        String[] projection = new String[] { "body" };//"_id", "address", "person",, "date", "type
        String where = " address = '1066321332' AND date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        Cursor cur = cr.query(SMS_INBOX, null, null, null, "date desc");
        if (null == cur)
            return;
        StringBuilder sb = new StringBuilder();
        Log.i(TAG, "getSmsFromPhone");

        if (cur.moveToNext()) {
            sb.append("发件人手机号码: "+cur.getString(cur.getColumnIndex("address")))
                    .append("\n")
                    .append("信息内容: "+ cur.getString(cur.getColumnIndex("body")))
                    .append("\n")
                    .append("是否查看: "+cur.getInt(cur.getColumnIndex("read")))
                    .append("\n")
                    .append("发送时间： "+cur.getInt(cur.getColumnIndex("date")))
                    .append("\n");

            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));

            mHandler.obtainMessage(MSG_OUTBOXCONTENT, sb.toString()).sendToTarget();

            //这里我是要获取自己短信服务号码中的验证码~~
//            Pattern pattern = Pattern.compile(" [a-zA-Z0-9]{10}");
//            Matcher matcher = pattern.matcher(body);
//            if (matcher.find()) {
//                String res = matcher.group().substring(1, 11);
//                Log.d(TAG, "getSmsFromPhone: "+res);
//            }
        }
    }

}
