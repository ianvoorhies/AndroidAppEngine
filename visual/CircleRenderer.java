package com.test.enginetest.visual;
import android.graphics.Canvas;
import com.test.enginetest._super_structure.ObjectStructure;
import com.test.enginetest.math.ComplexColor;
public final class CircleRenderer extends Renderer{
    private ComplexColor color=new ComplexColor();
    private float radius=0.0f;
    public CircleRenderer(ObjectStructure sprite){super(sprite);}
    public CircleRenderer(ObjectStructure sprite,float radius){super(sprite);setRadius(radius);}
    @Override protected void onRender(Canvas canvas){
        getPaint().setColor(color.toInt());
        canvas.drawCircle(getX(),getY(),getRadius(),getPaint());
    }
    public ComplexColor getColor(){return color;}
    public void setColor(ComplexColor value){color=value;}
    public void setColor(int value){this.color=new ComplexColor(value);}
    public void setRadius(float value){radius=value;}
    public float getRadius(){return radius;}
}
