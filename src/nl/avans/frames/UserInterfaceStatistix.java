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
        JLabel jLabel = new JLabel("Informatica | Jaar 1 | Klas C | Dion Klaassen, Zenya Groosman, Bart Klomp ");
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        container.add(jLabel, BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        JButton account = new JButton("Account");
        JButton profiel = new JButton("Profiel");
        JButton programmas = new JButton("Programma's");
        JButton bekekenProgrammas = new JButton("Bekeken programma's");
        JButton statestieken = new JButton("Statestieken");
        if (SelectedAccount.getSelectedAccount() == null)
            profiel.setEnabled(false);
        if (SelectedAccount.getSelectedProfiel() == null)
            bekekenProgrammas.setEnabled(false);
        panel.add(account);
        panel.add(profiel);
        panel.add(programmas);
        panel.add(bekekenProgrammas);
        panel.add(statestieken);
        return panel;
    }

    private JPanel createBody() {
        JPanel panel = new JPanel(new BorderLayout(3, 0));
        panel.add(createSidePanel(), BorderLayout.WEST);
        panel.add(createSerieStatestieken(), BorderLayout.CENTER);
        return panel;
    }
    private JPanel createSidePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JButton("Serie statestieken"));
        panel.add(new JButton("Film statestieken"));
        panel.add(new JButton("Account statestieken"));
        JButton perStat = new JButton("Persoonlijke statestieken");
        if (SelectedAccount.getSelectedAccount() == null)
            perStat.setEnabled(false);
        panel.add(perStat);
        return panel;
    }

    private JPanel createSerieStatestieken(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel selectSerie = new JPanel();
        selectSerie.setLayout(new BoxLayout(selectSerie, BoxLayout.X_AXIS));
        selectSerie.add(new JLabel("Selecteer een serie"));
        String[] series = new String[Main.programmaSupplier.getSeries().size()];
        for (int i = 0; i < Main.programmaSupplier.getSeries().size(); i++){
            series[i] = Main.programmaSupplier.getSeries().get(i).getTitel();
        }
        JComboBox serieLijst = new JComboBox<String>(series);
        selectSerie.add(serieLijst);
        panel.add(selectSerie);

        JPanel selectFunctie = new JPanel();
        selectFunctie.setLayout(new BoxLayout(selectFunctie, BoxLayout.X_AXIS));
        JButton kijkPercentage = new JButton("Kijkpercentages");
        selectFunctie.add(kijkPercentage);
        selectFunctie.add(new JButton("test"));
        panel.add(selectFunctie);
        JTextArea textArea= new JTextArea();
        textArea.setEnabled(false);
        panel.add(textArea);
        kijkPercentage.addActionListener(new ClickListenerStatestiekenSerie(textArea, serieLijst));

        return panel;
    }
}
