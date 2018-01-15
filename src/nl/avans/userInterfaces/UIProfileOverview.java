package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Profile;
import nl.avans.userInterfaces.pop_ups.PopUpAddProfile;
import nl.avans.userInterfaces.pop_ups.PopUpEditProfile;
import nl.avans.userInterfaces.pop_ups.PopUpviewProfile;

import javax.swing.*;
import java.awt.*;

public class UIProfileOverview extends UserInterfaceBase {
    private DefaultListModel<Profile> profileList;
    private JList<Profile> jList;


    public UIProfileOverview(JFrame jFrame) {
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
        panel.setLayout(new BorderLayout(100, 0));

        title(panel);
        panel.add(new JLabel(" "), BorderLayout.WEST);

        panel.add(new JLabel(" "), BorderLayout.EAST);
        profiles(panel);

        JPanel grid = (new JPanel());
        grid.setLayout(new GridLayout(1, 5));
        JButton view = new JButton("Bekijken");
        view.setBackground(Color.red);
        view.setForeground(Color.white);
        grid.add(view);
        view.addActionListener(e -> {

            new PopUpviewProfile(jList, profileList);



        });

        JButton edit = new JButton("Bewerken");
        edit.setBackground(Color.red);
        edit.setForeground(Color.white);
        grid.add(edit);
        edit.addActionListener(e -> {
            new PopUpEditProfile(jList, profileList);
                });

        JButton add = new JButton("Toevoegen");
        add.setBackground(Color.red);
        add.setForeground(Color.white);
        grid.add(add);
        if (profileList.size() >= 5) {
            add.setEnabled(false);
        }
        add.addActionListener(e -> {
            if (profileList.size() < 5)
                new PopUpAddProfile(profileList);
            else
                add.setEnabled(false);
        });

        JButton delete = new JButton("Verwijderen");
        delete.setBackground(Color.red);
        delete.setForeground(Color.white);
        grid.add(delete);
        delete.addActionListener(e -> {
            if (jList.getSelectedValue() != null) {
                Main.accountSupplier.deleteProfile((Profile) jList.getSelectedValue());
                profileList.removeElement(jList.getSelectedValue());
            }
            if (profileList.size() < 5) {
                add.setEnabled(true);
            }
        });

        JButton select = new JButton("Selecteer");
        select.setBackground(Color.red);
        select.setForeground(Color.white);
        grid.add(select);
        select.addActionListener(e -> {
            SelectedAccount.setSelectedProfile((Profile) jList.getSelectedValue());
            getFrame().setTitle("Netflix Statistix " + (SelectedAccount.getSelectedAccount() == null ? "No account selected" : SelectedAccount.getSelectedAccount() + (SelectedAccount.getSelectedProfile() == null ? " No profile selected" : " " + SelectedAccount.getSelectedProfile())));
        });
        panel.add(grid, BorderLayout.SOUTH);

        container.add(panel);
    }

    private void title(Container container) {
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
        profileList = new DefaultListModel<Profile>();
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 5));

        for (Profile profile : SelectedAccount.getSelectedAccount().getProfiles()) {
            profileList.addElement(profile);
        }

        jList = new JList<Profile>(profileList);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);

        grid.add(jList);

        jList.setBackground(Color.LIGHT_GRAY);

        container.add(grid);
    }
}
