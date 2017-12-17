package nl.avans.sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountSupplier {
    private ArrayList<Account> accounts = new ArrayList<>();
    private SQLConnection sqlConnection;

    public AccountSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void refresh() throws SQLException {
        accounts.clear();
        makeAccounts();
    }

    public void makeAccounts() throws SQLException {
        ResultSet accountsEnProfielen = sqlConnection.executeSql("SELECT * FROM Profiel LEFT JOIN Account ON Account.Abonneenummer = Profiel.Abonneenummer;");
        while (accountsEnProfielen.next()) {
            Date geboortedatum = accountsEnProfielen.getDate("Geboortedatum");
            String profielnaam = accountsEnProfielen.getString("Profielnaam");
            Profiel profiel = new Profiel(geboortedatum, profielnaam);

            int aboneenr = accountsEnProfielen.getInt("Abonneenummer");
            String naam = accountsEnProfielen.getString("Naam");
            String straat = accountsEnProfielen.getString("Straat");
            String postcode = accountsEnProfielen.getString("Postcode");
            String plaats = accountsEnProfielen.getString("Plaats");
            int huisnummer = accountsEnProfielen.getInt("Huisnummer");
            Account account = new Account(aboneenr, naam, straat, postcode, huisnummer, plaats);
            if (!accounts.contains(account)) {
                account.addProfiel(profiel);
                accounts.add(account);
            } else {
                int i = accounts.indexOf(account);
                accounts.get(i).addProfiel(profiel);
            }
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
