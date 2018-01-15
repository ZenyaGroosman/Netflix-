package nl.avans.userInterfaces.pop_ups;

import java.awt.*;
import java.text.NumberFormat;
import javax.swing.*;

import nl.avans.Main;
import nl.avans.misc.CustomNumberFormatter;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.UserInterfaceBase;

public class PopuppEditAccount extends JFrame {


    private JList accountList;
    private DefaultListModel accounts;

    public PopuppEditAccount(JList accountList, DefaultListModel accounts) {
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
        GridLayout layout = new GridLayout(6,2);
        panel.setLayout(layout);

        JTextField name = new JTextField(account2.getNaam());
        JTextField street = new JTextField(account2.getStreet());
        JTextField postalcode = new JTextField(account2.getPostcode());

        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        CustomNumberFormatter numberFormatter = new CustomNumberFormatter(longFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        JFormattedTextField housenumber = new JFormattedTextField(account2.getHouseNumber());

        JTextField place = new JTextField(account2.getPlace());

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

        JButton edit = new JButton("verander");

        edit.setBackground(Color.red);
        edit.setForeground(Color.white);

        panel.add(new JLabel(), layout);
        panel.add(edit, layout);

        container.add(panel, BorderLayout.CENTER);

        edit.addActionListener( e->{
            int id = 0;
            for (Account account:Main.accountSupplier.getAccounts()){
                if (account.getId() > id)
                    id = account.getId();
            }

            System.out.println(name.getText());
            System.out.println(id);
            System.out.println(street.getText());

            System.out.println(postalcode.getText());
            System.out.println(housenumber.getValue());
            System.out.println(place.getText());

            Account account = new Account(id, name.getText(), street.getText(), postalcode.getText(), (int)housenumber.getValue(), place.getText());
            Account account3 = account;
            Main.accountSupplier.deleteAccount(account);
            Main.accountSupplier.createAccount(account3);

            dispose();
        });
    }
}
