package nl.avans.userInterfaces.actionListener;

import nl.avans.sql.Account;
import nl.avans.userInterfaces.PopuppAddAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerAddAccount implements ActionListener{
    private JList accountList;

    public ActionListenerAddAccount(JList accountlist) {
        this.accountList = accountlist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = (Account)accountList.getSelectedValue();

        new PopuppAddAccount(accountList);



     }
}
