package nl.avans.userInterfaces.pop_ups;

import java.awt.*;
import java.text.NumberFormat;
import javax.swing.*;

import nl.avans.Main;
import nl.avans.misc.CustomNumberFormatter;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.UserInterfaceBase;

public class PopuppAddAccount extends JFrame {


    private DefaultListModel accounts;

    public PopuppAddAccount(DefaultListModel accounts) {

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

        add.addActionListener( e->{
            int id = 0;
            if(name.getText().isEmpty()){
                name.setBackground(Color.RED);
            }
            if (street.getText().isEmpty()){
                street.setBackground(Color.RED);
            }
            if(postalcode.getText().isEmpty()){
                postalcode.setBackground(Color.RED);
            }
            if(housenumber.getText().isEmpty()){
                housenumber.setBackground(Color.RED);
            }
            if(place.getText().isEmpty()){
                place.setBackground(Color.RED);
            }


            if(!name.getText().isEmpty()){
                name.setBackground(Color.WHITE);
            }
            if (!street.getText().isEmpty()){
                street.setBackground(Color.WHITE);
            }
            if(!postalcode.getText().isEmpty()){
                postalcode.setBackground(Color.WHITE);
            }
            if(!housenumber.getText().isEmpty()){
                housenumber.setBackground(Color.WHITE);
            }
            if(!place.getText().isEmpty()){
                place.setBackground(Color.WHITE);
            }




            for (Account account:Main.accountSupplier.getAccounts()){
                if (account.getId() > id)
                    id = account.getId();
            }
            id++;

            if (!place.getText().isEmpty() && !housenumber.getText().isEmpty() && !postalcode.getText().isEmpty() && !street.getText().isEmpty() && !name.getText().isEmpty()) {


                Account account = new Account(id, name.getText(), street.getText(), postalcode.getText(), (int) housenumber.getValue(), place.getText());

                this.accounts.addElement(account);
                Main.accountSupplier.createAccount(account);

                dispose();
            }
        });
    }
}
