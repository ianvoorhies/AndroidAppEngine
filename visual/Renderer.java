package com.test.enginetest.visual;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.test.enginetest._super_structure.ObjectStructure;
public abstract class Renderer{
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private ObjectStructure sprite;
    private boolean enabled=true;
    private float xOffset=0.0f,yOffset=0.0f,zOffset=0.0f;
    public Renderer(ObjectStructure sprite){
        this.sprite=sprite;
        paint.setStyle(Paint.Style.FILL);
    }
    public final ObjectStructure getAttachedObjectStructure(){return sprite;}
    public final void enable(){setEnabled(true);}
    public final void disable(){setEnabled(false);}
    private final void setEnabled(boolean value){enabled=value;}
    public final boolean isEnabled(){return enabled;}
    public final void setXOffset(float value){xOffset=value;}
    public final void setYOffset(float value){yOffset=value;}
    public final void setZOffset(float value){zOffset=value;}
    public final float getXOffset(){return xOffset;}
    public final float getYOffset(){return yOffset;}
    public final float getZOffset(){return zOffset;}
    public final float getX(){return getAttachedObjectStructure().getX()+getXOffset();}
    public final float getY(){return getAttachedObjectStructure().getY()+getYOffset();}
    public final float getZ(){return getAttachedObjectStructure().getZ()+getZOffset();}
    public final void setPaint(Paint value){paint=value;}
    public final Paint getPaint(){return paint;}
    public final void render(Canvas canvas){if(isEnabled())onRender(canvas);}
//Override as needed
    protected void onRender(Canvas canvas){}
}
