package nl.avans.sql;

import java.sql.Date;
import java.util.ArrayList;

public class Profile {
    private Date geboortedatum;
    private String profielnaam;

    public Profile(Date geboortedatum, String profielnaam) {
        this.geboortedatum = geboortedatum;
        this.profielnaam = profielnaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getProfielnaam() {
        return profielnaam;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Profile && ((Profile) obj).profielnaam.equals(profielnaam);
    }
}
