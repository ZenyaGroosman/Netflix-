package nl.avans.sql;

import java.util.ArrayList;

public class Serie {
    private ArrayList<Aflevering> afleveringen;
    private String leeftijdsindicatie;
    private String genre;
    private String taal;
    private String titel;
    private String lijktOp;

    public Serie(String leeftijdsindicatie, String genre, String taal, String titel, String lijktOp) {
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

    public void addAflevering(Aflevering aflevering) {
        afleveringen.add(aflevering);
    }

    public void removeAflevering(Aflevering aflevering) {
        afleveringen.remove(aflevering);
    }

    public ArrayList<Aflevering> getAfleveringen() {
        return afleveringen;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Serie && ((Serie) obj).leeftijdsindicatie.equals(this.leeftijdsindicatie)
                && ((Serie) obj).genre.equals(this.genre)
                && ((Serie) obj).titel.equals(this.titel)
                && ((Serie) obj).taal.equals(this.taal)
                && ((Serie) obj).lijktOp.equals(this.lijktOp);
    }

    public String getLijktOp() {
        return lijktOp;
    }
}
