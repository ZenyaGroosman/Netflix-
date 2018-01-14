package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.Account;
import nl.avans.sql.AccountSupplier;
import nl.avans.userInterfaces.UIAccountOverview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerAddAccountToDatabase implements ActionListener {

    private Account account;
    private DefaultListModel accounts;

    public ActionListenerAddAccountToDatabase(Account account, DefaultListModel accounts) {
        this.account = account;
        this.accounts = accounts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.accounts.addElement(account);
        Main.accountSupplier.createAccount(account);
    }
}
