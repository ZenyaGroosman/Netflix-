/*package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.Account;
import nl.avans.sql.AccountSupplier;
import nl.avans.userInterfaces.UIAccountOverview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerAccount implements ActionListener{

    private JPanel panel;
    private JButton delete;
    private JFrame frame;

    public ActionListenerAccount(JPanel panel, JButton delete, JFrame frame) {
        this.delete = delete;
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.accountSupplier.deleteAccount();
        if (e.getSource() == delete) {

        }
    }
}
*/