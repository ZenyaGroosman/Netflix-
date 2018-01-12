package nl.avans.userInterfaces.actionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerStatistixMovie implements ActionListener {
    private JPanel panel;
    private JComboBox<String> movieList;
    private JFrame frame;

    public ActionListenerStatistixMovie(JPanel panel, JComboBox movieList, JFrame frame) {
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

