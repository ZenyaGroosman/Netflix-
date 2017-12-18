package nl.avans.frames;

import nl.avans.Main;
import nl.avans.SelectedAccount;
import nl.avans.frames.activityListeners.ClickListenerStatestiekenSerie;

import javax.swing.*;
import java.awt.*;

public class UserInterfaceStatistix extends UserInterfaceBase {
    public UserInterfaceStatistix(JFrame jFrame) {
        super(jFrame);
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
        container.add(createBody(), BorderLayout.CENTER);

        container.add(createFooter(), BorderLayout.SOUTH);
    }



    private JPanel createBody() {
        JPanel panel = new JPanel(new BorderLayout(3, 0));
        panel.add(createSidePanel(), BorderLayout.WEST);
        panel.add(createSerieStatestieken(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSidePanel() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 1));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        JButton seriesStats = new JButton("Serie statestieken");
        panel.add(seriesStats);
        panel.add(new JButton("Film statestieken"));
        panel.add(new JButton("Account statestieken"));
        JButton perStat = new JButton("Persoonlijke statestieken");
        if (SelectedAccount.getSelectedAccount() == null)
            perStat.setEnabled(false);
        panel.add(perStat);
        grid.add(panel);
        return grid;
    }

    private JPanel createSerieStatestieken() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonsAndSelect = new JPanel();
        buttonsAndSelect.setLayout(new BoxLayout(buttonsAndSelect, BoxLayout.Y_AXIS));

        JPanel selectSeries = new JPanel();
        selectSeries.setLayout(new BoxLayout(selectSeries, BoxLayout.X_AXIS));
        selectSeries.add(new JLabel("Selecteer een serie"));
        String[] series = new String[Main.programmaSupplier.getSeries().size()];
        for (int i = 0; i < Main.programmaSupplier.getSeries().size(); i++) {
            series[i] = Main.programmaSupplier.getSeries().get(i).getTitel();
        }
        JComboBox seriesList = new JComboBox<String>(series);
        selectSeries.add(seriesList);
        buttonsAndSelect.add(selectSeries);


        JPanel selectFunctie = new JPanel();
        selectFunctie.setLayout(new GridLayout(1, 2));
        JButton watchPercentage = new JButton("Gemiddelde kijkpercentages");
        JButton watchPercentagePerUser = new JButton("Gemiddelde kijkpercentages voor account");
        selectFunctie.add(new JLabel());
        selectFunctie.add(watchPercentage);
        selectFunctie.add(watchPercentagePerUser);
        buttonsAndSelect.add(selectFunctie);
        panel.add(buttonsAndSelect, BorderLayout.NORTH);
        JPanel resultGrid = new JPanel();
        panel.add(resultGrid, BorderLayout.CENTER);
        watchPercentage.addActionListener(new ClickListenerStatestiekenSerie(resultGrid, seriesList, getFrame()));

        return panel;
    }
}
