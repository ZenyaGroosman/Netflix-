package nl.avans.sql;

import java.sql.Date;
import java.util.ArrayList;

public class Profile {
    private Date geboortedatum;
    private String profielnaam;
    private ArrayList<WatchedProgram> watchedPrograms;

    public Profile(Date geboortedatum, String profielnaam) {
        this.geboortedatum = geboortedatum;
        this.profielnaam = profielnaam;
        watchedPrograms = new ArrayList<>();
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getProfielnaam() {
        return profielnaam;
    }

    public void addProgram(WatchedProgram watchedProgram){
        watchedPrograms.add(watchedProgram);
    }
    public void removeWatchedProgram(WatchedProgram watchedProgram){
        watchedPrograms.remove(watchedProgram);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Profile && ((Profile) obj).profielnaam.equals(profielnaam);
    }
}
