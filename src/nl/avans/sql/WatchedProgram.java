package nl.avans.sql;

public class WatchedProgram {
    private int percentage;
    private Program program;

    public WatchedProgram(int percentage, Program program) {
        this.percentage = percentage;
        this.program = program;
    }

    public int getPercentage() {
        return percentage;
    }

    public Program getProgram() {
        return program;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WatchedProgram && percentage == ((WatchedProgram) obj).percentage && getProgram().getId() == (((WatchedProgram) obj)).getProgram().getId();
    }
}
