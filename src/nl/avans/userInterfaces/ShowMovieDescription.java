package nl.avans.userInterfaces;

import java.awt.*;
import javax.swing.*;
import nl.avans.Main;
import nl.avans.sql.Film;
import nl.avans.sql.Series;
import sun.rmi.server.Activation$ActivationSystemImpl_Stub;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class ShowMovieDescription extends JFrame {
    public ShowMovieDescription(){
        setSize(400,400);
        setLocation(600,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Beschrijvingen");
    }
    public void createComponents(Container container){
        DefaultListModel list = new DefaultListModel();
        for (Film film : Main.programSupplier.getFilms()){
            list.addElement(film);
            film.getTitle();
        }
        JList flist = new JList(list);
        flist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        flist.setLayoutOrientation(JList.VERTICAL);
        flist.setVisibleRowCount(3);
        container.add(flist, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(flist);
        container.add(scrollPane);
    }








}




