package qau.campos.timelogger.utils;

import java.util.Calendar;

import qau.campos.timelogger.models.NumericDate;

public class DateFormatHelper {
    public static NumericDate getNumericDate(){
        final Calendar c = Calendar.getInstance();
        NumericDate date = new NumericDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return date;
    }
    public static String getTimeStampFromNumericDate(NumericDate date){
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, date.getYear());
        c.set(Calendar.MONTH, date.getMonth());
        c.set(Calendar.DAY_OF_MONTH, date.getDay());
        return c.getTime().toString();
    }
}
