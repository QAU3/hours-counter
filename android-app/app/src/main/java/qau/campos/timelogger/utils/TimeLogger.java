package qau.campos.timelogger.utils;

import android.content.Context;
import android.os.Handler;

import qau.campos.timelogger.LoggerView;

public class TimeLogger implements  Runnable  {
    Context context;
    long startTime;
    private Handler timerHandler;

    public TimeLogger(Context context){
        this.context = context;
        this.startTime = System.currentTimeMillis();
        this.setTimerHandler(new Handler());
    }


    @Override
    public void run() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        LoggerView loggerView = (LoggerView) context;
        loggerView.onTick(minutes / 60, minutes % 60);

        getTimerHandler().postDelayed(this, 500);
    }

    public Handler getTimerHandler() {
        return timerHandler;
    }

    private void setTimerHandler(Handler timerHandler) {
        this.timerHandler = timerHandler;
    }

    public void startTimer (){
        getTimerHandler().postDelayed(this, 0);
    }
    public void pauseTimer(){
        getTimerHandler().removeCallbacks(this);
    }
}