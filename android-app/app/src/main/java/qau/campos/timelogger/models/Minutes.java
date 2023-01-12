package qau.campos.timelogger.models;

import java.util.Date;

import qau.campos.timelogger.utils.NumericDate;

/**
 * Model used to serialize lodged time.
 */
public class Minutes {
    private int minutes;
    private String username;
    private NumericDate date;

    /**
     * Creates a new instance of Minutes model.
     */
    public Minutes(String username, NumericDate date, int minutes ) {
        this.minutes = minutes;
        this.username = username;
        this.date = date;
    }

    // Getters and setters
    public int getMinutes() {
        return minutes;
    }

    private void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public NumericDate getDate() {
        return date;
    }

    private void setDate(NumericDate date) {
        this.date = date;
    }
}
