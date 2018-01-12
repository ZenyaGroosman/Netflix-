package nl.avans;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class AccountOverzicht implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        // Create your app here]
        frame = new JFrame("Title");
        frame.setPreferredSize(new Dimension(1500, 750));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    private void createComponents(Container container) {

        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Are you?"));
        JCheckBox check = new JCheckBox("yes213!");
        JCheckBox check2 = new JCheckBox("yes123123!");
        JCheckBox check3 = new JCheckBox("ye123123s!");
        JCheckBox checkbox = new JCheckBox("No!");

        container.add(check);
        container.add(check2);
        container.add(check3);
        container.add(checkbox);

        ButtonGroup buttonGroup = new ButtonGroup();
        container.add(new JLabel("Why?"));

        JRadioButton Menubar = new JRadioButton("accountoverzicht");
        JRadioButton fun = new JRadioButton("Because its fun!");

        buttonGroup.add(Menubar);
        buttonGroup.add(fun);

        container.add(Menubar);
        container.add(fun);

        JButton button = new JButton("Done!");
        container.add(button);
    }


    public JFrame getFrame() {
        return frame;
    }
}