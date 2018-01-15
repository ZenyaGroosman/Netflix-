package nl.avans.userInterfaces.pop_ups;


import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Profile;
import nl.avans.userInterfaces.UserInterfaceBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PopUpAddProfile extends JFrame {
    private DefaultListModel<Profile> defaultListModel;


    public PopUpAddProfile(DefaultListModel<Profile> defaultListModel) {
        this.defaultListModel = defaultListModel;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Voeg profiel toe");

        createComponents(this.getContentPane());
    }


    public void createComponents(Container container) {
        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        container.add(panel, BorderLayout.CENTER);


        panel.add(new JLabel("Profiel naam "));
        JTextField profileName = new JTextField();
        panel.add(profileName);

        panel.add(new JLabel("Selecteer geboortedatum "));

        JPanel birthDay = new JPanel();
        birthDay.setLayout(new GridLayout(3, 1));

        Integer[] years = new Integer[2018 - 1900];
        for (int i = 0; i < 118; i++) {
            years[i] = 1900 + i;
        }

        JComboBox<Integer> yearBox = new JComboBox<>(years);
        birthDay.add(yearBox);
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = 1 + i;
        }
        JComboBox<Integer> monthBox = new JComboBox<>(months);
        birthDay.add(monthBox);
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = 1 + i;
        }
        JComboBox<Integer> dayBox = new JComboBox<>(days);
        ActionListener actionListener = e -> {
            int daysInMonth = getDaysInMonth((int) monthBox.getSelectedItem(), (int) yearBox.getSelectedItem());
            dayBox.removeAllItems();
            for (int i = 1; i <= daysInMonth; i++) {
                dayBox.addItem(i);
            }
        };
        yearBox.addActionListener(actionListener);
        monthBox.addActionListener(actionListener);
        birthDay.add(dayBox);
        panel.add(birthDay);

        JButton button = new JButton("Save");
        button.setBackground(Color.red);
        button.setForeground(Color.white);
        button.addActionListener(e -> {

            if (!profileName.getText().isEmpty()) {
                boolean taken = false;
                for (int i = 0; i < defaultListModel.size(); i++){
                    if (defaultListModel.get(i).getProfileName().equals(profileName.getText())) {
                        profileName.setBackground(Color.RED);
                        taken = true;
                        break;
                    }
                }
                if (!taken) {
                    Profile profile = new Profile(new Date((int) yearBox.getSelectedItem() - 1900, (int) monthBox.getSelectedItem(), (int) dayBox.getSelectedItem()), profileName.getText(), SelectedAccount.getSelectedAccount());
                    boolean result = Main.accountSupplier.createProfile(profile);
                    if (result) {
                        defaultListModel.addElement(profile);
                        this.dispose();
                    }
                }
            } else
                profileName.setBackground(Color.RED);
        });
        panel.add(button);

    }


    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
                return 31;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            case 2:
                return (year % 4 == 0 ? 29 : 28);
        }
        return -1;
    }
}