package nl.avans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
//        boolean set = connection.executeSqlNoResult("DROP TABLE Persons"); test code
//        System.out.println(set);
    }
}