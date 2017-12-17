package nl.avans.frames;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UIProgramOverview extends JFrame{

    private JFrame f;
    private JPanel p;

    public UIProgramOverview(){

       ProgramOverview();

    }
    public void ProgramOverview(){
        f = new JFrame("Program Overview");
        f.setVisible(true);
        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p = new JPanel();
        p.setBackground(Color.darkGray);

        f.add(p);




    }
}
