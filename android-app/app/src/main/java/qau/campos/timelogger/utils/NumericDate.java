package qau.campos.timelogger.utils;

public class NumericDate {
    private int year;
    private int month;
    private int day;

    public NumericDate(int year, int month, int day){
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
