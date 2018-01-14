package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.Film;
import nl.avans.sql.Program;
import nl.avans.sql.WatchedProgram;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionListenerStatistixMovieAge implements ActionListener {

    private final JFormattedTextField limit;
    private final JPanel panel;
    private final JFrame frame;

    public ActionListenerStatistixMovieAge(JFormattedTextField limit, JPanel panel, JFrame frame){

        this.limit = limit;
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        int age = (int)limit.getValue();

        //goes through all the movies to see if they fit the age limit
        ArrayList<Film> moviesFitToAge = new ArrayList<>();

        for (Film movie : Main.programSupplier.getFilms()) {
            if (movie.getMinimumAge() <= age){
                moviesFitToAge.add(movie);
            }
        }

        //maps the data into a 2d array
        int finalRows = moviesFitToAge.size();
        String[][] strings2d = new String[finalRows][2];
        int i = 0;
        for (Film movie : moviesFitToAge) {
            strings2d[i][0] = movie.getTitle();
            strings2d[i][1] = movie.getMinimumAge() + " jaar en ouder";

            i++;
        }

        //models the data into a table
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
                        return "Film";
                    case 1:
                        return "Leeftijds Limiet";
                }
                return super.getColumnName(column);
            }


        };
        JTable table = new JTable(dataModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        panel.add(scrollPane);
        frame.repaint();
        frame.revalidate();

    }
}
