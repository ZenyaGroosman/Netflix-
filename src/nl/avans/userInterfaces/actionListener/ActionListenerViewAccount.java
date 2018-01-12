package nl.avans.userInterfaces.actionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerViewAccount implements ActionListener{
    private JPanel panel;
    private JButton view;
    private JFrame frame;

    public ActionListenerViewAccount(JPanel panel, JButton view, JFrame frame) {
        this.view = view;
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view) {
            System.exit(0);
        }
    }
}
