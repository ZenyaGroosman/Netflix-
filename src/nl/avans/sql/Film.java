package nl.avans.sql;

public class Film  extends Programma{
    private String leeftijdsindicatie;
    private String genre;
    private String taal;

    public Film(String leeftijdsindicatie, String genre, String taal, int tijdsduur, int id, String titel) {
        super(tijdsduur, id, titel);
        this.leeftijdsindicatie = leeftijdsindicatie;
        this.genre = genre;
        this.taal = taal;
    }

    public String getLeeftijdsindicatie() {
        return leeftijdsindicatie;
    }

    public String getGenre() {
        return genre;
    }

    public String getTaal() {
        return taal;
    }
}
