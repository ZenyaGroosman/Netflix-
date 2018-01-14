package nl.avans.userInterfaces;

import nl.avans.userInterfaces.actionListener.ActionListenerStatistixSelectMovie;
import nl.avans.userInterfaces.actionListener.ActionListenerStatistixSerieStats;

import javax.swing.*;
import java.awt.*;

public class UserInterfaceStatistix extends UserInterfaceBase {
    public UserInterfaceStatistix(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public void run() {
        createComponents(getFrame().getContentPane());
        getFrame().pack();
        getFrame().setVisible(true);
    }

    @Override
    public void createComponents(Container container) {
        container.setLayout(new BorderLayout(10, 5));
        container.add(createHeader(), BorderLayout.NORTH);
        container.add(createBody(), BorderLayout.CENTER);

        container.add(createFooter(), BorderLayout.SOUTH);
    }

    //creates the body of the page
    private JPanel createBody() {
        JPanel panel = new JPanel(new BorderLayout(3, 0));
        panel.add(createSidePanel(panel), BorderLayout.WEST);

        return panel;
    }

    //creates the button panel on the left
    private JPanel createSidePanel(Container container) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 1));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        JButton seriesStats = new JButton("Serie statestieken");
        seriesStats.addActionListener(new ActionListenerStatistixSerieStats(center, getFrame()));
        panel.add(seriesStats);
        seriesStats.doClick(); //makes it so the "serie statestieken" part of the page loads
        JButton movieStatistix = new JButton("Film statestieken");
        movieStatistix.addActionListener(new ActionListenerStatistixSelectMovie(center, getFrame()));
        panel.add(movieStatistix);
        grid.add(panel);

        container.add(center, BorderLayout.CENTER);
        return grid;
    }
}
