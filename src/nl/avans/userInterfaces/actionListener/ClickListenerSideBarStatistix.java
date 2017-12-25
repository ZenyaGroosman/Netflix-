package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerSideBarStatistix implements ActionListener {
    private Container container;
    private JFrame frame;

    public ClickListenerSideBarStatistix(Container container, JFrame frame) {
        this.container = container;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        BorderLayout layout = (BorderLayout) container.getLayout();
        Component component = layout.getLayoutComponent(BorderLayout.CENTER);
        if (component != null)
            container.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        switch (button) {
            case "Serie statestieken": {
                container.add(createSeriesStats(), BorderLayout.CENTER);
                break;
            }
            case "Film statestieken": {
                container.add(createMovieStats(), BorderLayout.CENTER);
                break;
            }
        }
        frame.repaint();
        frame.revalidate();

    }


    /**
     * creates the panel for the movie stats
     *
     * @return the panel
     */
    private JPanel createMovieStats() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel optionSelection = new JPanel();
        optionSelection.setLayout(new BoxLayout(optionSelection, BoxLayout.Y_AXIS));

        JPanel selectMovieOption = new JPanel();
        selectMovieOption.setLayout(new BoxLayout(selectMovieOption, BoxLayout.X_AXIS));
        selectMovieOption.add(new JLabel("Selecteer een functie"));
        String[] series = new String[]{"Films kijkpercentage"};
        JComboBox movieOptionList = new JComboBox<String>(series);
        movieOptionList.addActionListener(new ClickListenerSelectMovieListener(optionSelection, frame));

        selectMovieOption.add(movieOptionList);
        optionSelection.add(selectMovieOption);
        panel.add(optionSelection, BorderLayout.NORTH);
        panel.add(new JLabel(), BorderLayout.CENTER);
        movieOptionList.setSelectedIndex(0);

        return panel;
    }

    /**
     * creates the panel for the movie serie
     *
     * @return the panel
     */
    private JPanel createSeriesStats() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonsAndSelect = new JPanel();
        buttonsAndSelect.setLayout(new BoxLayout(buttonsAndSelect, BoxLayout.Y_AXIS));

        JPanel selectSeries = new JPanel();
        selectSeries.setLayout(new BoxLayout(selectSeries, BoxLayout.X_AXIS));
        selectSeries.add(new JLabel("Selecteer een serie"));
        String[] series = new String[Main.programSupplier.getSeries().size()];
        for (int i = 0; i < Main.programSupplier.getSeries().size(); i++) {
            series[i] = Main.programSupplier.getSeries().get(i).getTitel();
        }
        JComboBox seriesList = new JComboBox<String>(series);
        selectSeries.add(seriesList);
        buttonsAndSelect.add(selectSeries);


        JPanel selectFunctions = new JPanel();
        selectFunctions.setLayout(new GridLayout(1, 2));
        JButton watchPercentage = new JButton("Gemiddelde kijkpercentages");
        selectFunctions.add(new JLabel());
        selectFunctions.add(watchPercentage);
        buttonsAndSelect.add(selectFunctions);
        panel.add(buttonsAndSelect, BorderLayout.NORTH);
        JPanel resultGrid = new JPanel();
        panel.add(resultGrid, BorderLayout.CENTER);
        watchPercentage.addActionListener(new ClickListenerStatistixSerie(resultGrid, seriesList, frame));

        return panel;
    }

}
