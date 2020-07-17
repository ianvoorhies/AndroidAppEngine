package com.test.enginetest.visual;
import android.graphics.Bitmap;
public final class Image{
    private Bitmap bitmap;
    private String name;
    public Image(String name,Bitmap bitmap){
        this.name=name;
        this.bitmap=bitmap;
    }
    public void setName(String value){name=value;}
    public String getName(){return name;}
    public Bitmap getBitmap(){return bitmap;}
}
