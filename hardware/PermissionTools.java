package com.test.enginetest.hardware;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class PermissionTools{
    public static final String ROOT="android.permission";
    public static final String READ_SMS=ROOT+".READ_SMS";
    public static final String RECEIVE_BOOT_COMPLETED=ROOT+".RECEIVE_BOOT_COMPLETED";
    public static final String INTERNET=ROOT+".INTERNET";
    public static final String ACCESS_NETWORK_STATE=ROOT+".ACCESS_NETWORK_STATE";
    public static final String ACCESS_FINE_LOCATION=ROOT+".ACCESS_FINE_LOCATION";
    public static final String ACCESS_COARSE_LOCATION=ROOT+".ACCESS_COARSE_LOCATION";
    public static final String WRITE_EXTERNAL_STORAGE=ROOT+".WRITE_EXTERNAL_STORAGE";
    private Context context;
    public PermissionTools(Context context){this.context=context;}
    public boolean hasPermission(String permissionID){return ContextCompat.checkSelfPermission(context,permissionID)==PackageManager.PERMISSION_GRANTED;}
    public void getPermission(String permissionID){
        ActivityCompat.requestPermissions((Activity)context,new String[]{permissionID},1);}
}
