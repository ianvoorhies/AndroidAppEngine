package com.test.enginetest._super_structure;
import java.util.ArrayList;
import java.util.List;
import com.test.enginetest.hardware.Memory;
import com.test.enginetest.util.CustomCallback;
import com.test.enginetest.util.ThreadExecution;
import com.test.enginetest.hardware.Dimension;
import com.test.enginetest.math.ComplexColor;
import com.test.enginetest.visual.Image;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public final class EngineStructure extends View implements CustomCallback{
    private final Handler longTouchHandler=new Handler();
    private Runnable longTouchRunnable=new Runnable(){public void run(){touchLong(touchX,touchY);}};
    private List<Image>images=new ArrayList<>();
    private Activity activity;
    private ComplexColor backgroundColor=new ComplexColor();
    private Dimension hardware;
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private ScreenStructure currentSceneStruct;
    private ThreadExecution timer;
    private boolean touching=false,longTouchStarted=false,touchEnabled=true;
    private float touchX=-1.0f,touchY=-1.0f,screenWidth=-1.0f,screenHeight=-1.0f;
    private int longTouchDuration=270;
    public EngineStructure(Context context){super(context);init(context);}
    public EngineStructure(Context context,AttributeSet attrs){super(context,attrs);init(context);}
    public EngineStructure(Context context,AttributeSet attrs,int defStyle){super(context,attrs,defStyle);init(context);}
    private void init(Context context){
        activity=(Activity)context;
        activity.setContentView(this);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(activity instanceof AppCompatActivity)try{((AppCompatActivity)activity).getSupportActionBar().hide();}catch(Exception e){}
        paint.setStyle(Paint.Style.FILL);
        hardware=new Dimension(getContext());
        screenWidth=hardware.getScreenWidth();
        screenHeight=hardware.getScreenHeight();
        Memory.collectGarbage();
        setBackgroundColor(Color.BLACK);
        timer=new ThreadExecution(this,"update",10);
    }
    @Override public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN)touchDown(event.getX(),event.getY());
        else if(event.getAction()==MotionEvent.ACTION_MOVE)touchMove(event.getX(),event.getY());
        else if(event.getAction()==MotionEvent.ACTION_UP)touchUp(event.getX(),event.getY());
        return true;
    }
    private void touchDown(float touchX,float touchY){
        if(touchEnabled){
            setTouchCoords(touchX,touchY,true);
            longTouchStarted=true;
            longTouchHandler.postDelayed(longTouchRunnable,longTouchDuration);
            if(getCurrentScene()!=null)getCurrentScene().touchDown();
        }else setTouchCoords(touchX,touchY);
    }
    private void touchMove(float touchX,float touchY){
        setTouchCoords(touchX,touchY);
        if(touchEnabled&&getCurrentScene()!=null)getCurrentScene().touchMove();
    }
    private void touchUp(float touchX,float touchY){
        if(touchEnabled){
            setTouchCoords(touchX,touchY,false);
            if(getCurrentScene()!=null)getCurrentScene().touchUp();
        }
        else setTouchCoords(touchX,touchY);
    }
    private void touchLong(float touchX,float touchY){
        if(!longTouchStarted||!touchEnabled)return;
        longTouchStarted=false;
        setTouchCoords(touchX,touchY);
        if(getCurrentScene()!=null)getCurrentScene().longTouch();
    }
    private void setTouchCoords(float x,float y,boolean touching){
        setTouchCoords(x,y);
        setTouching(touching);
        if(!touching)longTouchStarted=false;
    }
    private void setTouchCoords(float touchX,float touchY){
        this.touchX=touchX;
        this.touchY=touchY;
    }
    private void setTouching(boolean value){touching=value;}
    public boolean isTouching(){return touching;}
    public float getTouchX(){return touchX;}
    public float getTouchY(){return touchY;}
    public void enableTouch(){setTouchEnabled(true);}
    public void disableTouch(){setTouchEnabled(false);}
    private void setTouchEnabled(boolean value){
        touchEnabled=value;
        if(!touchEnabled)touching=false;
    }
    public void setLongTouchDuration(int longTouchDuration){this.longTouchDuration=longTouchDuration;}
    public int getLongTouchDuration(){return longTouchDuration;}
    @Override public void callbackEvent(String eventKey){if(eventKey.equals("update"));}
    @Override public void dataCallbackEvent(String eventKey,String data){}
    public void update(){if(getCurrentScene()!=null)if(getCurrentScene().updatesEnabled())getCurrentScene().update();}
    @Override public void onDraw(Canvas canvas){
        paint.setColor(getBackgroundColor().toInt());
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        if(getCurrentScene()!=null)getCurrentScene().render(canvas);
        invalidate();
    }
    public ComplexColor getBackgroundColor(){return backgroundColor;}
    public void setBackgroundColor(ComplexColor value){backgroundColor=value;}
    public void setBackgroundColor(int value){this.backgroundColor=new ComplexColor(value);}
    public void closeApp(){
        destroy();
        hardware.closeApp();
    }
    public void backPressed(){if(getCurrentScene()!=null)getCurrentScene().backPressed();}
    public void pause(){
        timer.pause();
        if(getCurrentScene()!=null)getCurrentScene().pause();
    }
    public void resume(){
        timer.resume();
        if(getCurrentScene()!=null)getCurrentScene().resume();
    }
    public void destroy(){
        timer.kill();
        if(getCurrentScene()!=null)getCurrentScene().destroy();
    }
    public float getScreenWidth(){return screenWidth;}
    public float getScreenHeight(){return screenHeight;}
    public Dimension getDimension(){return hardware;}
    public ScreenStructure getCurrentScene(){return currentSceneStruct;}
    public String getSceneName(){if(getCurrentScene()!=null)return getCurrentScene().getName();return"";}
    public Image addImage(String name,Bitmap bitmap){
        images.add(new Image(name,bitmap));
        try{return images.get(images.size()-1);}
        catch(Exception e){return null;}
    }
    public Image getImage(String name){
        for(int i=0;i<images.size();i++)if(images.get(i).getName().equals(name))return images.get(i);
        return null;
    }
    public Image getImage(int index){
        if(index<0||index>images.size()-1)return null;
        return images.get(index);
    }
    public ScreenStructure switchScene(ScreenStructure scene){return switchScene(scene,"");}
    public ScreenStructure switchScene(ScreenStructure scene,String name){
        if(getCurrentScene()!=null)getCurrentScene().disableUpdates();
        this.currentSceneStruct=scene;
        getCurrentScene().setWindow(this);
        getCurrentScene().setName(name);
        getCurrentScene().init();
        return getCurrentScene();
    }
    public Activity getActivity(){return activity;}
}
