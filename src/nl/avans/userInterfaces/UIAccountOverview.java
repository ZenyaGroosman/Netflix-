package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.sql.Account;



import javax.swing.*;
import java.awt.*;

public class UIAccountOverview extends UserInterfaceBase{
    public UIAccountOverview(JFrame jFrame){
        super (jFrame);
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
        panel.setLayout(new BorderLayout(100,0));

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

    private void accounts(Container container) {
        DefaultListModel AccountList = new DefaultListModel();
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 5));

        for (Account account : Main.accountSupplier.getAccounts()) {
            AccountList.addElement(account.getNaam() + " (" + account.getId() + ")");
        }

        JList accountList = new JList(AccountList);
        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountList.setLayoutOrientation(JList.VERTICAL);

        grid.add(accountList);

        accountList.setBackground(Color.LIGHT_GRAY);

        container.add(grid);
    }

    private void buttons(Container container){
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 4));

        view(grid);
        add(grid);
        edit(grid);
        delete(grid);

        container.add(grid, BorderLayout.SOUTH);
    }

    private void view(Container container) {
        JButton view = new JButton("bekijken");

        view.setBackground(Color.red);
        view.setForeground(Color.white);

        container.add(view);
    }

    private void delete(Container container) {
        JButton delete = new JButton("verwijderen");
        delete.setBackground(Color.red);
        delete.setForeground(Color.white);

        getFrame().repaint();
        getFrame().revalidate();

        container.add(delete);
    }

    private void add(Container container) {
        JButton add = new JButton("toevoegen");

        add.setBackground(Color.red);
        add.setForeground(Color.white);

        container.add(add);
    }

    private void edit (Container container) {


    }
}
