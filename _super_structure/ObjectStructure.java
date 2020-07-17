package com.test.enginetest._super_structure;
import android.graphics.Canvas;
import com.test.enginetest.math.Collider;
import com.test.enginetest.visual.Renderer;
public class ObjectStructure{
    private Collider collider;
    private Renderer renderer;
    private String name="";
    private boolean initialized=false,created=false;
    private float x=0.0f,y=0.0f,z=0.0f;
    public ObjectStructure(){moveTo(0.0f,0.0f,0.0f);}
    public ObjectStructure(float x,float y){moveTo(x,y,0.0f);}
    public ObjectStructure(float x,float y,float z){moveTo(x,y,z);}
    public final void init(){
        initialized=true;
        onCreate();
        created=true;
    }
    public final void moveUp(){moveUp(1.0f);}
    public final void moveUp(float distance){setY(getY()-distance);}
    public final void moveRight(){moveRight(1.0f);}
    public final void moveRight(float distance){setX(getX()+distance);}
    public final void moveDown(){moveDown(1.0f);}
    public final void moveDown(float distance){setY(getY()+distance);}
    public final void moveLeft(){moveLeft(1.0f);}
    public final void moveLeft(float distance){setX(getX()-distance);}
    public final void moveTo(float x,float y){moveTo(x,y,getZ());}
    public final void moveTo(float x,float y,float z){
        setX(x);
        setY(y);
        setZ(z);
    }
    public final void setX(float value){x=value;}
    public final void setY(float value){y=value;}
    public final void setZ(float value){z=value;}
    public final float getX(){return x;}
    public final float getY(){return y;}
    public final float getZ(){return z;}
    public final boolean isInitialized(){return initialized;}
    public final boolean isCreated(){return created;}
    public final void setName(String value){name=value;}
    public final String getName(){return name;}
    public final void update(){onUpdate();}
    public final void collide(Collider collider){onCollision(collider.getAttachedStructure());}
    public final void setCollider(Collider value){this.collider=value;}
    public final Collider getCollider(){return collider;}
    public final void setRenderer(Renderer value){this.renderer=value;}
    public final Renderer getRenderer(){return renderer;}
    public final void render(Canvas canvas){
        getRenderer().render(canvas);
        onRender();
    }
//Override as needed
    protected void onUpdate(){}
    protected void onRender(){}
    protected void onCreate(){}
    protected void onCollision(ObjectStructure other){}
}
