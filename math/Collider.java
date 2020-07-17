package com.test.enginetest.math;
import com.test.enginetest._super_structure.ObjectStructure;
public abstract class Collider{
    private ObjectStructure sprite;
    private boolean enabled=true;
    private float xOffset=0.0f,yOffset=0.0f,zOffset=0.0f;
    public Collider(ObjectStructure sprite){this.sprite=sprite;}
    public final ObjectStructure getAttachedStructure(){return sprite;}
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
    public final float getX(){return getAttachedStructure().getX()+getXOffset();}
    public final float getY(){return getAttachedStructure().getY()+getYOffset();}
    public final float getZ(){return getAttachedStructure().getZ()+getZOffset();}
    public final boolean containsPoint(float x,float y){
        if(isEnabled())return detectPoint(x,y);
        return false;
    }
    public final boolean isTouching(Collider collider){
        if(getZ()!=collider.getZ()||!isEnabled())return false;
        return detectCollision(collider);
    }
    public final void collide(Collider collider){
        if(isEnabled()&&collider.isEnabled()){
            getAttachedStructure().collide(collider);
            collider.getAttachedStructure().collide(this);
        }
    }
//Override as needed
    protected boolean detectCollision(Collider collider){return false;}
    protected boolean detectPoint(float x,float y){return false;}
}
