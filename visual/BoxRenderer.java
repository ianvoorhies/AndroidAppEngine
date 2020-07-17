package com.test.enginetest.visual;
import android.graphics.Canvas;
import com.test.enginetest._super_structure.ObjectStructure;
import com.test.enginetest.math.ComplexColor;
public final class BoxRenderer extends Renderer{
    private ComplexColor color=new ComplexColor();
    private float width=0.0f,height=0.0f;
    public BoxRenderer(ObjectStructure sprite){super(sprite);}
    public BoxRenderer(ObjectStructure sprite,float size){super(sprite);setSize(size);}
    public BoxRenderer(ObjectStructure sprite,float width,float height){super(sprite);setSize(width,height);}
    @Override protected void onRender(Canvas canvas){
        getPaint().setColor(color.toInt());
        canvas.drawRect(getX(),getY(),getX()+getWidth(),getY()+getHeight(),getPaint());
    }
    public ComplexColor getColor(){return color;}
    public void setColor(ComplexColor value){color=value;}
    public void setColor(int value){this.color=new ComplexColor(value);}
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
