package qau.campos.timelogger.models;

public class AggregatedTime {

    private int id;
    private int minutes;

    public AggregatedTime(int id, int minutes) {
        this.setId(id);
        this.setMinutes(minutes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
