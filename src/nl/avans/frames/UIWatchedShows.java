package nl.avans.frames;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class UIWatchedShows extends JFrame {


    private JFrame frame1;
    private JPanel panel1;

    public UIWatchedShows(){

        WatchedShows();

    }
    public void WatchedShows(){
        frame1 = new JFrame("Watched Shows");
        frame1.setVisible(true);
        frame1.setSize(600,400);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel();
        panel1.setBackground(Color.darkGray);

        frame1.add(panel1);




    }
}
