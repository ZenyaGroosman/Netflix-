package nl.avans.frames.activityListeners;

import nl.avans.Main;
import nl.avans.sql.SQLHelper;
import nl.avans.sql.Serie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClickListenerStatestiekenSerie implements ActionListener {
    private JTextArea textArea;
    private JComboBox serieLijst;

    public ClickListenerStatestiekenSerie(JTextArea textArea, JComboBox serieLijst) {
        this.textArea = textArea;
        this.serieLijst = serieLijst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        switch (button) {
            case "Kijkpercentages": {
                ResultSet set = SQLHelper.sqlConnection.executeSql("SELECT Aflevering.Titel, AVG(Percentage) AS 'Percentage' FROM Aflevering JOIN Bekeken ON Aflevering.Id = Bekeken.Gezien WHERE Aflevering.Serie = '" + serieLijst.getSelectedItem().toString() + "' GROUP BY Aflevering.Titel;");
                textArea.setText("");
                try {
                    if (set != null)
                        while (set.next()) {
                            String s = set.getString("Titel") + " " + set.getString("Percentage") + "%";
                            textArea.append(s);
                            textArea.append("\n");
                        }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }

    }
}
