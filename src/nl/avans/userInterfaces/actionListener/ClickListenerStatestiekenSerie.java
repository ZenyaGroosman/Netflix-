package nl.avans.userInterfaces.actionListener;

import nl.avans.SelectedAccount;
import nl.avans.sql.SQLHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClickListenerStatestiekenSerie implements ActionListener {
    private JPanel panel;
    private JComboBox<String> seriesList;
    private JFrame frame;

    public ClickListenerStatestiekenSerie(JPanel panel, JComboBox seriesList, JFrame frame) {
        this.panel = panel;
        this.seriesList = seriesList;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        switch (button) {
            case "Gemiddelde kijkpercentages": {
                try {
                    panel.removeAll();
                    ResultSet set = SQLHelper.sqlConnection.executeSql("SELECT Aflevering.Seizoen, Programma.Titel, AVG(Percentage) AS 'Percentage' FROM Aflevering LEFT JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien LEFT JOIN Programma ON Programma.Id = Aflevering.Id WHERE Aflevering.Serie = '" + seriesList.getSelectedItem() + "' GROUP BY Programma.Titel, Aflevering.Seizoen ORDER BY Aflevering.Seizoen;");
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

                    ResultSet average = SQLHelper.sqlConnection.executeSql("SELECT AVG(Percentage) AS 'Percentage' FROM Aflevering JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien WHERE Aflevering.Serie = '" + seriesList.getSelectedItem() + "' GROUP BY Aflevering.Serie;");
                    strings.add("Totaal");
                    strings.add("Volledige Serie");
                    if (average != null) {
                        average.next();
                        String percentage = average.getString("Percentage");
                        strings.add((percentage != null) ? percentage + "%" : "Nog niet bekeken");

                        rows++;
                    }
                    if (SelectedAccount.getSelectedAccount() != null) {
                        ResultSet averageAccount = SQLHelper.sqlConnection.executeSql("SELECT ISNULL(AVG(Percentage), null) AS 'Percentage' FROM Aflevering LEFT JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien WHERE Aflevering.Serie = '" + seriesList.getSelectedItem() + "' AND Bekeken.Abonneenummer = " + SelectedAccount.getSelectedAccount().getId() + " GROUP BY Bekeken.Abonneenummer;");
                        strings.add("Totaal");
                        strings.add("Voor geselecteerd account");
                        if (averageAccount != null) {
                            averageAccount.next();
                            String percentage = average.getString("Percentage");
                            strings.add((percentage != null) ? percentage + "%" : "Nog niet bekeken door dit account");
                        }
                        rows++;
                    } else {
                        strings.add("Totaal");
                        strings.add("Voor geselecteerd account");
                        strings.add("Geen account geselecteerd");
                        rows++;
                    }

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
                    break;
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }

    }
}
