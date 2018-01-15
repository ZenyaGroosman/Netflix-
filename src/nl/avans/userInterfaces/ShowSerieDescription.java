package nl.avans.userInterfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import nl.avans.sql.Film;
import nl.avans.sql.Series;

public class ShowSerieDescription extends JFrame {
    private JList serieList;

    public ShowSerieDescription (JList serieList){
        setSize(400,400);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Beschrijvingen");
        createComponents(this.getContentPane());
    }

    private void createComponents(Container container) {
        Series serie = (Series) serieList.getSelectedValue();

        serieList.setVisibleRowCount(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        serieList.setLayoutOrientation(JList.VERTICAL);
        serieList.setVisibleRowCount(2);
        container.add(serieList, BorderLayout.CENTER);
    }

}
