package nl.avans;

import nl.avans.userInterfaces.UserInterfaceStatistix;
import nl.avans.userInterfaces.UserInterfaceBase;
import nl.avans.sql.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main{
    public static ProgramSupplier programmaSupplier;
    public static AccountSupplier accountSupplier;

    public static void main(String[] args) throws SQLException {
        // write your code here

        SQLConnection connection = new SQLConnection();
        connection.connectDatabase("jdbc:sqlserver://statistixnetflix.database.windows.net:1433;database=Netflix;user=informatica@statistixnetflix;password=Avans12345;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        SQLHelper.sqlConnection = connection;
        programmaSupplier = new ProgramSupplier(connection);
        programmaSupplier.makePrograms();
        accountSupplier = new AccountSupplier(connection);
        accountSupplier.makeAccounts();
        JFrame jFrame = new JFrame("Netflix Statistix");
        jFrame.setPreferredSize(new Dimension(700, 600));

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UserInterfaceBase ui = new UserInterfaceStatistix(jFrame);
        SwingUtilities.invokeLater(ui);

//        AccountSupplier accountSupplier = new AccountSupplier(connection);
//        accountSupplier.makeAccounts();
//        for (Account acc : accountSupplier.getAccounts())
//            System.out.println(acc.getNaam());
//        connection.disconnectDatabase();
    }

}