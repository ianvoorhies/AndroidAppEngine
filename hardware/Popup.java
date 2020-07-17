package com.test.enginetest.hardware;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import com.test.enginetest.util.NetworkTools;
public final class Popup{
    private Context context;
    public Popup(Context context){this.context=context;}
    public void toast(String message){Toast.makeText(context,message,Toast.LENGTH_SHORT).show();}
    public void longToast(String message){Toast.makeText(context,message,Toast.LENGTH_LONG).show();}
    public void showWindow(String title,String message){new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog,int which){}}).show();}
    public void askUserToRateApp(String title,String message,final String url){
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                new NetworkTools(context).launchBrowser(url);
            }
        }).setNeutralButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){}
        }).show();
    }
}
