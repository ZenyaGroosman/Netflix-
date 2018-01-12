package nl.avans.userInterfaces.actionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerDeleteAccount implements ActionListener{

    private JPanel panel;
    private JButton edit;
    private JFrame frame;

    public ActionListenerDeleteAccount(JPanel panel, JButton edit, JFrame frame) {
        this.edit = edit;
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == edit) {
            System.exit(0);
        }
    }
}
