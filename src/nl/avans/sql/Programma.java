package nl.avans.sql;

public abstract class Programma {
    private int tijdsduur;
    private int id;
    private String titel;

    protected Programma(int tijdsduur, int id, String titel) {
        this.tijdsduur = tijdsduur;
        this.id = id;
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public int getTijdsduur() {
        return tijdsduur;
    }

    public int getId() {
        return id;
    }
}
