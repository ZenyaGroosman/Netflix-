package nl.avans.sql;

import java.util.ArrayList;

public class Series {
    private ArrayList<Episode> afleveringen;
    private String leeftijdsindicatie;
    private String genre;
    private String taal;
    private String titel;
    private String lijktOp;

    public Series(String leeftijdsindicatie, String genre, String taal, String titel, String lijktOp) {
        this.leeftijdsindicatie = leeftijdsindicatie;
        this.genre = genre;
        this.taal = taal;
        this.titel = titel;
        this.lijktOp = lijktOp;
        this.afleveringen = new ArrayList<>();
    }

    public String getTitel() {
        return titel;
    }

    public String getTaal() {
        return taal;
    }

    public String getGenre() {
        return genre;
    }

    public String getLeeftijdsindicatie() {
        return leeftijdsindicatie;
    }

    public void addAflevering(Episode aflevering) {
        afleveringen.add(aflevering);
    }

    public void removeAflevering(Episode aflevering) {
        afleveringen.remove(aflevering);
    }

    public ArrayList<Episode> getAfleveringen() {
        return afleveringen;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Series && ((Series) obj).leeftijdsindicatie.equals(this.leeftijdsindicatie)
                && ((Series) obj).genre.equals(this.genre)
                && ((Series) obj).titel.equals(this.titel)
                && ((Series) obj).taal.equals(this.taal)
                && ((Series) obj).lijktOp.equals(this.lijktOp);
    }

    public String getLijktOp() {
        return lijktOp;
    }
}
