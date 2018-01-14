/*package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Account;
import nl.avans.sql.Profile;


import javax.swing.*;
import java.awt.*;

public class UIProfielOverview extends UserInterfaceBase{
    public UIProfielOverview(JFrame jFrame){
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

        panel.add(new JLabel(" "), BorderLayout.EAST);
        profiles(panel);

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

        JLabel label = new JLabel("Profiel Overzicht");

        label.setHorizontalAlignment(SwingConstants.CENTER);

        grid.add(label);

        container.add(panel, BorderLayout.NORTH);

        label.setFont(f);
    }

    private void profiles(Container container) {
        DefaultListModel profileList = new DefaultListModel();
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 5));

        for (Profile profile : SelectedAccount.getSelectedAccount().getProfiles()) {
            profileList.addElement(profile);
        }

        JList accountList = new JList(profileList);
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
       JButton edit = new JButton("edit");

       edit.setBackground(Color.red);
       edit.setForeground(Color.white);

       container.add(edit);

    }
}
*/