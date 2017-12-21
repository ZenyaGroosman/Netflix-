package nl.avans.userInterfaces.actionListener;


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


        if (e.getSource() instanceof JComboBox) {
            JComboBox<String> comboBox = (JComboBox) e.getSource();
            JPanel selectMovieOption = new JPanel();
            selectMovieOption.setLayout(new BoxLayout(selectMovieOption, BoxLayout.X_AXIS));
            selectMovieOption.add(new JLabel("Selecteer een funcite"));
            String[] series = new String[]{"Bekeken", "Films kijkpercentage"};
            JComboBox movieOptionList = new JComboBox<String>(series);
            movieOptionList.setSelectedItem(comboBox.getSelectedItem());
            movieOptionList.addActionListener(new FunctionSelectMovieListener(panel, frame));
            selectMovieOption.add(movieOptionList);
            panel.add(selectMovieOption);
            switch ((String) Objects.requireNonNull(comboBox.getSelectedItem())){
                case  "Films kijkpercentage": {
                    JPanel selectMovie = new JPanel();
                    selectMovie.setLayout(new BoxLayout(selectMovie, BoxLayout.X_AXIS));
                    selectMovie.add(new JLabel("Kies een serie"));
                    String[] Movies = new String[Main.programSupplier.getFilms().size()];
                    for (int i = 0; i < Main.programSupplier.getFilms().size(); i++) {
                        Movies[i] = Main.programSupplier.getFilms().get(i).getTitle();
                    }
                    JComboBox seriesList = new JComboBox<String>(Movies);
                    selectMovie.add(seriesList);
                    panel.add(selectMovie);

                    JPanel selectPercentage;
                    break;
                }
            }
        }
        frame.repaint();
        frame.revalidate();

    }
}
