package com.test.enginetest._super_structure;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import com.test.enginetest.util.CustomCallback;
import com.test.enginetest.math.ComplexColor;
public class ScreenStructure implements CustomCallback{
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private EngineStructure window;
    private ComplexColor backgroundColor=new ComplexColor();
    private String name="";
    private Canvas canvas;
    private float screenWidth=-1.0f,screenHeight=-1.0f;
    private boolean receiveUpdates=false;
    public ScreenStructure(){disableUpdates();}
    public final void init(){
        screenWidth=window.getScreenWidth();
        screenHeight=window.getScreenHeight();
        onCreate();
        enableUpdates();
    }
    public final float getScreenWidth(){return screenWidth;}
    public final float getScreenHeight(){return screenHeight;}
    public final boolean updatesEnabled(){return receiveUpdates;}
    public final void disableUpdates(){setReceiveUpdates(false);}
    public final void enableUpdates(){setReceiveUpdates(true);}
    private final void setReceiveUpdates(boolean value){receiveUpdates=value;}
    public final Canvas getCanvas(){return canvas;}
    public final Paint getPaint(){return paint;}
    public final void setWindow(EngineStructure value){window=value;}
    public final EngineStructure getWindow(){return window;}
    public final String getName(){return name;}
    public final void setName(String value){name=value;}
    public final Context getContext(){return getWindow().getContext();}
    public final Activity getActivity(){return getWindow().getActivity();}
    public final void setBackgroundColor(ComplexColor value){backgroundColor=value;}
    public final void setBackgroundColor(int value){this.backgroundColor=new ComplexColor(value);}
    public final ComplexColor getBackgroundColor(){return backgroundColor;}
    public final void render(Canvas canvas){
        this.canvas=canvas;
        getPaint().setColor(backgroundColor.toInt());
        getPaint().setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getScreenWidth(),getScreenHeight(),getPaint());
        //sprites
        onRender();
    }
    public final void drawLine(float x1,float y1,float x2,float y2){canvas.drawLine(x1,y1,x2,y2,getPaint());}
    public final void drawCircle(float x,float y,float radius){canvas.drawCircle(x,y,radius,getPaint());}
    public final void drawRect(float left,float top,float right,float bottom){canvas.drawRect(left,top,right,bottom,getPaint());}
    protected final void delayedCall(final String eventKey,final int duration){getActivity().runOnUiThread(new Runnable(){public void run(){new Handler().postDelayed(new Runnable(){@Override public void run(){onDelayedCall(eventKey);}},duration);}});}
    @Override public final void callbackEvent(String eventKey){}
    @Override public final void dataCallbackEvent(String eventKey,String eventData){}
    public final float getTouchX(){return getWindow().getTouchX();}
    public final float getTouchY(){return getWindow().getTouchY();}
    public final void touchDown(){onTouchDown();}
    public final void touchMove(){onTouchMove();}
    public final void touchUp(){onTouchUp();}
    public final void longTouch(){onLongTouch();}
    public final void backPressed(){onBackPressed();}
    public final void update(){onUpdate();}
    public final void pause(){onPause();}
    public final void resume(){onResume();}
    public final void destroy(){onDestroy();}
//Override as needed
    protected void onTouchDown(){}
    protected void onTouchMove(){}
    protected void onTouchUp(){}
    protected void onLongTouch(){}
    protected void onUpdate(){}
    protected void onDelayedCall(String eventKey){}
    protected void onBackPressed(){}
    protected void onPause(){}
    protected void onResume(){}
    protected void onDestroy(){}
    protected void onRender(){}
    protected void onCreate(){}
}
