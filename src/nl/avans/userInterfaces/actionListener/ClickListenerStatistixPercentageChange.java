package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.WatchedProgram;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClickListenerStatistixPercentageChange implements ActionListener, ChangeListener {
    private JComboBox<String> limit;
    private JSlider slider;
    private JFrame frame;
    private JPanel panel;
    private JComboBox seriesList;

    public ClickListenerStatistixPercentageChange(JComboBox<String> limit, JSlider slider, JFrame frame, JPanel panel, JComboBox seriesList) {
        this.limit = limit;
        this.slider = slider;
        this.frame = frame;
        this.panel = panel;
        this.seriesList = seriesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addTable();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        addTable();
    }

    private void addTable() {
        panel.removeAll();
        int percentage = slider.getValue();
        String minOrMax = limit.getSelectedItem().toString();
        String movie = seriesList.getSelectedItem().toString();

        ArrayList<WatchedProgram> watchedProgramsFitToPercentage = new ArrayList<>();

        for (WatchedProgram watchedProgram : Main.accountSupplier.getWatchedPrograms()) {
            if (watchedProgram.getProgram().getTitle().equals(movie) && ((minOrMax.equals("Minimaal") && watchedProgram.getPercentage() >= percentage) || (minOrMax.equals("Maximaal") && watchedProgram.getPercentage() <= percentage))) {
                watchedProgramsFitToPercentage.add(watchedProgram);
            }
        }

        int finalRows = watchedProgramsFitToPercentage.size() + 1;
        String[][] strings2d = new String[finalRows][2];
        int i = 0;
        for (WatchedProgram watchedProgram : watchedProgramsFitToPercentage) {
            strings2d[i][0] = watchedProgram.getProfile().getProfielnaam();
            strings2d[i][1] = "" + watchedProgram.getPercentage() + "%";

            i++;
        }
        strings2d[finalRows - 1][0] = "Totaal";
        strings2d[finalRows - 1][1] = "" + (finalRows - 1);

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 2;
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
                        return "Naam";
                    case 1:
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
    }
}
