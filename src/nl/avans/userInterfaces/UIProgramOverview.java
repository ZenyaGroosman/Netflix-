package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.sql.Film;
import nl.avans.sql.Series;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JScrollPane;


public class UIProgramOverview extends UserInterfaceBase {



    private JPanel panel1;

    public UIProgramOverview(JFrame jFrame){
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


        panel1 = new JPanel();
        panel1.setBackground(Color.darkGray);

        // Creating header en footer

        container.setLayout(new BorderLayout(10, 5));
        container.add(createHeader(), BorderLayout.NORTH);
        container.add(createFooter(), BorderLayout.SOUTH);
        container.add(panel1, BorderLayout.CENTER);


//       //SideButtons
//
        JPanel subPanel = new JPanel();
        JPanel gridsPanel = new JPanel();

        gridsPanel.setLayout(new GridLayout(3, 1));
        subPanel.setLayout(new GridLayout(2, 1));
        gridsPanel.setBackground(Color.LIGHT_GRAY);

        gridsPanel.add(subPanel);

        JButton button1 = new JButton("Films & Series");
        button1.setBackground(Color.red);
        button1.setForeground(Color.white);

        subPanel.add(button1);
        subPanel.setBackground(Color.LIGHT_GRAY);
        container.add(gridsPanel, BorderLayout.WEST);

        // Add Shows and Movies

        DefaultListModel demoList = new DefaultListModel();
        for (Film film : Main.programSupplier.getFilms()) {
            demoList.addElement(film);
        }
        JList filmList = new JList(demoList);
        filmList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        filmList.setLayoutOrientation(JList.VERTICAL);
        filmList.setVisibleRowCount(2);
        container.add(filmList, BorderLayout.CENTER);
        filmList.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(filmList);
        scrollPane.setMaximumSize(new Dimension(100, 50));
        container.add(scrollPane);


        DefaultListModel demoList2 = new DefaultListModel();
        for (Series series : Main.programSupplier.getSeries()) {
            demoList2.addElement(series);

            JList serieList = new JList(demoList2);
            serieList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            serieList.setLayoutOrientation(JList.VERTICAL);
            serieList.setVisibleRowCount(2);
            container.add(serieList, BorderLayout.EAST);
            serieList.setBackground(Color.LIGHT_GRAY);

        }
        // Show movie and show description

        JButton btn = new JButton("Beschrijvingen");
        subPanel.add(btn);
        btn.setBackground(Color.red);
        btn.setForeground(Color.white);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMovieDescription description = new ShowMovieDescription(filmList);
            }
        });

    }


    }
