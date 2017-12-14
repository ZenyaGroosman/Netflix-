package nl.avans.frames;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterfaceBekekenProgrammas implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Bekeken Programma's");
        frame.setPreferredSize(new Dimension(900, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
    }

    public JFrame getFrame() {
        return frame;
    }
}