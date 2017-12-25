package nl.avans.userInterfaces;

import javax.swing.*;
import java.awt.*;

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
        Font f = new Font("default", Font.PLAIN, 40);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.NORTH);

        JLabel label = new JLabel("Account Overzicht");

        label.setHorizontalAlignment(SwingConstants.CENTER);

        grid.add(label);

        container.add(panel);

        label.setFont(f);

    }







}
