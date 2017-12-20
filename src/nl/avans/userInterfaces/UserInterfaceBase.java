package nl.avans.userInterfaces;

import nl.avans.SelectedAccount;

import javax.swing.*;
import java.awt.*;

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
    public JLabel createFooter(){
        JLabel jLabel = new JLabel("Informatica | Jaar 1 | Klas C | Dion Klaassen, Zenya Groosman, Bart Klomp ");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        return jLabel;
    }
}
