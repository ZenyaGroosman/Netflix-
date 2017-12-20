package nl.avans.userInterfaces;

import nl.avans.SelectedAccount;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class UserInterfaceBase implements Runnable{
    private JFrame jFrame;

    public UserInterfaceBase(JFrame jFrame){

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
        if (SelectedAccount.getSelectedAccount() == null)
            profile.setEnabled(false);
        if (SelectedAccount.getSelectedProfiel() == null)
            watchedPrograms.setEnabled(false);
        panel.add(account);
        panel.add(profile);
        panel.add(programs);
        panel.add(watchedPrograms);
        panel.add(stats);
        return panel;
    }
    public JPanel createFooter(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        JLabel jLabel = new JLabel("Informatica | Jaar 1 | Klas C | Dion Klaassen, Zenya Groosman, Bart Klomp ");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        BufferedImage netflix = null;
        try {
            netflix = ImageIO.read(new File("resources/images/netflix_logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(netflix));
        panel.add(picLabel);
        panel.add(jLabel);
        return panel;
    }
}
