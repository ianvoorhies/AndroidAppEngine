package com.test.enginetest.hardware;
/*
REQUIRED PERMISSIONS:
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.FLASHLIGHT"/>
<uses-feature android:name="android.hardware.camera"/>
<uses-feature android:name="android.hardware.camera.flash"/>
*/
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Flashlight{
    private CameraManager mCameraManager;
    private String mCameraId;
    private boolean itsLIT;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Flashlight(Context context){
        itsLIT=false;
        try{
            mCameraManager=(CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
            mCameraId=mCameraManager.getCameraIdList()[0];
        }
        catch(Exception e){e.printStackTrace();}
    }
    public void on(){itsLIT=change(true);}
    public void off(){itsLIT=change(false);}
    public void toggle(){itsLIT=change(!isItLIT());}
    public boolean isItLIT(){return itsLIT;}
    private boolean change(boolean on){
        try{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(on)mCameraManager.setTorchMode(mCameraId,true);
                else mCameraManager.setTorchMode(mCameraId,false);
                return on;
            }
        }
        catch(Exception e){e.printStackTrace();}
        return false;
    }
}
