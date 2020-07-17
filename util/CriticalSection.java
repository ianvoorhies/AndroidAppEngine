package com.test.enginetest.util;
import java.util.concurrent.Semaphore;
public final class CriticalSection{
    private CustomCallback callback;
    private Semaphore mutex;
    private String eventKey;
    public CriticalSection(CustomCallback callback,String eventKey){
        this.callback=callback;
        this.eventKey=eventKey;
        mutex=new Semaphore(1);
    }
    public boolean execute(){
        try{
            mutex.acquire();
            callback.callbackEvent(eventKey);
            mutex.release();
        }
        catch(Exception e){return false;}
        return true;
    }
}
