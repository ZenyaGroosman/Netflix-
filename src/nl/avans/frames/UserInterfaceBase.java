package nl.avans.frames;

import javax.swing.*;

public abstract class UserInterfaceBase implements Runnable{
    private JFrame jFrame;


    public abstract void run();

    public abstract void createComponetns();

    public JFrame getFrame() {
        return jFrame;
    }
}
