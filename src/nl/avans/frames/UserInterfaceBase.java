package nl.avans.frames;

import javax.swing.*;

public abstract class UserInterfaceBase implements Runnable{
    private JFrame jFrame;

    public UserInterfaceBase(JFrame jFrame){

        this.jFrame = jFrame;
    }

    public abstract void run();

    public abstract void createComponents();

    public JFrame getFrame() {
        return jFrame;
    }
}
