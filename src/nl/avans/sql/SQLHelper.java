package nl.avans.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLHelper {

     public static SQLConnection sqlConnection;

    /**
     * deletes an object form the database
     * @param tableName the name of the table
     * @param keyColumns an array with the column names of the primary keys
     * @param keyValues an array containing the values of the primary keys in the same order as the column
     * @return true succesfull
     */
    public static boolean deleteObject(String tableName, String[] keyColumns, Object[] keyValues) {
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        stringBuilder.append(tableName).append(" WHERE ");
        for (int i = 0; i < keyColumns.length; i++) {
            stringBuilder.append(keyColumns[i]).append(" = ").append(keyValues[i]).append(" ").append(i != keyColumns.length - 1 ? " " : " AND ");
        }
        return sqlConnection.executeSqlNoResult(stringBuilder.toString());
    }

    /**
     * deletes an object form the database
     * @param tableName the name of the table
     * @param columns an array with the column names
     * @param values an array containing the values of the columns in the same order as the columns
     * @return true succesfull
     */
    public static boolean createObject(String tableName, String[] columns, Object[] values) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(tableName).append(" (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]).append(i != columns.length - 1 ? ", " : " ");
        }
        stringBuilder.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(values[i]).append(i != columns.length - 1 ? ", " : " ");
        }
        stringBuilder.append(")");
        return sqlConnection.executeSqlNoResult(stringBuilder.toString());
    }

    /**
     * deletes an object form the database
     * @param tableName the name of the table
     * @param columns an array with the column names
     * @param oldValues an array containing the old values of the columns in the same order as the columns
     * @param newValues an array containing the new values of the columns in the same order as the columns
     * @return true succesful
     */
    public static boolean editObject(String tableName, String[] columns, Object[] oldValues, Object[] newValues) {
        StringBuilder stringBuilder = new StringBuilder("UPDATE ");
        stringBuilder.append(tableName).append(" (");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]).append(" = ").append(newValues[i]).append(" ");
        }
        stringBuilder.append(") WHERE ");
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]).append(" = ").append(oldValues[i]).append(" ").append(i != columns.length - 1 ? " " : " AND ");
        }
        stringBuilder.append("");
        return sqlConnection.executeSqlNoResult(stringBuilder.toString());
    }

}
