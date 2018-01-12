package nl.avans.userInterfaces.actionListener;

import nl.avans.Main;
import nl.avans.sql.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerStatistixSideBar implements ActionListener {
    private Container container;
    private JFrame frame;

    /**
     * This class is responsible for listening to the sidebar buttons. These buttons all cause the main part of the
     * window to display the propper functions
     * @param container
     * @param frame
     */
    public ActionListenerStatistixSideBar(Container container, JFrame frame) {
        this.container = container;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        container.removeAll();
        switch (button) {
            case "Serie statestieken": {
                container.add(createSeriesStats());
                break;
            }
        }
        frame.repaint();
        frame.revalidate();

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

        JPanel selectAccount = new JPanel();
        selectAccount.setLayout(new BoxLayout(selectAccount, BoxLayout.X_AXIS));
        selectAccount.add(new JLabel("Selecteer een account"));
        Account[] accounts = new Account[Main.accountSupplier.getAccounts().size() + 1];
        accounts[0] = new Account(0, "Alle accounts", "", "", 0, "");
        for (int i = 0; i < Main.accountSupplier.getAccounts().size(); i++) {
            accounts[i + 1] = Main.accountSupplier.getAccounts().get(i);
        }
        JComboBox<Account> accountList = new JComboBox<Account>(accounts);
        selectAccount.add(accountList);
        buttonsAndSelect.add(selectAccount);

        JPanel selectFunctions = new JPanel();
        selectFunctions.setLayout(new GridLayout(1, 2));
        JButton watchPercentage = new JButton("Gemiddelde kijkpercentages");
        selectFunctions.add(new JLabel());
        selectFunctions.add(watchPercentage);
        buttonsAndSelect.add(selectFunctions);
        panel.add(buttonsAndSelect, BorderLayout.NORTH);
        JPanel resultGrid = new JPanel();
        panel.add(resultGrid, BorderLayout.CENTER);
        watchPercentage.addActionListener(new ActionListenerStatistixSerie(resultGrid, seriesList,accountList, frame));

        return panel;
    }

}
