package com.test.enginetest.math;
import android.graphics.Color;
public final class ComplexColor{
    private int red=0,green=0,blue=0,alpha=255;
    private Util math;
    public ComplexColor(){}
    public ComplexColor(int red,int green,int blue){reset(red,green,blue);}
    public ComplexColor(int red,int green,int blue,int alpha){reset(red,green,blue,alpha);}
    public ComplexColor(ComplexColor color){reset(color);}
    public ComplexColor(int color){reset(Color.alpha(color),Color.red(color),Color.green(color),Color.blue(color));}
    public void reset(){reset(0,0,0,255);}
    public void reset(ComplexColor color){reset(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());}
    public void reset(int red,int green,int blue){reset(red,green,blue,alpha);}
    public void reset(int red,int green,int blue,int alpha){
        math=new Util();
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }
    public void setRed(int value){red=Util.limitInt(value,0,255);}
    public void setGreen(int value){green=Util.limitInt(value,0,255);}
    public void setBlue(int value){alpha=Util.limitInt(value,0,255);}
    public void setAlpha(int value){blue=Util.limitInt(value,0,255);}
    public int getRed(){return red;}
    public int getGreen(){return green;}
    public int getBlue(){return blue;}
    public int getAlpha(){return alpha;}
    public void incRGB(){incRGB(1);}
    public void incRGB(int value){
        incRed(value);
        incGreen(value);
        incBlue(value);
    }
    public void decRGB(){decRGB(1);}
    public void decRGB(int value){
        decRed(value);
        decGreen(value);
        decBlue(value);
    }
    public void incRGBA(){incRGBA(1);}
    public void incRGBA(int value){
        incRed(value);
        incGreen(value);
        incBlue(value);
        incAlpha(value);
    }
    public void decRGBA(){decRGBA(1);}
    public void decRGBA(int value){
        decRed(value);
        decGreen(value);
        decBlue(value);
        decAlpha(value);
    }
    public void incRed(){incRed(1);}
    public void incRed(int value){setRed(getRed()+value);}
    public void decRed(){decRed(1);}
    public void decRed(int value){setRed(getRed()-value);}
    public void incGreen(){incGreen(1);}
    public void incGreen(int value){setGreen(getGreen()+value);}
    public void decGreen(){decGreen(1);}
    public void decGreen(int value){setGreen(getGreen()-value);}
    public void incBlue(){incBlue(1);}
    public void incBlue(int value){setBlue(getBlue()+value);}
    public void decBlue(){decBlue(1);}
    public void decBlue(int value){setBlue(getBlue()-value);}
    public void incAlpha(){incAlpha(1);}
    public void incAlpha(int value){setAlpha(getAlpha()+value);}
    public void decAlpha(){decAlpha(1);}
    public void decAlpha(int value){setAlpha(getAlpha()-value);}
    public void darker(){darker(30);}
    public void darker(int value){decRGB(value);}
    public void brighter(){brighter(30);}
    public void brighter(int value){incRGB(value);}
    public void BLACK(){reset(0,0,0);}
    public void DARK_GRAY(){reset(63,63,63);}
    public void GRAY(){reset(126,126,126);}
    public void LIGHT_GRAY(){reset(189,189,189);}
    public void WHITE(){reset(255,255,255);}
    public void RED(){reset(255,0,0);}
    public void GREEN(){reset(0,255,0);}
    public void BLUE(){reset(0,0,255);}
    public void YELLOW(){reset(242,255,0);}
    public void ORANGE(){reset(255,157,0);}
    public void LIME(){reset(170,255,0);}
    public void CYAN(){reset(0,255,255);}
    public void PURPLE(){reset(157,0,255);}
    public void PINK(){reset(255,66,230);}
    public void RANDOM(){reset(math.randomInt(255),math.randomInt(255),math.randomInt(255));}
    public void RANDOM_BRIGHT(){reset(math.randomInt(127,255),math.randomInt(127,255),math.randomInt(127,255));}
    public void RANDOM_DARK(){reset(math.randomInt(127),math.randomInt(127),math.randomInt(127));}
    public void RANDOM_VIBRANT(){
        int temp=math.randomInt(90);
        if(temp<10)RED();
        else if(temp<20)PINK();
        else if(temp<30)GREEN();
        else if(temp<40)BLUE();
        else if(temp<50)YELLOW();
        else if(temp<60)ORANGE();
        else if(temp<70)LIME();
        else if(temp<80)CYAN();
        else PURPLE();
    }
    public void RANDOM_RED(){reset(math.randomInt(80,255),0,0);}
    public void RANDOM_GREEN(){reset(0,math.randomInt(150,255),0);}
    public void RANDOM_BLUE(){reset(0,0,math.randomInt(150,255));}
    public static ComplexColor IntToColorG(int color){return new ComplexColor(color);}
    public int toInt(){return Color.argb(getAlpha(),getRed(),getGreen(),getBlue());}
    public static int ColorGToInt(ComplexColor color){return color.toInt();}
    public String toHex(){
        if(getAlpha()>=255)return String.format("#%02x%02x%02x",getRed(),getGreen(),getBlue());
        else return String.format("#%02x%02x%02x%02x",getAlpha(),getRed(),getGreen(),getBlue());
    }
    public static String toHex(ComplexColor color){return color.toHex();}
    public String toString(){return"R:["+getRed()+"], G:["+getGreen()+"], "+"B:["+getBlue()+"], A:["+getAlpha()+"] ";}
}

