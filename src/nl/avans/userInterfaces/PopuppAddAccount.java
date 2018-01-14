package nl.avans.userInterfaces;

import java.awt.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.Border;
import nl.avans.Main;
import nl.avans.misc.CustomNumberFormatter;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.actionListener.ActionListenerAddAccount;
import nl.avans.userInterfaces.actionListener.ActionListenerAddAccountToDatabase;
import nl.avans.userInterfaces.actionListener.ActionListenerDeleteAccount;

import nl.avans.sql.Account;
import nl.avans.sql.Film;

public class PopuppAddAccount extends JFrame {

    private JList accountList;
    private DefaultListModel accounts;

    public PopuppAddAccount(JList accountList, DefaultListModel accounts) {
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
        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = (new JPanel());
        GridLayout layout = new GridLayout(6,2);
        panel.setLayout(layout);

        JTextField name = new JTextField();
        JTextField street = new JTextField();
        JTextField postalcode = new JTextField();

        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        CustomNumberFormatter numberFormatter = new CustomNumberFormatter(longFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        JFormattedTextField housenumber = new JFormattedTextField(numberFormatter);

        JTextField place = new JTextField();

        JLabel entername = new JLabel("enter naam");
        JLabel enterstreet = new JLabel("enter straat");
        JLabel enterpostalcode = new JLabel("enter post code");
        JLabel enterhousenumber = new JLabel("enter huisnummer");
        JLabel enterplace = new JLabel("enter plaats");

        panel.add(entername, layout);
        panel.add(name, layout);
        panel.add(enterstreet, layout);
        panel.add(street, layout);
        panel.add(enterpostalcode, layout);
        panel.add(postalcode, layout);
        panel.add(enterhousenumber, layout);
        panel.add(housenumber, layout);
        panel.add(enterplace, layout);
        panel.add(place, layout);

        JButton add = new JButton("toevoegen");

        add.setBackground(Color.red);
        add.setForeground(Color.white);

        panel.add(new JLabel(), layout);
        panel.add(add, layout);

        container.add(panel, BorderLayout.CENTER);

        int id = 0;
        for (Account account:Main.accountSupplier.getAccounts()){
            if (account.getId() > id)
                id = account.getId();
        }
        id++;
        System.out.println(id);
        System.out.println(name.getText());
        System.out.println(street.getText());
        System.out.println(postalcode.getText());
        System.out.println(housenumber.getText());
        System.out.println(place.getText());

        Account newAccount = new Account(id, name.getText(), street.getText(), postalcode.getText(), (int)housenumber.getValue(), place.getText());

        add.addActionListener(new ActionListenerAddAccountToDatabase(newAccount, accounts));
    }
}
