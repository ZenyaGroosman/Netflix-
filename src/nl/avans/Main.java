package nl.avans;

import nl.avans.sql.SQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static nl.avans.sql.SQLHelper.printResultSet;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here

        SQLConnection connection = new SQLConnection();
        connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        ResultSet set = connection.executeSql("SELECT * FROM Aflevering");
        printResultSet(set);
        connection.disconnectDatabase();
    }

}