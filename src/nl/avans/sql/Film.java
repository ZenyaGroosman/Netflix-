package nl.avans.sql;

public class Film extends Program {
    private int minimumAge; //minimum leeftijd
    private String genre;
    private String language;

    public Film(int minimumAge, String genre, String language, int duration, int id, String title) {
        super(duration, id, title);
        this.minimumAge = minimumAge;
        this.genre = genre;
        this.language = language;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String toString () { return this.getTitle(); }

}
