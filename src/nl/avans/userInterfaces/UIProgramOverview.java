package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.sql.Film;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;


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

        container.setLayout(new BorderLayout(10,5));
        container.add(createHeader(),BorderLayout.NORTH);
        container.add(createFooter(), BorderLayout.SOUTH);
        container.add(panel1, BorderLayout.CENTER);


       //SideButtons

        JPanel subPanel = new JPanel();
        JPanel gridsPanel = new JPanel();

        gridsPanel.setLayout(new GridLayout(3,1));
        subPanel.setLayout(new GridLayout(2,1));
        gridsPanel.setBackground(Color.LIGHT_GRAY);

        gridsPanel.add(subPanel);

        JButton button1 = new JButton("Films");
        button1.setBackground(Color.red);
        button1.setForeground(Color.white);


        JButton button2 = new JButton("Series");
        button2.setBackground(Color.red);
        button2.setForeground(Color.white);

        subPanel.add(button1);
        subPanel.add(button2);
        subPanel.setBackground(Color.LIGHT_GRAY);

        container.add(gridsPanel, BorderLayout.WEST);

        // Add Shows and Movies

//        Main.programmaSupplier.getFilms();
//        Main.programmaSupplier.getSeries();


        DefaultListModel demoList = new DefaultListModel();
        for (Film film : Main.programSupplier.getFilms()){
            demoList.addElement(film.getTitle()); }
        JList serieList = new JList(demoList);
        serieList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        serieList.setLayoutOrientation(JList.VERTICAL);
        serieList.setVisibleRowCount(2);
        container.add(serieList);
        serieList.setBackground(Color.lightGray);





















    }
}
