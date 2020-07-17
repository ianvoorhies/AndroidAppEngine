package com.test.enginetest.util;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
public final class Encryption{//todo
    public static String encryptBase64SHA512(String value,String salt){
        byte[]bytes=encryptSHA512(value,salt);
        if(bytes==null)return"error";
        return Base64.encodeToString(bytes,Base64.NO_WRAP);
    }
    private static byte[]encryptSHA512(String value,String salt){
        try{
            MessageDigest digest=MessageDigest.getInstance("SHA-512");
            return digest.digest((salt+value).getBytes());
        }
        catch(Exception e){return null;}
    }
    public static String encryptAES(String value,String key){return encrypt(value,key,"AES");}
    public static String decryptAES(String value,String key){return decrypt(value,key,"AES");}
    private static String encrypt(String value,String key,String algorithm){
        try{
            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec=new PBEKeySpec(key.toCharArray(),key.getBytes(),128,256);
            SecretKey temp=secretKeyFactory.generateSecret(keySpec);
            SecretKey secretKey=new SecretKeySpec(temp.getEncoded(),algorithm);
            Cipher cipher=Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            return bytesToHex(cipher.doFinal(value.getBytes()));
        }
        catch(Exception e){return"error";}
    }
    private static String decrypt(String value,String key,String algorithm){
        try{
            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec=new PBEKeySpec(key.toCharArray(),key.getBytes(),128,256);
            SecretKey temp=secretKeyFactory.generateSecret(keySpec);
            SecretKey secretKey=new SecretKeySpec(temp.getEncoded(),algorithm);
            Cipher cipher=Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            return new String(cipher.doFinal(stringToBytes(value)));
        }
        catch(Exception e){return"error";}
    }
    private static byte[]stringToBytes(String value){
        byte[]bytes=new byte[value.length()/2];
        for(int i=0;i<bytes.length;i++)bytes[i]=Integer.valueOf(value.substring(2*i,2*i+2),16).byteValue();
        return bytes;
    }
    private static String bytesToHex(byte[]value){
        StringBuffer stringBuffer=new StringBuffer(2*value.length);
        for(int i=0;i<value.length;i++)stringBuffer.append("0123456789ABCDEF".charAt((value[i]>>4)&0x0f)).append("0123456789ABCDEF".charAt(value[i]&0x0f));
        return stringBuffer.toString();
    }
}
