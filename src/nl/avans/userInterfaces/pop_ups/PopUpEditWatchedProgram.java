package nl.avans.userInterfaces.pop_ups;


import nl.avans.Main;
import nl.avans.sql.WatchedProgram;
import nl.avans.userInterfaces.UserInterfaceBase;

import javax.swing.*;
import java.awt.*;

public class PopUpEditWatchedProgram extends JFrame {
    private JList<WatchedProgram> watchedProgramList;
    private DefaultListModel defaultListModel;


    public PopUpEditWatchedProgram(JList<WatchedProgram> watchedProgramList, DefaultListModel defaultListModel) {
        this.watchedProgramList = watchedProgramList;
        this.defaultListModel = defaultListModel;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Bewerk bekeken programma");

        createComponents(this.getContentPane());
    }


    public void createComponents(Container container) {
        WatchedProgram watchedProgram = watchedProgramList.getSelectedValue();
        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        container.add(panel, BorderLayout.CENTER);
        panel.add(new JLabel("Selecteer kijpercentage"));
        //makes slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, watchedProgram.getPercentage());
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        JButton button = new JButton("Save");
        button.setBackground(Color.red);
        button.setForeground(Color.white);
        button.addActionListener(e -> {
            WatchedProgram newProgram = new WatchedProgram(slider.getValue(), watchedProgram.getProgram(), watchedProgram.getProfile());
            Main.accountSupplier.updateWatchedPrograms(watchedProgram, newProgram);
            defaultListModel.removeElement(watchedProgram);
            defaultListModel.addElement(newProgram);
            this.dispose();
//            jFrame.repaint();
//            jFrame.revalidate();
        });
        panel.add(button);

    }

}