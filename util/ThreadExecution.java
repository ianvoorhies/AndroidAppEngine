package com.test.enginetest.util;
public final class ThreadExecution{
    private CustomCallback callback;
    private UpdateTimer updateTimer;
    private String key;
    private int speed;
    public ThreadExecution(CustomCallback callback,String eventKey,int speed){
        this.callback=callback;
        key=eventKey;
        this.speed=speed;
        updateTimer=new UpdateTimer();
        updateTimer.start();
    }
    public void setCallback(CustomCallback value){callback=value;}
    public void setEventKey(String value){key=value;}
    public void setSpeed(int value){speed=value;}
    public CustomCallback getCallback(){return callback;}
    public String getEventKey(){return key;}
    public int getSpeed(){return speed;}
    public void pause(){updateTimer.setPaused(true);}
    public void resume(){updateTimer.setPaused(false);}
    public boolean isPaused(){return updateTimer.isPaused();}
    public boolean isRunning(){return updateTimer.isRunning();}
    public void kill(){kill(false);}
    public void kill(boolean retry){
        if(retry){
            boolean attempt=true;
            while(attempt){
                try{
                    updateTimer.setRunning(false);
                    updateTimer.join();
                    attempt=false;
                }
                catch(Exception e){}
            }
        }
        else{
            try{
                updateTimer.setRunning(false);
                updateTimer.join();
            }
            catch(Exception e){}
        }
    }
    private class UpdateTimer extends Thread{
        private boolean running=true;
        private boolean paused=false;
        private long beginTime,timeDifference,sleepTime;
        @Override public void run(){
            while(running){
                if(!paused){
                    beginTime=System.currentTimeMillis();
                    callback.callbackEvent(key);
                    timeDifference=System.currentTimeMillis()-beginTime;
                    sleepTime=speed-timeDifference;
                    if(sleepTime>0){
                        try{Thread.sleep(sleepTime);}
                        catch(InterruptedException e){}
                    }
                }
            }
        }
        private void setPaused(boolean value){paused=value;}
        public boolean isPaused(){return paused;}
        public void setRunning(boolean value){running=value;}
        public boolean isRunning(){return running;}
    }
}
