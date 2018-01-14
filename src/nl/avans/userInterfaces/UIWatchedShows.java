package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.misc.SelectedAccount;
import nl.avans.sql.Program;
import nl.avans.sql.WatchedProgram;
import nl.avans.userInterfaces.pop_ups.PopUpEditWatchedProgram;

import javax.swing.*;
import java.awt.*;


public class UIWatchedShows extends UserInterfaceBase {


    private JPanel panel1;

    public UIWatchedShows(JFrame jFrame) {
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

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(100, 0));

        //makes a jlist with all watched programs from the selected profile
        DefaultListModel<WatchedProgram> watchedProgramList = new DefaultListModel<WatchedProgram>();
        for (WatchedProgram program : Main.accountSupplier.getWatchedPrograms()) {
            if (program.getProfile().equals(SelectedAccount.getSelectedProfile())) {
                watchedProgramList.addElement(program);
            }
        }

        JList<WatchedProgram> jList = new JList<WatchedProgram>(watchedProgramList);
        jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setVisibleRowCount(2);
        jList.setBackground(Color.LIGHT_GRAY);
        panel.add(jList, BorderLayout.CENTER);

        panel.add(new JLabel(), BorderLayout.WEST);
        panel.add(new JLabel(), BorderLayout.EAST);
        container.add(panel, BorderLayout.CENTER);


        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 3));

        JButton add = new JButton("Bewerken");

        add.setBackground(Color.red);
        add.setForeground(Color.white);
        add.addActionListener(e->{
            new PopUpEditWatchedProgram(jList, watchedProgramList, getFrame());
        });
        grid.add(add);

        container.add(add, BorderLayout.SOUTH);
    }



}
