package com.test.enginetest.math;
import android.graphics.RectF;
import com.test.enginetest._super_structure.ObjectStructure;
public final class BoxCollider extends Collider{
    private float width=0.0f,height=0.0f;
    public BoxCollider(ObjectStructure sprite){super(sprite);}
    public BoxCollider(ObjectStructure sprite,float size){super(sprite);setSize(size);}
    public BoxCollider(ObjectStructure sprite,float width,float height){super(sprite);setSize(width,height);}
    @Override protected boolean detectPoint(float x,float y){return(getX()<x&&getY()<y&&getX()+getWidth()>x&&getY()+getHeight()>y);}
    @Override protected boolean detectCollision(Collider collider){
        if(collider instanceof BoxCollider){
            RectF area=new RectF(getX(),getY(),getX()+getWidth(),getY()+getHeight());
            RectF area2=new RectF(collider.getX(),collider.getY(),collider.getX()+((BoxCollider)collider).getWidth(),collider.getY()+((BoxCollider)collider).getHeight());
            if(area.intersect(area2))return true;
        }
        else if(collider instanceof CircleCollider){
            //todo
        }
        return false;
    }
    public void setSize(float value){setSize(value,value);}
    public void setSize(float width,float height){
        setWidth(width);
        setHeight(height);
    }
    public void setWidth(float value){width=value;}
    public void setHeight(float value){width=value;}
    public float getWidth(){return width;}
    public float getHeight(){return height;}
}
