package nl.avans.frames;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class UIWatchedShows extends UserInterfaceBase {



    private JPanel panel1;

    public UIWatchedShows(JFrame jFrame){
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

        container.setLayout(new BorderLayout(10,5));
        container.add(createHeader(),BorderLayout.NORTH);
        container.add(createFooter(), BorderLayout.SOUTH);
        container.add(panel1, BorderLayout.CENTER);




    }
}
