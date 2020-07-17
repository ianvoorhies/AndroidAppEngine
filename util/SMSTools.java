package com.test.enginetest.util;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class SMSTools{
    private Context context;
    public SMSTools(Context context){
        this.context=context;
        if(ContextCompat.checkSelfPermission(context,"android.permission.READ_SMS")==PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions((Activity)context,new String[]{"android.permission.READ_SMS"},1);
    }
    public SMS[]getSMS(int start,int stahp){
        if(ContextCompat.checkSelfPermission(context,"android.permission.READ_SMS")!=PackageManager.PERMISSION_GRANTED)return new SMS[]{};
        try{
            if((stahp-start)+1<=0) return new SMS[]{};
            SMS[] data=new SMS[(stahp-start)+1];
            Uri uri=Uri.parse("content://sms");
            Cursor cursor=context.getContentResolver().query(uri,new String[]{"_id","thread_id","address","person","date","body","type"},null,null,null);
            int loop=0,arrayindex=0;
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    String id=cursor.getString(cursor.getColumnIndex("_id"));
                    String thread=cursor.getString(cursor.getColumnIndex("thread_id"));
                    String address=cursor.getString(cursor.getColumnIndex("address"));
                    String name=cursor.getString(cursor.getColumnIndex("person"));
                    String date=cursor.getString(cursor.getColumnIndex("date"));
                    String msg=cursor.getString(cursor.getColumnIndex("body"));
                    String type=cursor.getString(cursor.getColumnIndex("type"));
                    if(loop>=start&&loop<=stahp){
                        data[arrayindex]=new SMS(new String[]{id,thread,address,name,date,msg,type});
                        arrayindex++;
                    }
                    if(loop>=stahp)return data;
                    loop++;
                }
            }
            return new SMS[]{};
        }
        catch(SecurityException e){return new SMS[]{new SMS(new String[]{"-1","-1","-1","-1","-1","SecurityException: "+e.getMessage(),"-1"})};}
        catch(Exception e){return new SMS[]{new SMS(new String[]{"-1","-1","-1","-1","-1","Exception: "+e.getMessage(),"-1"})};}
    }
}
