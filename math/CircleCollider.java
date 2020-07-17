package com.test.enginetest.math;
import com.test.enginetest._super_structure.ObjectStructure;
public final class CircleCollider extends Collider{
    private float radius=0.0f;
    public CircleCollider(ObjectStructure sprite){super(sprite);}
    public CircleCollider(ObjectStructure sprite,float radius){super(sprite);setRadius(radius);}
    @Override protected boolean detectPoint(float x,float y){return(Util.distance(getX(),getY(),x,y)<=getRadius());}
    @Override protected boolean detectCollision(Collider collider){
        if(collider instanceof CircleCollider){if(Util.distance(getX(),getY(),collider.getX(),collider.getY())<=getRadius())return true;}
        else if(collider instanceof BoxCollider){
            //todo
        }
        return false;
    }
    public void setRadius(float value){radius=value;}
    public float getRadius(){return radius;}
}
