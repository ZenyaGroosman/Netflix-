package nl.avans.userInterfaces;

import nl.avans.misc.SelectedAccount;
import nl.avans.userInterfaces.actionListener.ClickListenerStatistixSelectMovie;
import nl.avans.userInterfaces.actionListener.ClickListenerStatistixSideBar;

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


    private JPanel createBody() {
        JPanel panel = new JPanel(new BorderLayout(3, 0));
        panel.add(createSidePanel(panel), BorderLayout.WEST);

        return panel;
    }

    private JPanel createSidePanel(Container container) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 1));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        JButton seriesStats = new JButton("Serie statestieken");
        seriesStats.addActionListener(new ClickListenerStatistixSideBar(center, getFrame()));
        panel.add(seriesStats);
        JButton movieStatistix = new JButton("Film statestieken");
        movieStatistix.addActionListener(new ClickListenerStatistixSelectMovie(center, getFrame()));
        panel.add(movieStatistix);
        JButton accountStatistix = new JButton("Account statestieken");
        accountStatistix.addActionListener(new ClickListenerStatistixSideBar(center, getFrame()));
        panel.add(accountStatistix);
        JButton personalStat = new JButton("Persoonlijke statestieken");
        personalStat.addActionListener(new ClickListenerStatistixSideBar(center, getFrame()));
        if (SelectedAccount.getSelectedAccount() == null)
            personalStat.setEnabled(false);
        panel.add(personalStat);
        grid.add(panel);

        container.add(center, BorderLayout.CENTER);
        return grid;
    }


}
