package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLHelper {

     public static SQLConnection sqlConnection;


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

    /**
     * Verwijderd een object uit de database
     * @param tableName de naam van de tabel waar het object in staat
     * @param keyColumns een array met de column namen van de primaryKeys
     * @param keyValues een array met de waarde van de primaryKeys in dezelfde voorgoorde als de columns array
     * @return true als geslaagd
     */
    public static boolean deleteObject(String tableName, String[] keyColumns, Object[] keyValues) {
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        stringBuilder.append(tableName).append(" WHERE ");
        for (int i = 0; i < keyColumns.length; i++) {
            stringBuilder.append(keyColumns[i]).append(" = ").append(keyValues[i]).append(" ");
        }
        return sqlConnection.executeSqlNoResult(stringBuilder.toString());
    }

    /**
     * voegt een object toe aan de database
     * @param tableName de naam van de tabel waar het object in staat
     * @param columns een array met de column namen van de tabel
     * @param values een array met de waarde in dezelfde voorgoorde als de columns array
     * @return true als geslaagd
     */
    public static boolean createObject(String tableName, String[] columns, Object[] values) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(tableName).append(" (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]).append(i == columns.length - 1 ? ", " : " ");
        }
        stringBuilder.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(values[i]).append(i == columns.length - 1 ? ", " : " ");
        }
        stringBuilder.append(")");
        return sqlConnection.executeSqlNoResult(stringBuilder.toString());
    }

}
