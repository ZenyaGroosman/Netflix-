package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerDeleteAccount implements ActionListener {

    private JList accountList;

    public ActionListenerDeleteAccount(JList accountlist) {
        this.accountList = accountlist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = (Account) accountList.getSelectedValue();
        Main.accountSupplier.deleteAccount(account);
        accountList.remove(accountList.getSelectedIndex());
    }
}
