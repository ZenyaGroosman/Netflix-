package nl.avans.userInterfaces;

import nl.avans.Main;
import nl.avans.SelectedAccount;
import nl.avans.userInterfaces.actionListener.ClickListenerStatestiekenSerie;
import nl.avans.userInterfaces.actionListener.FunctionSelectMovieListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UIAccountOverview extends UserInterfaceBase{
    public UIAccountOverview(JFrame jFrame){
        super (jFrame);
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
        titel(container);
    }

    private void titel(Container container) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 3));

        panel.add(grid, BorderLayout.NORTH);


        grid.add(new JLabel("Account Overzicht"));
        container.add(panel);

        
    }







}
