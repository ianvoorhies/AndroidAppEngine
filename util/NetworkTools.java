package com.test.enginetest.util;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
//Required permission: <uses-permission android:name="android.permission.INTERNET"/>
//Required permission: <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
public final class NetworkTools{//todo
    private Context context;
    private CustomCallback callback;
    private String eventKey;
    private boolean working=false;
    public NetworkTools(Context context){this.context=context;}
    public void launchBrowser(String url){
        if(!url.startsWith("http://")&&!url.startsWith("https://"))url="http://"+url;
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        context.startActivity(intent);
    }
    public void accessURL(String url,CustomCallback callback,String eventKey){
        if(!NetworkTools.deviceOnline(context)||working)callback.dataCallbackEvent(eventKey,"error");
        this.callback=callback;
        this.eventKey=eventKey;
        working=true;
        new ReadTextFile().execute(url);
    }
    private void networkDone(String data){
        callback.dataCallbackEvent(eventKey,data);
        working=false;
    }
    class ReadTextFile extends AsyncTask<String,Integer,String>{
        String result="";
        public ReadTextFile(){}
        @Override protected String doInBackground(String... address){
            try{
                URL url=new URL(address[0]);
                BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while((line=in.readLine())!=null)result+=line;
                in.close();
            }
            catch(Exception e){}
            return result;
        }
        @Override protected void onPostExecute(String result){networkDone(result);}
    }
    @SuppressLint("MissingPermission")
    public boolean deviceOnline(){
        if(context==null)return false;
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null&&cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public static void exitIfOffline(Context context){if(!deviceOnline(context))System.exit(-1);}
    @SuppressLint("MissingPermission")
    public static boolean deviceOnline(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null&&cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
