package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Account;
import nl.avans.sql.SQLHelper;
import nl.avans.sql.Series;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActionListenerStatistixSerie implements ActionListener {
    private JPanel panel;
    private JComboBox<String> seriesList;
    private JComboBox accountList;
    private JFrame frame;

    public ActionListenerStatistixSerie(JPanel panel, JComboBox seriesList, JComboBox accountList, JFrame frame) {
        this.panel = panel;
        this.seriesList = seriesList;
        this.accountList = accountList;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = (Account) accountList.getSelectedItem();
        String accountQuerry = "";
        //checks if all accounts should be checked or only one
        if (!account.getNaam().equals("Alle accounts")){
            accountQuerry = "AND Bekeken.Abonneenummer = '" + account.getId() + "' ";
        }

        try {
            panel.removeAll();

            //sql statement to get the average watch time per episode, for either all accounts or one account
            ResultSet set = SQLHelper.sqlConnection.executeSql("SELECT Aflevering.Seizoen, Programma.Titel, AVG(Percentage) AS 'Percentage' FROM Aflevering LEFT JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien " + accountQuerry + " LEFT JOIN Programma ON Programma.Id = Aflevering.Id WHERE Aflevering.Serie = '" + seriesList.getSelectedItem() + "' GROUP BY Programma.Titel, Aflevering.Seizoen ORDER BY Aflevering.Seizoen;");
            int rows = 0;
            ArrayList<String> strings = new ArrayList<>();
            if (set != null) {
                while (set.next()) {
                    rows++;
                    String percentage = set.getString("Percentage");
                    strings.add(set.getString("Seizoen"));
                    strings.add(set.getString("Titel"));
                    strings.add((percentage != null) ? percentage + "%" : "Nog niet bekeken");
                }
            }

            //sql statement to get the average watch time for the series overall, for either all accoutns or one account
            ResultSet average = SQLHelper.sqlConnection.executeSql("SELECT AVG(Percentage) AS 'Percentage' FROM Aflevering JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien " + accountQuerry + " WHERE Aflevering.Serie = '" + seriesList.getSelectedItem() + "' GROUP BY Aflevering.Serie;");

            strings.add("Totaal");
            strings.add("Volledige Serie");
            if (average != null && average.next()) {
                String percentage = average.getString("Percentage");
                strings.add((percentage != null) ? percentage + "%" : "Nog niet bekeken");

                rows++;
            } else {
                strings.add("Nog niet bekeken");
                rows++;
            }

            //turns the array into a a 2d array
            int finalRows = rows;
            String[][] strings2d = new String[rows][3];
            int r = 0;
            int c = 0;
            for (String string : strings) {
                strings2d[r][c] = string;
                if (c % 3 == 2) {
                    r++;
                    c = -1;
                }
                c++;
            }

            //models the data for the table
            TableModel dataModel = new AbstractTableModel() {
                public int getColumnCount() {
                    return 3;
                }

                public int getRowCount() {
                    return finalRows;
                }

                public Object getValueAt(int row, int col) {
                    return strings2d[row][col];
                }

                @Override
                public String getColumnName(int column) {
                    switch (column) {
                        case 0:
                            return "Seizoen";
                        case 1:
                            return "Titel";
                        case 2:
                            return "Kijkpercentage";
                    }
                    return super.getColumnName(column);
                }


            };
            JTable table = new JTable(dataModel);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            frame.repaint();
            frame.revalidate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

    }
}
