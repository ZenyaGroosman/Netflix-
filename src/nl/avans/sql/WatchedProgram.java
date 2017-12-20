package nl.avans.sql;

public class WatchedProgram {
    private int percentage;
    private int programId;

    public WatchedProgram(int percentage, int programId) {
        this.percentage = percentage;
        this.programId = programId;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getProgramId() {
        return programId;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WatchedProgram && percentage == ((WatchedProgram) obj).percentage && programId == (((WatchedProgram) obj).programId);
    }
}
