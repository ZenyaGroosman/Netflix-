package nl.avans.userInterfaces;

import nl.avans.misc.SelectedAccount;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class UserInterfaceBase implements Runnable {


    private JFrame jFrame;

    public UserInterfaceBase(JFrame jFrame) {

        this.jFrame = jFrame;
    }

    public abstract void run();

    public abstract void createComponents(Container container);

    public JFrame getFrame() {
        return jFrame;
    }

    public JPanel createHeader() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        JButton account = new JButton("Account");
        JButton profile = new JButton("Profiel");
        JButton programs = new JButton("Programma's");
        JButton watchedPrograms = new JButton("Bekeken programma's");
        JButton stats = new JButton("Statestieken");
        account.setBackground(Color.red);
        account.setForeground(Color.white);
        profile.setBackground(Color.red);
        profile.setForeground(Color.white);
        programs.setBackground(Color.red);
        programs.setForeground(Color.white);
        watchedPrograms.setBackground(Color.red);
        watchedPrograms.setForeground(Color.white);
        stats.setBackground(Color.red);
        stats.setForeground(Color.white);
        programs.addActionListener(e -> changePage(EnumPages.PROGRAMS));
        account.addActionListener(e -> changePage(EnumPages.ACCOUNTS));
        profile.addActionListener(e -> changePage(EnumPages.PROFILES));
        watchedPrograms.addActionListener(e -> changePage(EnumPages.WATCHED_PRAGRAMS));
        stats.addActionListener(e -> changePage(EnumPages.STATISTIX));


        if (SelectedAccount.getSelectedAccount() == null)
            profile.setEnabled(false);
        if (SelectedAccount.getSelectedProfile() == null)
            watchedPrograms.setEnabled(false);
        panel.add(account);
        panel.add(profile);
        panel.add(programs);
        panel.add(watchedPrograms);
        panel.add(stats);


        return panel;
    }

    public JPanel createFooter() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));


        JLabel jLabel = new JLabel("Informatica | Jaar 1 | Klas C | Dion Klaassen, Zenya Groosman, Bart Klomp ");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        BufferedImage netflix = null;
        try {
            netflix = ImageIO.read(new File("resources/images/netflix_logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(netflix));
        picLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(picLabel);
        panel.add(jLabel);
        return panel;
    }

    /*
    * This method empties the current content pane and fills it with the content of the next selected page.
    */
    private void changePage(EnumPages pages) {
        UserInterfaceBase userInterfaceBase = null;
        jFrame.setContentPane(new Container());
        switch (pages) {
            case PROFILES:
//                userInterfaceBase = new UIProfiles(temp);
                break;
            case PROGRAMS:
                userInterfaceBase = new UIProgramOverview(jFrame);
                break;
            case WATCHED_PRAGRAMS:
                userInterfaceBase = new UIWatchedShows(jFrame);
                break;
            case STATISTIX:
                userInterfaceBase = new UserInterfaceStatistix(jFrame);
                break;
            default:
                userInterfaceBase = new UIAccountOverview(jFrame);
                break;
        }
        userInterfaceBase.run();
        jFrame.repaint();
        jFrame.revalidate();
    }
}
