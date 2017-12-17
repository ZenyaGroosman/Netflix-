package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProgrammaSupplier {
    private ArrayList<Programma> programmas = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    private SQLConnection sqlConnection;

    public ProgrammaSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void refreshProgrammas() throws SQLException {
        programmas.clear();
        series.clear();
        makeProgrammas();
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
            aflevering.setSerie(serie);
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

    /**
     * voegd een nieuwe serie toe aan de database
     * @param serie de nieuwe serie
     * @return of het gelukt is
     */
    public boolean createSerie(Serie serie){
        String[] columns = new String[]{"Serie", "Taal", "Genre", "Leeftijd", "LijktOp"};
        String[] value = new String[]{serie.getTitel(), serie.getTaal(), serie.getGenre(), serie.getLeeftijdsindicatie(), serie.getLijktOp()};
        boolean success = SQLHelper.createObject("Serie", columns, value);

        if(success){
            series.add(serie);
        }
        return success;
    }

    /**
     * verwijderd een serie uit de databas en uit de supplier
     * @param serie the serie to delete
     * @return als sucessvol
     */
    public boolean deleteSerie(Serie serie){
        String[] columns = new String[]{"Serie"};
        String[] value = new String[]{serie.getTitel()};
        boolean success = SQLHelper.deleteObject("Serie", columns, value);
        if (success){
            series.remove(serie);
            Iterator<Programma> iterator = programmas.iterator();
            while (iterator.hasNext()){
                Programma programma = iterator.next();
                if (programma instanceof Aflevering && ((Aflevering) programma).getSerie().equals(serie)){
                    programmas.remove(programma);
                }
            }
        }

        return success;
    }


    public boolean createAflevering(Aflevering aflevering){
        String[] columnsProgramma = new String[]{"Id", "Titel",  "Tijdsduur"};
        Object[] valueProgramma = new Object[]{aflevering.getId(), aflevering.getTitel(),aflevering.getTijdsduur()};
        String[] columnsAflevering = new String[]{"Id", "Serie","Seizoen"};
        Object[] valueAflevering = new Object[]{aflevering.getId(), aflevering.getSerie().getTitel(), aflevering.getSeizoen()};
        boolean succes = SQLHelper.createObject("Programma", columnsProgramma, valueProgramma);
        if (succes){
            succes = SQLHelper.createObject("Aflevering", columnsAflevering, valueAflevering);
        }
        return succes;
    }

    public boolean deleteAflevering(Aflevering aflevering){
        String[] columns = new String[]{"Id"};
        Object[] values = new Object[]{aflevering.getId()};
        boolean success = SQLHelper.deleteObject("Programma", columns, values);
        if (success){
            success = SQLHelper.deleteObject("Aflevering", columns, values);
        }
        if (success){
            aflevering.getSerie().removeAflevering(aflevering);
        }
        return success;
    }
}
