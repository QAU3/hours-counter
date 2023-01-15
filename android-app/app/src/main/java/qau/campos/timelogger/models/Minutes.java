package qau.campos.timelogger.models;

/**
 * Model used to serialize lodged time.
 */
public class Minutes {
    private String id;
    private int minutes;
    private String username;
    private String date;

    /**
     * Creates a new instance of Minutes model.
     */
    public Minutes(String username, String date, int minutes ) {
        this.minutes = minutes;
        this.username = username;
        this.date = date;
    }
    public Minutes(String id,String username, String date, int minutes ) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
