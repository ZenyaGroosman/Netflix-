package nl.avans.userInterfaces.actionListener;

import nl.avans.sql.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class ActionListenerEditAccount implements ActionListener {


    private JList accountList;


    public ActionListenerEditAccount(JList accountList) {
       this.accountList = accountList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = (Account)accountList.getSelectedValue();
    }

}