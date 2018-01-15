package nl.avans.sql;

public class Episode extends Program {
    private Series serie;
    private String seizoen;

    public Episode(int tijdsduur, int id, String titel, String seizoen) {
        super(tijdsduur, id, titel);
        this.seizoen = seizoen;
    }

    public String getSeizoen() {
        return seizoen;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }

    public String toString() {
        return '(' + this.serie.getTitel() + ')' + this.getTitle();
    }
}
