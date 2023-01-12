package qau.campos.timelogger.utils;

import java.util.Calendar;

public class DateFormatHelper {
    public static NumericDate getNumericDate(){
        final Calendar c = Calendar.getInstance();
        NumericDate date = new NumericDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        return date;
    }
}
