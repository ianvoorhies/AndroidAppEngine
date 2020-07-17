package com.test.enginetest.math;
import android.graphics.Point;
import java.text.DecimalFormat;
import java.util.Random;
public final class Util{
    private Random random;
    public Util(){this.random=new Random();}
    public static float limitFloat(float value,float lowerBound,float upperBound){
        if(value>upperBound)value=upperBound;
        else if(value<lowerBound)value=lowerBound;
        return value;
    }
    public static int limitInt(int value,int lowerBound,int upperBound){
        if(value>upperBound)value=upperBound;
        else if(value<lowerBound)value=lowerBound;
        return value;
    }
    public static float distance(Point point1,Point point2){return distance(point1.x,point1.y,point2.x,point2.y);}
    public static float distance(float x1,float y1,float x2,float y2){return(float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));}
    public static String roundedFloatString(float value,int decimalPlaces){return roundedDoubleString(value,decimalPlaces);}
    public static String roundedDoubleString(double value,int decimalPlaces){
        String formatKey="#";
        if(decimalPlaces>0){
            formatKey+=".";
            for(int i=0;i<decimalPlaces;i++)formatKey+="#";
        }
        DecimalFormat decimalFormat=new DecimalFormat(formatKey);
        return decimalFormat.format(value);
    }
    public static float accuracyPercentage(int hits,int misses){return((float)hits/((float)hits+(float)misses))*100;}
    public static String secondsToHMS(int value){
        int hours=value/3600;
        int remainder=value%3600;
        int minutes=remainder/60;
        int seconds=remainder%60;
        String timeText="";
        if(hours>1)timeText+=hours+"h ";
        if(minutes>1&&seconds!=0)timeText+=minutes+"m ";
        if(seconds>1&&seconds!=0)timeText+=seconds+"s";
        if(timeText.length()<=1)timeText="0s";
        return timeText;
    }
    public static float MSToSeconds(float value){return value/1000.0f;}
    public static float MSToMinutes(float value){return value/60000.0f;}
    public static float secondsToMS(float value){return value*1000.0f;}
    public static float secondsToMinutes(float value){return value/60.0f;}
    public static float minutesToMS(float value){return value*60000.0f;}
    public static float minutesToSeconds(float value){return value*60.0f;}
    public String randomString(int length){return randomString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",length);}
    public String randomString(String characterSet,int length){
        StringBuilder stringBuilder=new StringBuilder(length);
        for(int i=0;i<length;i++)stringBuilder.append(characterSet.charAt(random.nextInt(characterSet.length())));
        return stringBuilder.toString();
    }
    public char randomLetter(){return randomChar("ABCDEFGHIJKLMNOPQRSTUVWXYZ");}
    public char randomChar(){return randomChar("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");}
    public char randomChar(String characterSet){return characterSet.charAt(random.nextInt(characterSet.length()));}
    public double randomDouble(){return randomDouble(Double.MAX_VALUE-1);}
    public double randomDouble(double max){return randomDouble(0,max);}
    public double randomDouble(double min,double max){return min+(max-min)*random.nextDouble();}
    public float randomFloat(){return randomFloat(Float.MAX_VALUE-1);}
    public float randomFloat(float max){return randomFloat(0,max);}
    public float randomFloat(float min,float max){return random.nextFloat()*(max-min)+min;}
    public int randomInt(){return randomInt(Integer.MAX_VALUE-1);}
    public int randomInt(int max){return randomInt(0,max);}
    public int randomInt(int min,int max){return random.nextInt((max-min)+1)+min;}
    public boolean randomBoolean(){
        int random=randomInt(0,100);
        if(random<=50)return true;
        return false;
    }
}
