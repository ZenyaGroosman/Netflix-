
package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.sql.Film;
import nl.avans.sql.Series;
//import nl.avans.userInterfaces.actionListener.ClickListenerProgramOverview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JScrollPane;


public class UIProgramOverview extends UserInterfaceBase {


    private JPanel panel1;
    private JList film;

    public UIProgramOverview(JFrame jFrame) {
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

        JButton button1 = new JButton("Series");
        button1.setBackground(Color.red);
        button1.setForeground(Color.white);

        subPanel.add(button1);
        subPanel.setBackground(Color.LIGHT_GRAY);
        container.add(gridsPanel, BorderLayout.WEST);

        // Add Shows and Movies

//        Main.programmaSupplier.getFilms();
//        Main.programmaSupplier.getSeries();


        DefaultListModel demoList = new DefaultListModel();
        for (Film film : Main.programSupplier.getFilms()) {
           demoList.addElement(film);
            demoList.addElement(film.getGenre());
            demoList.addElement(film.getLanguage());
            demoList.addElement(film.getMinimumAge());
            demoList.addElement(film.getDuration());
        }
        JList filmList = new JList(demoList);

        this.film = filmList;


        DefaultListModel demoList2 = new DefaultListModel();
        for (Series series : Main.programSupplier.getSeries()) {
            demoList2.addElement(series);
            demoList2.addElement(series.getAfleveringen());
            demoList2.addElement(series.getGenre());
            demoList2.addElement(series.getLeeftijdsindicatie());
            demoList2.addElement(series.getLijktOp());
            demoList2.addElement(series.getTaal());
            JList serieList = new JList(demoList2);
            serieList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            serieList.setLayoutOrientation(JList.VERTICAL);
            serieList.setVisibleRowCount(2);
            container.add(serieList, BorderLayout.CENTER);
            serieList.setBackground(Color.LIGHT_GRAY);
            JScrollPane scrollPane1 = new JScrollPane(serieList);
            scrollPane1.setMaximumSize(new Dimension(100, 50));
            container.add(scrollPane1);


        }
        JButton btn = new JButton("Films");
        subPanel.add(btn);
        btn.setBackground(Color.red);
        btn.setForeground(Color.white);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMovieDescription description = new ShowMovieDescription(film);
                description.getContentPane();



            }
        });


    }


}