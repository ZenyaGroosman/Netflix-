package nl.avans.sql;

public abstract class Program implements Comparable<Program> {
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Program && ((Program) obj).id == id;
    }

    @Override
    public int compareTo(Program o) {
        return this.toString().compareTo(o.toString());
    }
}
