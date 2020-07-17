package com.test.enginetest.hardware;
import android.annotation.SuppressLint;
import android.content.Context;
//Required permission: <uses-permission android:name="android.permission.VIBRATE"/>
public final class Vibrate{
    private android.os.Vibrator vibrator;
    public Vibrate(Context context){vibrator=(android.os.Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);}
    public void vibrate(){vibrate(1);}
    @SuppressLint("MissingPermission")
    public void vibrate(int duration){
            vibrator.vibrate(duration);
    }
}
