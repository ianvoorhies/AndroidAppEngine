package com.test.enginetest.util;
public final class ExecutionTime{
    private long start,stop;
    public ExecutionTime(){reset();}
    public void reset(){start=stop=0;}
    public void start(){
        reset();
        start=System.currentTimeMillis();
    }
    public void stop(){stop=System.currentTimeMillis();}
    public long milliseconds(){
        if(stop<start)return 0;
        return stop-start;
    }
    public double seconds(){return milliseconds()/1000.0;}
}
