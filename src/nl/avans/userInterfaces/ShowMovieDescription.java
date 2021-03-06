package nl.avans.userInterfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import nl.avans.sql.Film;
import nl.avans.sql.Series;



public class ShowMovieDescription extends JFrame {
    private JList movieList;

    public ShowMovieDescription (JList movieList){
        this.movieList = movieList;
        setSize(400,400);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Films");
        createComponents(this.getContentPane());
    }

    private void createComponents(Container container) {
        Film film = (Film) movieList.getSelectedValue();
        movieList.setVisibleRowCount(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        movieList.setLayoutOrientation(JList.VERTICAL);
        movieList.setVisibleRowCount(2);
        container.add(movieList, BorderLayout.CENTER);
    }

}
