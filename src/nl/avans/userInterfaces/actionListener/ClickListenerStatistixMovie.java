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

public class ClickListenerStatistixMovie implements ActionListener {
    private JPanel panel;
    private JComboBox<String> movieList;
    private JFrame frame;

    public ClickListenerStatistixMovie(JPanel panel, JComboBox movieList, JFrame frame) {
        this.panel = panel;
        this.movieList = movieList;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        switch (button) {
            case "Film kijkpercentages": {

            }
        }

    }
}

