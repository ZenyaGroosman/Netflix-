package nl.avans.userInterfaces.actionListener;


import nl.avans.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClickListenerSelectMovieListener implements ActionListener {
    private JPanel panel;
    private JFrame frame;

    public ClickListenerSelectMovieListener(JPanel panel, JFrame frame) {
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
            movieOptionList.addActionListener(new ClickListenerSelectMovieListener(panel, frame));
            selectMovieOption.add(movieOptionList);
            panel.add(selectMovieOption);
            switch ((String) Objects.requireNonNull(comboBox.getSelectedItem())){
                case  "Films kijkpercentage": {
                    fillMovieByWatchPercentage();
                    break;
                }
            }
        }
        frame.repaint();
        frame.revalidate();
    }

    private void fillMovieByWatchPercentage(){
        JPanel selectMovie = new JPanel();
        selectMovie.setLayout(new BoxLayout(selectMovie, BoxLayout.X_AXIS));
        selectMovie.add(new JLabel("Kies een film: "));
        String[] Movies = new String[Main.programSupplier.getFilms().size()];
        for (int i = 0; i < Main.programSupplier.getFilms().size(); i++) {
            Movies[i] = Main.programSupplier.getFilms().get(i).getTitle();
        }

        JComboBox seriesList = new JComboBox<String>(Movies);
        selectMovie.add(seriesList);
        panel.add(selectMovie);

        JPanel panelLabelAndCombobox = new JPanel();
        panelLabelAndCombobox.setLayout(new BoxLayout(panelLabelAndCombobox, BoxLayout.X_AXIS));

        JLabel jLabel = new JLabel("Selecteer percentage limiet: ");
        panelLabelAndCombobox.add(jLabel);
        JComboBox<String> limit = new JComboBox<>(new String[]{"Minimaal", "Maximaal"});
        panelLabelAndCombobox.add(limit);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(panelLabelAndCombobox);
        panel.add(slider);
    }
}
