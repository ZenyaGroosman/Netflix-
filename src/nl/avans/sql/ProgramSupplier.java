package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProgramSupplier {
    private ArrayList<Program> programs = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private SQLConnection sqlConnection;

    public ProgramSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void refreshPrograms() throws SQLException {
        programs.clear();
        series.clear();
        makePrograms();
    }

    public void makePrograms() throws SQLException {
        makeEpisodes();
        makeFilms();
    }

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

    /**
     * Geeft de serie terug als de titel matcht
     *
     * @param title de titel
     * @return de serie
     */
    public Series getSeriesByTitle(String title) {
        for (Series s : series) {
            if (s.getTitel().equals(title))
                return s;
        }
        return null;
    }

    /**
     * voegd een nieuwe serie toe aan de database
     *
     * @param series de nieuwe serie
     * @return of het gelukt is
     */
    public boolean createSeries(Series series) {
        String[] columns = new String[]{"Serie", "Taal", "Genre", "Leeftijd", "LijktOp"};
        String[] value = new String[]{series.getTitel(), series.getTaal(), series.getGenre(), series.getLeeftijdsindicatie(), series.getLijktOp()};
        boolean success = SQLHelper.createObject("Serie", columns, value);

        if (success) {
            this.series.add(series);
        }
        return success;
    }

    /**
     * verwijderd een series uit de databas en uit de supplier
     *
     * @param series the series to delete
     * @return als sucessvol
     */
    public boolean deleteSeries(Series series) {
        String[] columns = new String[]{"Serie"};
        String[] value = new String[]{series.getTitel()};
        boolean success = SQLHelper.deleteObject("Serie", columns, value);
        if (success) {
            this.series.remove(series);
            Iterator<Program> iterator = programs.iterator();
            while (iterator.hasNext()) {
                Program programma = iterator.next();
                if (programma instanceof Episode && ((Episode) programma).getSerie().equals(series)) {
                    programs.remove(programma);
                }
            }
        }

        return success;
    }


    public boolean createEpisode(Episode episode) {
        String[] columnsProgramma = new String[]{"Id", "Titel", "Tijdsduur"};
        Object[] valueProgramma = new Object[]{episode.getId(), episode.getTitle(), episode.getDuration()};
        String[] columnsAflevering = new String[]{"Id", "Serie", "Seizoen"};
        Object[] valueAflevering = new Object[]{episode.getId(), episode.getSerie().getTitel(), episode.getSeizoen()};
        boolean succes = SQLHelper.createObject("Programma", columnsProgramma, valueProgramma);
        if (succes) {
            succes = SQLHelper.createObject("Aflevering", columnsAflevering, valueAflevering);
        }
        return succes;
    }

    public boolean deleteEpisode(Episode episode) {
        String[] columns = new String[]{"Id"};
        Object[] values = new Object[]{episode.getId()};
        boolean success = SQLHelper.deleteObject("Programma", columns, values);
        if (success) {
            success = SQLHelper.deleteObject("Aflevering", columns, values);
        }
        if (success) {
            episode.getSerie().removeAflevering(episode);
        }
        programs.remove(episode);
        return success;
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
