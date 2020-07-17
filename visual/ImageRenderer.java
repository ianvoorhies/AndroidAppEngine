package com.test.enginetest.visual;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.test.enginetest._super_structure.ObjectStructure;
public final class ImageRenderer extends Renderer{
    private Image image;
    private float width=0.0f,height=0.0f;
    public ImageRenderer(ObjectStructure sprite){super(sprite);}
    public ImageRenderer(ObjectStructure sprite,float size){super(sprite);setSize(size);}
    public ImageRenderer(ObjectStructure sprite,float width,float height){super(sprite);setSize(width,height);}
    @Override protected void onRender(Canvas canvas){
        try{canvas.drawBitmap(image.getBitmap(),null,new RectF(getX(),getY(),getX()+getWidth(),getY()+getHeight()),getPaint());}
        catch(Exception e){}
    }
    public void setImage(Image value){image=value;}
    public Image getImage(){return image;}
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
