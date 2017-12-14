package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgrammaSupplier {
    private ArrayList<Programma> programmas = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    private SQLConnection sqlConnection;

    public ProgrammaSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void makeProgrammas() throws SQLException {
        makeAfleveringen();
        makeFilms();
    }

    private void makeFilms() throws SQLException {
        ResultSet films = sqlConnection.executeSql("SELECT * FROM Film JOIN Programma ON Film.Id = Programma.Id");

        while (films.next()) {
            int id = films.getInt("Id");
            String taal = films.getString("Taal");
            String genre = films.getString("Genre");
            String leeftijdsindicatie = films.getString("Leeftijdsindicatie");
            String titel = films.getString("Titel");
            int tijdsduur = films.getInt("Tijdsduur");
            programmas.add(new Film(leeftijdsindicatie, genre, taal, tijdsduur, id, titel));
        }
    }

    private void makeAfleveringen() throws SQLException {
        ResultSet afleveringenEnSeries = sqlConnection.executeSql("SELECT * FROM Aflevering JOIN Programma ON Aflevering.Id = Programma.Id LEFT JOIN Serie ON Aflevering.Serie = Serie.Serie;");
        while (afleveringenEnSeries.next()) {
            int id = afleveringenEnSeries.getInt("Id");
            String titel = afleveringenEnSeries.getString("Titel");
            String seizoen = afleveringenEnSeries.getString("Seizoen");
            int tijdsduur = afleveringenEnSeries.getInt("Tijdsduur");
            Aflevering aflevering = new Aflevering(tijdsduur, id, titel, seizoen);
            programmas.add(aflevering);

            String serieTitel = afleveringenEnSeries.getString("Serie");
            String taal = afleveringenEnSeries.getString("Taal");
            String genre = afleveringenEnSeries.getString("Genre");
            String leeftijdsindicatie = afleveringenEnSeries.getString("Leeftijd");
            String lijktOp = afleveringenEnSeries.getString("LijktOp");
            Serie serie = new Serie(leeftijdsindicatie, genre, taal, serieTitel, lijktOp);
            if (!series.contains(serie)) {
                serie.addAflevering(aflevering);
                series.add(serie);
            } else {
                int i = series.indexOf(serie);
                series.get(i).addAflevering(aflevering);
            }
        }
    }

    public ArrayList<Programma> getProgrammas() {
        return programmas;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    /**
     * Geeft de serie terug als de titel matcht
     * @param title de titel
     * @return de serie
     */
    public Serie getSerieByTitel(String title){
        for (Serie s: series){
            if (s.getTitel().equals(title))
                return s;
        }
        return null;
    }
}
