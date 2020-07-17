package com.test.enginetest.util;
import android.content.Context;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
public final class FileTools{
    private Context context;
    public FileTools(Context context){this.context=context;}
    public boolean isExternalStorageWritable(){
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))return true;
        return false;
    }
    public boolean isExternalStorageReadable(){
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))return true;
        return false;
    }
    public boolean initializeFile(String filePath,String value){
        File file=context.getFileStreamPath(filePath);
        if(file.exists())return true;
        try{file.createNewFile();}
        catch(Exception e){e.printStackTrace();}
        return saveStringToFile(filePath,value);
    }
    public boolean appendStringToFile(String filePath,String value){return saveStringToFile(filePath,value,true);}
    public boolean saveStringToFile(String filePath,String value){return saveStringToFile(filePath,value,false);}
    private boolean saveStringToFile(String filePath,String value,boolean append){
        File file=context.getFileStreamPath(filePath);
        if(!file.exists())
            try{file.createNewFile();}
            catch(Exception e){return false;}
        String fileOut=value;
        FileOutputStream writer;
        try{
            if(append)writer=context.openFileOutput(file.getName(),Context.MODE_APPEND);
            else writer=context.openFileOutput(file.getName(),Context.MODE_PRIVATE);
            writer.write(fileOut.getBytes());
            writer.flush();
            writer.close();
            return true;
        }catch(Exception e){return false;}
    }
    public String readStringFromFile(String filePath){
        StringBuffer data=new StringBuffer("");
        try{
            FileInputStream fileIn=context.openFileInput(filePath);
            InputStreamReader inputStream=new InputStreamReader(fileIn);
            BufferedReader bufferedReader=new BufferedReader(inputStream);
            String readString=bufferedReader.readLine();
            while(readString!=null){
                data.append(readString);
                readString=bufferedReader.readLine();
            }
            inputStream.close();
        }catch(Exception e){e.printStackTrace();}
        return data.toString();
    }
}
