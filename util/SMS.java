package com.test.enginetest.util;
import java.text.SimpleDateFormat;
import java.util.Locale;
public class SMS{
    private final int ID=0;
    private final int THREAD=1;
    private final int NUMBA=2;
    private final int NAME=3;
    private final int DATE=4;
    private final int MSG=5;
    private final int TYPE=6;
    private String[]data;
    public SMS(String[]data){this.data=data;}
    public boolean sent(){return getType().equals("2");}
    public boolean received(){return getType().equals("1");}
    private boolean IDError(){return data[ID]==null;}
    public boolean IDUnavailable(){return(IDError()?true:(data[ID].equals("")?true:false));}
    public String getID(){return(IDUnavailable()?"N/A":data[ID]);}
    private boolean threadIDError(){return data[THREAD]==null;}
    public boolean threadIDUnavailable(){return(threadIDError()?true:(data[THREAD].equals("")?true:false));}
    public String getThreadID(){return(threadIDUnavailable()?"N/A":data[THREAD]);}
    private boolean phoneNumberError(){return data[NUMBA]==null;}
    public boolean phoneNumberUnavailable(){return(phoneNumberError()?true:(data[NUMBA].equals("")?true:false));}
    public String getPhoneNumber(){return(phoneNumberUnavailable()?"N/A":data[NUMBA]);}
    private boolean contactNameError(){return data[NAME]==null;}
    public boolean contactNameUnavailable(){return(contactNameError()?true:(data[NAME].equals("")?true:false));}
    public String getContactName(){return(contactNameUnavailable()?"N/A":data[NAME]);}
    private boolean dateError(){return data[DATE]==null;}
    public boolean dateUnavailable(){return(dateError()?true:(data[DATE].equals("")?true:false));}
    public String getRawDate(){return(dateUnavailable()?"N/A":data[DATE]);}
    public String getFormattedDate(){//todo
        if(getRawDate().equals("N/A"))return getRawDate();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",Locale.getDefault());
        try{return simpleDateFormat.parse(getRawDate()).toString();}
        catch(Exception e){return getRawDate();}
    }
    private boolean messageError(){return data[MSG]==null;}
    public boolean messageUnavailable(){return(messageError()?true:(data[MSG].equals("")?true:false));}
    public String getMessage(){return(messageUnavailable()?"N/A":data[MSG]);}
    private boolean typeError(){return data[TYPE]==null;}
    public boolean typeUnavailable(){return(typeError()?true:(data[TYPE].equals("")?true:false));}
    public String getType(){return(typeUnavailable()?"N/A":data[TYPE]);}
    @Override public String toString(){return(sent()?"SENT":"RECEIVED")+": number["+getPhoneNumber()+"] contact["+getContactName()+"] msg["+getMessage()+"] date["+getFormattedDate()+"] threadID["+getThreadID()+"] msgID["+getID()+"]";}
}
