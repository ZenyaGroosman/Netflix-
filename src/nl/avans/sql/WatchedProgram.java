package nl.avans.sql;

public class WatchedProgram {
    private int percentage;
    private Program program;
    private Profile profile;

    public WatchedProgram(int percentage, Program program, Profile profile) {
        this.percentage = percentage;
        this.program = program;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
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