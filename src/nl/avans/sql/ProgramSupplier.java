package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//This class is reasonable for fetching all information about films, episodes and series from the database
public class ProgramSupplier {
    private ArrayList<Program> programs = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private SQLConnection sqlConnection;

    public ProgramSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void makeProgramsAndSeries() throws SQLException {
        makeEpisodes();
        makeFilms();
        Collections.sort(programs);
    }

    //this function fetches all data about films from the database and makes objects out of that data
    private void makeFilms() throws SQLException {
        ResultSet films = sqlConnection.executeSql("SELECT * FROM Film JOIN Programma ON Film.Id = Programma.Id");

        while (films.next()) {
            int id = films.getInt("Id");
            String language = films.getString("Taal");
            String genre = films.getString("Genre");
            int minimumAge = films.getInt("Leeftijdsindicatie");
            String title = films.getString("Titel");
            int duration = films.getInt("Tijdsduur");
            programs.add(new Film(minimumAge, genre, language, duration, id, title));
        }
    }

    //this function fetches all data about episodes and series from the database and makes objects out of that data
    private void makeEpisodes() throws SQLException {
        ResultSet episodesAndSeries = sqlConnection.executeSql("SELECT * FROM Aflevering JOIN Programma ON Aflevering.Id = Programma.Id LEFT JOIN Serie ON Aflevering.Serie = Serie.Serie;");
        while (episodesAndSeries.next()) {
            int id = episodesAndSeries.getInt("Id");
            String title = episodesAndSeries.getString("Titel");
            String season = episodesAndSeries.getString("Seizoen");
            int duration = episodesAndSeries.getInt("Tijdsduur");
            Episode episode = new Episode(duration, id, title, season);
            programs.add(episode);

            String seriesTitle = episodesAndSeries.getString("Serie");
            String language = episodesAndSeries.getString("Taal");
            String genre = episodesAndSeries.getString("Genre");
            String ageRestriction = episodesAndSeries.getString("Leeftijd");
            String similarTo = episodesAndSeries.getString("LijktOp");
            Series series = new Series(ageRestriction, genre, language, seriesTitle, similarTo);
            if (!this.series.contains(series)) {
                series.addAflevering(episode);
                this.series.add(series);
            } else {
                int i = this.series.indexOf(series);
                this.series.get(i).addAflevering(episode);
            }
            episode.setSerie(series);
        }
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    public Series getSeriesByTitle(String title) {
        for (Series s : series) {
            if (s.getTitel().equals(title))
                return s;
        }
        return null;
    }


    public ArrayList<Film> getFilms() {
        ArrayList<Film> films = new ArrayList<>();
        for (Program p : programs) {
            if (p instanceof Film)
                films.add((Film) p);
        }
        return films;
    }

    public Program getProgramById(int watchedId) {
        Program program = null;
        for (Program p : programs) {
            if (p.getId() == watchedId) {
                program = p;
                break;
            }
        }
        return program;
    }
}
