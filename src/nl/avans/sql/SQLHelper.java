package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLHelper {

     public static SQLConnection sqlConnection;

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
