package nl.avans.sql;

public abstract class Program {
    private int duration;
    private int id;
    private String title;

    protected Program(int duration, int id, String title) {
        this.duration = duration;
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }
}
