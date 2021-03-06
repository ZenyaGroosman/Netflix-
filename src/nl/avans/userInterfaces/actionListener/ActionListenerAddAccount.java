package nl.avans.userInterfaces.actionListener;

import nl.avans.sql.Account;
import nl.avans.userInterfaces.pop_ups.PopuppAddAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerAddAccount implements ActionListener {

    private JList accountList;
    private DefaultListModel accounts;

    public ActionListenerAddAccount(JList accountlist, DefaultListModel accounts) {
        this.accountList = accountlist;
        this.accounts = accounts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PopuppAddAccount(accounts);
    }
}
