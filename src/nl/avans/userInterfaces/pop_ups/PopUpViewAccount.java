package nl.avans.userInterfaces.pop_ups;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;

import nl.avans.Main;
import nl.avans.misc.CustomNumberFormatter;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.UserInterfaceBase;

public class PopUpViewAccount extends JFrame {

    JList accountList;
    DefaultListModel accounts;


    public PopUpViewAccount(JList accountList, DefaultListModel accounts) {
        this.accountList = accountList;
        this.accounts = accounts;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("account toevoegen");
        createComponents(this.getContentPane());
    }

    public void createComponents(Container container) {
        Account account2 = (Account) accountList.getSelectedValue();

        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = (new JPanel());
        GridLayout layout = new GridLayout(6, 2);
        panel.setLayout(layout);

        JLabel thisName = new JLabel("dit is je naam");
        JLabel thisStreet = new JLabel("dit is je straat");
        JLabel thisPostalcode = new JLabel("dit is je postcode");
        JLabel thisHousenumber = new JLabel("dit is je huisnummer");
        JLabel thisPlace = new JLabel("dit is je plaats");
        JLabel thisId = new JLabel("dit is je id");

        JLabel id = new JLabel(Integer.toString(account2.getId()));
        JLabel name = new JLabel(account2.getNaam());
        JLabel street = new JLabel(account2.getStreet());
        JLabel postalcode = new JLabel(account2.getPostcode());
        JLabel housenumber = new JLabel(Integer.toString(account2.getHouseNumber()));
        JLabel place = new JLabel(account2.getPlace());

        panel.add(thisId, layout);
        panel.add(id, layout);
        panel.add(thisName, layout);
        panel.add(name, layout);
        panel.add(thisStreet, layout);
        panel.add(street, layout);
        panel.add(thisPostalcode, layout);
        panel.add(postalcode, layout);
        panel.add(thisHousenumber, layout);
        panel.add(housenumber, layout);
        panel.add(thisPlace, layout);
        panel.add(place, layout);

        container.add(panel, BorderLayout.CENTER);
    }


}
