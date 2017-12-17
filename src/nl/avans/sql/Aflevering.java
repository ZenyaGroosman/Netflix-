package nl.avans.sql;

public class Aflevering extends Programma {
    private Serie serie;
    private String seizoen;
    public Aflevering(int tijdsduur, int id, String titel, String seizoen) {
        super(tijdsduur, id, titel);
        this.seizoen = seizoen;
    }

    public String getSeizoen() {
        return seizoen;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Aflevering && ((Aflevering) obj).getId() == this.getId();
    }
}
