package nl.avans;

import nl.avans.misc.SelectedAccount;
import nl.avans.sql.AccountSupplier;
import nl.avans.sql.ProgramSupplier;
import nl.avans.sql.SQLConnection;
import nl.avans.sql.SQLHelper;
import nl.avans.userInterfaces.UIAccountOverview;
//import nl.avans.userInterfaces.UIProfielOverview;
import nl.avans.userInterfaces.UserInterfaceBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static ProgramSupplier programSupplier;
    public static AccountSupplier accountSupplier;
    public static UIAccountOverview UIAccountOverview;

    public static void main(String[] args) throws SQLException {
        // write your code here


        SQLConnection connection = new SQLConnection();
        connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

        SQLHelper.sqlConnection = connection;
        programSupplier = new ProgramSupplier(connection);
        programSupplier.makeProgramsAndSeries();
        accountSupplier = new AccountSupplier(connection);
        accountSupplier.makeAccounts();
        SelectedAccount.setSelectedAccount(accountSupplier.getAccounts().get(0));
        SelectedAccount.setSelectedProfile(accountSupplier.getAccounts().get(0).getProfiles().get(0));
        JFrame jFrame = new JFrame("Netflix Statistix");
        jFrame.setPreferredSize(new Dimension(900, 600));

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UserInterfaceBase ui = new UIAccountOverview(jFrame);
        SwingUtilities.invokeLater(ui);
    }

}