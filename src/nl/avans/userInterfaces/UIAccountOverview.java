package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Account;
import nl.avans.userInterfaces.actionListener.ActionListenerAddAccount;
import nl.avans.userInterfaces.actionListener.ActionListenerEditAccount;
import nl.avans.userInterfaces.actionListener.ActionListenerViewAccount;
import nl.avans.userInterfaces.pop_ups.PopUpViewAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class UIAccountOverview extends UserInterfaceBase {

    private JList accounts;
    private DefaultListModel account;
    private JPanel panel;

    public UIAccountOverview(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public void run() {
        createComponents(getFrame().getContentPane());
        getFrame().pack();
        getFrame().setVisible(true);
    }

    @Override
    public void createComponents(Container container) {
        container.setLayout(new BorderLayout(10, 5));
        container.add(createHeader(), BorderLayout.NORTH);
        container.add(createFooter(), BorderLayout.SOUTH);
        JPanel panel = (new JPanel());
        this.panel = panel;
        panel.setLayout(new BorderLayout(100, 0));

        titel(panel);
        panel.add(new JLabel(" "), BorderLayout.WEST);
        accounts(panel);
        panel.add(new JLabel(" "), BorderLayout.EAST);

        buttons(panel);
        container.add(panel);
    }

    private void titel(Container container) {
        Font f = new Font("default", Font.PLAIN, 40);
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 1));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.NORTH);

        JLabel label = new JLabel("Account Overzicht");

        label.setHorizontalAlignment(SwingConstants.CENTER);

        grid.add(label);

        container.add(panel, BorderLayout.NORTH);

        label.setFont(f);
    }

    public void accounts(Container container) {
        account = new DefaultListModel();
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 5));

        for (Account account2 : Main.accountSupplier.getAccounts()) {
            account.addElement(account2);
        }

        accounts = new JList(account);
        accounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accounts.setLayoutOrientation(JList.VERTICAL);

        grid.add(accounts);

        accounts.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(accounts);
        scrollPane.setMaximumSize(new Dimension(100, 50));
        grid.add(scrollPane);

        container.add(grid);
    }

    private void buttons(Container container) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 4));


        view(grid);
        add(grid);
        edit(grid);
        delete(grid);
        select(grid);

        container.add(grid, BorderLayout.SOUTH);
    }

    private void view(Container container) {
        JButton view = new JButton("bekijken");

        view.setBackground(Color.red);
        view.setForeground(Color.white);

        container.add(view);

        view.addActionListener(new ActionListenerViewAccount(accounts, account));
    }

    private void delete(Container container) {
        JButton delete = new JButton("verwijderen");
        delete.setBackground(Color.red);
        delete.setForeground(Color.white);

        container.add(delete);

        delete.addActionListener(e -> {
            Main.accountSupplier.deleteAccount((Account) accounts.getSelectedValue());
            account.removeElement(accounts.getSelectedValue());
        });
    }


    private void add(Container container) {
        JButton add = new JButton("toevoegen");

        add.setBackground(Color.red);
        add.setForeground(Color.white);

        container.add(add);

        add.addActionListener(new ActionListenerAddAccount(accounts, account));
    }

    private void edit(Container container) {
        JButton edit = new JButton("edit");

        edit.setBackground(Color.red);
        edit.setForeground(Color.white);

        container.add(edit);

        edit.addActionListener(new ActionListenerEditAccount(accounts, account));

    }

    private void select(Container container) {
        JButton select = new JButton("selecteren");
        select.setBackground(Color.red);
        select.setForeground(Color.white);

        container.add(select);

        select.addActionListener(e -> {
            SelectedAccount.setSelectedAccount((Account)accounts.getSelectedValue());
        });
    }
}


