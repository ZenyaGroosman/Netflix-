package nl.avans.userInterfaces.actionListener;


import nl.avans.misc.CustomNumberFormatter;
import nl.avans.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;

public class ActionListenerStatistixSelectMovie implements ActionListener {
    private JPanel panel;
    private JFrame frame;

    public ActionListenerStatistixSelectMovie(JPanel panel, JFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }

    /**
     * gets callend when the side button gets pressed and when the options combobox is changed
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        JPanel optionSelection = new JPanel();
        optionSelection.setLayout(new BoxLayout(optionSelection, BoxLayout.Y_AXIS));

        //creates a combobox with all statistical functions
        JPanel selectMovieOption = new JPanel();
        selectMovieOption.setLayout(new BoxLayout(selectMovieOption, BoxLayout.X_AXIS));
        selectMovieOption.add(new JLabel("Selecteer een functie"));
        String[] series = new String[]{"Kijkpercentage", "Leeftijds Limiet"};
        JComboBox movieOptionList = new JComboBox<String>(series);

        selectMovieOption.add(movieOptionList);
        optionSelection.add(selectMovieOption);
        panel.add(optionSelection, BorderLayout.NORTH);
        panel.add(new JLabel(), BorderLayout.CENTER);
        //checks if the source is from the button or from the combobox
        // to avoid an infinite loop
        if (e.getSource() instanceof JButton){
            movieOptionList.addActionListener(new ActionListenerStatistixSelectMovie(optionSelection, frame));
            movieOptionList.setSelectedIndex(0);
        }

        if (e.getSource() instanceof JComboBox) {
            JComboBox<String> comboBox = (JComboBox) e.getSource();
            movieOptionList.setSelectedItem(comboBox.getSelectedItem());
            movieOptionList.addActionListener(this);
            panel.add(selectMovieOption);

            //switch for checking witch function is selected
            switch ((String) Objects.requireNonNull(comboBox.getSelectedItem())) {
                case "Kijkpercentage": {
                    createMovieByWatchPercentage();
                    break;
                }
                case "Leeftijds Limiet":{
                    createMovieByAgeLimit();
                    break;
                }
            }
        }
        frame.repaint();
        frame.revalidate();
    }

    /**
     * adds all the elements to show movies restricted by ageLimit
     */
    private void createMovieByAgeLimit(){

        JPanel panelLabelAndTextField = new JPanel();
        panelLabelAndTextField.setLayout(new BoxLayout(panelLabelAndTextField, BoxLayout.X_AXIS));

        JLabel jLabel = new JLabel("Vul leeftijdslimiet in: ");
        panelLabelAndTextField.add(jLabel);

        //Number restricted text field
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        CustomNumberFormatter numberFormatter = new CustomNumberFormatter(longFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);

        JFormattedTextField limit = new JFormattedTextField(numberFormatter);
        JPanel resultGrid = new JPanel();
        limit.addActionListener(new ActionListenerStatistixMovieAge(limit, resultGrid, frame));
        panelLabelAndTextField.add(limit);
        limit.setValue(0);
        //End number restricted text field

        panel.add(panelLabelAndTextField);
        panel.add(resultGrid);
    }

    /**
     * adds all the elements to show movies restricted by watch percentage
     */
    private void createMovieByWatchPercentage() {
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
        JPanel resultGrid = new JPanel();
        seriesList.addActionListener(new ActionListenerStatistixMoviePercentage(limit, slider, frame, resultGrid, seriesList));
        slider.addChangeListener(new ActionListenerStatistixMoviePercentage(limit, slider, frame, resultGrid, seriesList));
        limit.addActionListener(new ActionListenerStatistixMoviePercentage(limit, slider, frame, resultGrid, seriesList));
        seriesList.setSelectedIndex(0);
        slider.setToolTipText("Selecteer percentage");
        panel.add(panelLabelAndCombobox);
        panel.add(slider);
        panel.add(resultGrid);
    }
}
