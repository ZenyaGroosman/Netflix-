package nl.avans.sql;

import java.sql.Date;

public class Profiel {
    private Date geboortedatum;
    private String profielnaam;

    public Profiel(Date geboortedatum, String profielnaam) {
        this.geboortedatum = geboortedatum;
        this.profielnaam = profielnaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getProfielnaam() {
        return profielnaam;
    }
}
