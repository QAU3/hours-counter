package qau.campos.timelogger.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    public static  String getNumericDateFromISO(String iso){
        final Calendar c = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()) ;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ", Locale.getDefault());
        NumericDate numericDate;
        try {
            Date date = dateformat.parse(iso);
            c.setTime(date);
            return new NumericDate(c.get(Calendar.YEAR) ,c.get(Calendar.YEAR), c.get(Calendar.DAY_OF_MONTH)).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "error converting";
    }
}
