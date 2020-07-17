package com.test.enginetest.hardware;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.Display;
public final class Dimension{
    private Context context;
    private float screenWidth=-1.0f,screenHeight=-1.0f;
    public Dimension(Context context){
        this.context=context;
        Display display=((Activity)context).getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        screenWidth=size.x;
        screenHeight=size.y;
    }
    public PointF getScreenSize(){return new PointF(screenWidth,screenHeight);}
    public float getScreenWidth(){return screenWidth;}
    public float getScreenHeight(){return screenHeight;}
    public void closeApp(){((Activity)context).finish();}
    public void launchActivity(Intent intent){context.startActivity(intent);}
}
