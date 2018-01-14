package nl.avans.userInterfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import nl.avans.sql.Film;

public class ShowMovieDescription extends JFrame {
    private JList movieList;


    public ShowMovieDescription(JList movieList){
        this.movieList = movieList;

        setSize(400,400);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Beschrijvingen");


    }
    public void createComponents(Container container){
        Film film = (Film) movieList.getSelectedValue();

        movieList.setVisibleRowCount(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        movieList.setLayoutOrientation(JList.VERTICAL);
        movieList.setVisibleRowCount(2);
        container.add(movieList, BorderLayout.CENTER);
        }




//        film.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        filmList.setLayoutOrientation(JList.VERTICAL);
//        filmList.setVisibleRowCount(2);
//        container.add(filmList, BorderLayout.CENTER);

    }