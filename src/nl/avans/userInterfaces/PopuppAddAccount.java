package nl.avans.userInterfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import nl.avans.Main;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.actionListener.ActionListenerAddAccount;
import nl.avans.userInterfaces.actionListener.ActionListenerDeleteAccount;

import nl.avans.sql.Account;
import nl.avans.sql.Film;

public class PopuppAddAccount extends JFrame {

    private JList accountList;

    public PopuppAddAccount(JList accountList) {
        this.accountList = accountList;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("account toevoegen");
        createComponents(this.getContentPane());

    }



    public void createComponents(Container container) {
        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = (new JPanel());
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JTextField name = new JTextField("enter name");
        JTextField street = new JTextField("enter street");
        JTextField postalcode = new JTextField("enter postalcode");
        JTextField housenumber = new JTextField("enter housenumber");
        JTextField place = new JTextField("enter place");

        panel.add(name, layout);
        panel.add(street, layout);
        panel.add(postalcode, layout);
        panel.add(housenumber, layout);
        panel.add(place, layout);

        container.add(panel, BorderLayout.CENTER);



    }
}
