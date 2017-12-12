package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLHelper {

    /**
     * Print de resultset als een tabel
     *
     * @param set
     * @throws SQLException
     */
    public static void printResultSet(ResultSet set) throws SQLException {
        if (set != null) {
            ResultSetMetaData rsmd = set.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            boolean first = true;
            int rows = 0;
            while (set.next()) {
                if (first) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",");
                        System.out.print(rsmd.getColumnName(i));
                    }
                    System.out.println();
                    first = false;
                }
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",");
                    String columnValue = set.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
                rows++;
            }
            System.out.println(String.format("%s rows and %s columns", rows, columnsNumber));
        } else {
            System.out.println("0 rows and 0 columns");
        }
    }
}
