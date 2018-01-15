package nl.avans.userInterfaces.actionListener;

import nl.avans.sql.Account;
import nl.avans.userInterfaces.pop_ups.PopuppEditAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerEditAccount implements ActionListener {

    private JList accountlist;
    private DefaultListModel accounts;

    public ActionListenerEditAccount(JList accountlist, DefaultListModel accounts) {
        this.accountlist = accountlist;
        this.accounts = accounts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = (Account) accountlist.getSelectedValue();

        new PopuppEditAccount(accountlist, accounts);
    }
}
