package com.leecompany.yoursentence;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreenTextService extends Service {

    private BroadcastReceiver mReceiver;
    private Boolean isShowing = false;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private TextView setTextView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, new Notification());
        mWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        setTextView = new TextView(this);
        setTextView.setText(intent.getStringExtra("userSetText"));
        switch(intent.getIntExtra("userSetColor", 8)) {
            case 1:
                setTextView.setTextColor(Color.parseColor("#FF0000"));
                break;
            case 2:
                setTextView.setTextColor(Color.parseColor("#FFBB00"));
                break;
            case 3:
                setTextView.setTextColor(Color.parseColor("#ABF200"));
                break;
            case 4:
                setTextView.setTextColor(Color.parseColor("#0054FF"));
                break;
            case 5:
                setTextView.setTextColor(Color.parseColor("#FFB2D9"));
                break;
            case 6:
                setTextView.setTextColor(Color.parseColor("#FFE400"));
                break;
            case 7:
                setTextView.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 8:
                setTextView.setTextColor(Color.parseColor("#000000"));
                break;
            default:
                setTextView.setTextColor(Color.parseColor("#000000"));
                break;
        }
        setTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, intent.getIntExtra("userSetSize", 24));

        switch(intent.getIntExtra("userSetFont", 1)) {
            case 0:
                break;
            case 1:
                // 진지한
                Typeface mTypeFace02 = Typeface.createFromAsset(getAssets(), "fonts/HYBSRB.TTF");
                setTextView.setTypeface(mTypeFace02);
                break;
            case 2:
                // 동글이
                Typeface mTypeFace03 = Typeface.createFromAsset(getAssets(), "fonts/HYDNKB.TTF");
                setTextView.setTypeface(mTypeFace03);
                break;
            case 3:
                // 동글이
                Typeface mTypeFace04 = Typeface.createFromAsset(getAssets(), "fonts/HMFMPYUN.TTF");
                setTextView.setTypeface(mTypeFace04);
                break;
        }

        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        switch(intent.getIntExtra("userSetPosition", 2)) {
            case 1:
                mParams.gravity = Gravity.TOP;
                mParams.y = 100;
                break;
            case 2:
                mParams.gravity = Gravity.BOTTOM;
                mParams.y = 100;
                break;
            default:
                mParams.gravity = Gravity.BOTTOM;
                mParams.y = 100;
                break;
        }

        mReceiver = new LockScreenStateReceiver();

        IntentFilter mFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        mFilter.addAction((Intent.ACTION_USER_PRESENT));

        registerReceiver(mReceiver, mFilter);

        return START_REDELIVER_INTENT;
    }

    public class LockScreenStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                if(!isShowing) {
                    mWindowManager.addView(setTextView, mParams);
                    isShowing = true;
                }
            }

            else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                if(isShowing) {
                    mWindowManager.removeViewImmediate(setTextView);
                    isShowing = false;
                }
            }
        }
    }
}
