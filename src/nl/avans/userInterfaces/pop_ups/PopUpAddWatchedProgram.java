package nl.avans.userInterfaces.pop_ups;


import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Program;
import nl.avans.sql.WatchedProgram;
import nl.avans.userInterfaces.UserInterfaceBase;

import javax.swing.*;
import java.awt.*;

public class PopUpAddWatchedProgram extends JFrame {
    private DefaultListModel<WatchedProgram> defaultListModel;


    public PopUpAddWatchedProgram(DefaultListModel<WatchedProgram> defaultListModel) {
        this.defaultListModel = defaultListModel;

        setSize(400, 400);
        setLocation(600, 300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setTitle("Voeg bekeken programma toe");

        createComponents(this.getContentPane());
    }


    public void createComponents(Container container) {
        container.add(UserInterfaceBase.createFooter(), BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        container.add(panel, BorderLayout.CENTER);



        Program[] programs = new Program[Main.programSupplier.getPrograms().size()];
        for (int i = 0; i < Main.programSupplier.getPrograms().size(); i++) {
            programs[i] = Main.programSupplier.getPrograms().get(i);
        }


        JComboBox<Program> comboBox = new JComboBox<>(programs);
        comboBox.setPreferredSize(new Dimension(300, 20));
        panel.add(new JLabel("Selecteer aflevering/film"));
        panel.add(comboBox);

        panel.add(new JLabel("Selecteer percentage"));
        //makes slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        JButton button = new JButton("Save");
        button.setBackground(Color.red);
        button.setForeground(Color.white);
        button.addActionListener(e -> {
            WatchedProgram newProgram = new WatchedProgram(slider.getValue(), (Program) comboBox.getSelectedItem(), SelectedAccount.getSelectedProfile());
            boolean hasBeenWatched = false;
            for (int i = 0; i < defaultListModel.getSize(); i++){
                if (defaultListModel.get(i).getProgram().equals((Program) comboBox.getSelectedItem())){
                    hasBeenWatched = true;
                }
            }
            if (!hasBeenWatched) {
                Main.accountSupplier.createWatchedPrograms(newProgram);
                defaultListModel.addElement(newProgram);
                this.dispose();
            } else {
                comboBox.setBackground(Color.RED);
            }
//            jFrame.repaint();
//            jFrame.revalidate();
        });
        panel.add(button);

    }

}