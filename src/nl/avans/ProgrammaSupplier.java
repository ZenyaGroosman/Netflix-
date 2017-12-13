package nl.avans;

import nl.avans.sql.SQLConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgrammaSupplier {
    private ArrayList<Programma> programmas = new ArrayList<>();
    private SQLConnection sqlConnection;

    public ProgrammaSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void makeProgrammas() throws SQLException{
        makeAfleveringen();
        makeFilms();
    }

    private void makeFilms() throws SQLException {
        ResultSet films = sqlConnection.executeSql("SELECT * FROM Film JOIN Programma ON Film.Id = Programma.Id");
        ResultSetMetaData rsmd = films.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (films.next()) {
//            String columnValue = films.getString();
//            System.out.print(columnValue);

        }
    }

    private void makeAfleveringen() {

    }

}
