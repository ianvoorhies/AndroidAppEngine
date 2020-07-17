package com.test.enginetest.util;
import android.content.Context;
import android.content.SharedPreferences;
public final class SaveData{
    private Context context;
    private SharedPreferences sharedPreferences;
    private String library="SaveData";
    public SaveData(Context context){init(context,library);}
    public SaveData(Context context,String library){init(context,library);}
    private void init(Context context,String library){
        this.context=context;
        setLibrary(library);
    }
    public void setLibrary(String value){
        library=value;
        sharedPreferences=context.getSharedPreferences(library,context.MODE_PRIVATE);
    }
    public String getLibrary(){return library;}
    public boolean saveBoolean(String key,boolean value){
        try{sharedPreferences.edit().putBoolean(key,value).apply();return true;}
        catch(Exception e){return false;}
    }
    public boolean flipBoolean(String key){
        boolean temp=!loadBoolean(key);
        saveBoolean(key,temp);
        return temp;
    }
    public boolean loadBoolean(String key){return loadBoolean(key,false);}
    public boolean loadBoolean(String key,boolean defaultValue){return sharedPreferences.getBoolean(key,defaultValue);}
    public boolean saveFloat(String key,float value){
        try{sharedPreferences.edit().putFloat(key,value).apply();return true;}
        catch(Exception e){return false;}
    }
    public float incFloat(String key){return incFloat(key,1.0f);}
    public float incFloat(String key,float value){
        float temp=loadInt(key)+value;
        saveFloat(key,temp);
        return temp;
    }
    public float decFloat(String key){return incFloat(key,1.0f);}
    public float decFloat(String key,float value){
        float temp=loadInt(key)-value;
        saveFloat(key,temp);
        return temp;
    }
    public float loadFloat(String key){return loadFloat(key,0.0f);}
    public float loadFloat(String key,float defaultValue){return sharedPreferences.getFloat(key,defaultValue);}
    public boolean saveInt(String key,int value){
        try{sharedPreferences.edit().putInt(key,value).apply();return true;}
        catch(Exception e){return false;}
    }
    public int incInt(String key){return incInt(key,1);}
    public int incInt(String key,int value){
        int temp=loadInt(key)+value;
        saveInt(key,temp);
        return temp;
    }
    public int decInt(String key){return decInt(key,1);}
    public int decInt(String key,int value){
        int temp=loadInt(key)-value;
        saveInt(key,temp);
        return temp;
    }
    public int loadInt(String key){return loadInt(key,0);}
    public int loadInt(String key,int defaultValue){return sharedPreferences.getInt(key,defaultValue);}
    public boolean saveString(String key,String value){
        try{sharedPreferences.edit().putString(key,value).apply();return true;}
        catch(Exception e){return false;}
    }
    public String loadString(String key){return loadString(key,"");}
    public String loadString(String key,String defaultValue){return sharedPreferences.getString(key,defaultValue);}
}
