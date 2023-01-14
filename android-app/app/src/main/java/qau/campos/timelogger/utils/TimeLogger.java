package qau.campos.timelogger.utils;

import android.content.Context;
import android.os.Handler;

import qau.campos.timelogger.LoggerView;

public class TimeLooger implements  Runnable  {
    Context context;
    long startTime;
    Handler timerHandler;

    public TimeLooger(Context context){
        this.context = context;
        this.startTime = System.currentTimeMillis();
        this.timerHandler = new Handler();
    }


    @Override
    public void run() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        LoggerView loggerView = (LoggerView) context;
        loggerView.onTick(minutes / 60, minutes % 60);

        timerHandler.postDelayed(this, 500);
    }
}