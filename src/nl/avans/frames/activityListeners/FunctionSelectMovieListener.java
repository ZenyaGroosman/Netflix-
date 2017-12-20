package nl.avans.frames.activityListeners;


import nl.avans.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FunctionSelectMovieListener implements ActionListener {
    private JPanel panel;
    private JFrame frame;

    public FunctionSelectMovieListener(JPanel panel, JFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        JPanel selectMovieOption = new JPanel();
        selectMovieOption.setLayout(new BoxLayout(selectMovieOption, BoxLayout.X_AXIS));
        selectMovieOption.add(new JLabel("Selecteer een funcite"));
        String[] series = new String[]{"Bekeken", "Films kijkpercentage"};
        JComboBox movieOptionList = new JComboBox<String>(series);
        movieOptionList.addActionListener(new FunctionSelectMovieListener(panel, frame));
        selectMovieOption.add(movieOptionList);
        panel.add(selectMovieOption);

        if (e.getSource() instanceof JComboBox) {
            JComboBox<String> comboBox = (JComboBox) e.getSource();
            switch ((String) Objects.requireNonNull(comboBox.getSelectedItem())){
                case  "Bekeken":{
                    JPanel selectMovie = new JPanel();
                    selectMovie.setLayout(new BoxLayout(selectMovie, BoxLayout.X_AXIS));
                    selectMovie.add(new JLabel("Kies een serie"));
                    String[] Movies = new String[Main.programmaSupplier.getFilms().size()];
                    for (int i = 0; i < Main.programmaSupplier.getFilms().size(); i++) {
                        Movies[i] = Main.programmaSupplier.getFilms().get(i).getTitle();
                    }
                    JComboBox seriesList = new JComboBox<String>(Movies);
                    selectMovie.add(seriesList);
                    panel.add(selectMovie);
                    break;
                }
            }
        }
        frame.repaint();
        frame.revalidate();

    }
}
