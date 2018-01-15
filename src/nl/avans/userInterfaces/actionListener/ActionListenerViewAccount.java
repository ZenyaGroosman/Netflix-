package nl.avans.userInterfaces.actionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.avans.userInterfaces.UIProfielOverview;
import nl.avans.userInterfaces.pop_ups.PopUpViewAccount;
import nl.avans.userInterfaces.pop_ups.PopuppAddAccount;
import nl.avans.userInterfaces.pop_ups.PopuppEditAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerViewAccount implements ActionListener{

    private JList accountList;
    private DefaultListModel accounts;


    public ActionListenerViewAccount(JList accountlist, DefaultListModel accounts) {

        this.accountList = accountlist;
        this.accounts = accounts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PopUpViewAccount(accountList, accounts);
    }
}
