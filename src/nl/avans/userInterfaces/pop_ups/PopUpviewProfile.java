package nl.avans.userInterfaces.pop_ups;

import nl.avans.sql.Profile;
import nl.avans.userInterfaces.UserInterfaceBase;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Date;

public class PopUpviewProfile extends JFrame{

    JList<Profile> profileList;
    DefaultListModel<Profile> profiles;

    public PopUpviewProfile(JList profileList, DefaultListModel profiles) {
        this.profileList = profileList;
        this.profiles = profiles;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("account toevoegen");
        createComponents(this.getContentPane());
    }

    public void createComponents(Container container) {

        Profile profile2 = (Profile)profileList.getSelectedValue();

        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);

        JPanel panel = (new JPanel());
        GridLayout layout = new GridLayout(2, 2);
        panel.setLayout(layout);

        JLabel thisProfileName = new JLabel("dit is je profiel naam");
        JLabel profileName = new JLabel(profile2.getProfileName());

        DateFormat profileBYear = new SimpleDateFormat("MM-dd-yyyy");
        String reportDate = profileBYear.format(profile2.getBirthday());

        JLabel thisProfileBYear = new JLabel("dit is je geboorte jaar");
        JLabel ProfileBYear = new JLabel(reportDate);

        System.out.println(profile2.getBirthday());
        panel.add(thisProfileName, layout);
        panel.add(profileName, layout);
        panel.add(thisProfileBYear, layout);
        panel.add(ProfileBYear, layout);


        container.add(panel, BorderLayout.CENTER);
    }
}
