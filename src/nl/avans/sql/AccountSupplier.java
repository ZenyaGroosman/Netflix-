package nl.avans.sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


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
            Profile profiel = new Profile(geboortedatum, profielnaam);

            int aboneenr = accountsEnProfielen.getInt("Abonneenummer");
            String naam = accountsEnProfielen.getString("Naam");
            String straat = accountsEnProfielen.getString("Straat");
            String postcode = accountsEnProfielen.getString("Postcode");
            String plaats = accountsEnProfielen.getString("Plaats");
            int huisnummer = accountsEnProfielen.getInt("Huisnummer");
            Account account = new Account(aboneenr, naam, straat, postcode, huisnummer, plaats);
            if (!accounts.contains(account)) {
                account.addProfile(profiel);
                accounts.add(account);
            } else {
                int i = accounts.indexOf(account);
                accounts.get(i).addProfile(profiel);
            }
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public boolean createAccount(Account account) {
        String[] columns = new String[]{"Abonneenummer", "Naam", "Straat", "Postcode", "Plaats", "Huisnummer"};
        Object[] value = new Object[]{account.getId(), account.getNaam(), account.getStreet(), account.getPostcode(), account.getPlace(), account.getHouseNumber()};
        boolean success = SQLHelper.createObject("Account", columns, value);

        if (success) {
            accounts.add(account);
        }
        return success;
    }

    public boolean deleteAccount(Account account) {
        String[] columns = new String[]{"Abonneenummer"};
        Object[] value = new Object[]{account.getId()};
        boolean success = SQLHelper.deleteObject("Account", columns, value);
        if (success) {
            accounts.remove(account);
        }

        return success;
    }

    public boolean createProfiel(Profile profiel, int accountId) {
        String[] columns = new String[]{"Abonneenummer", "Profielnaam", "Geboortedatum"};
        Object[] value = new Object[]{accountId, profiel.getProfielnaam(), profiel.getGeboortedatum()};
        boolean success = SQLHelper.createObject("Profil", columns, value);

        if (success) {
            Iterator<Account> iterator = accounts.iterator();
            while (iterator.hasNext()) {
                Account account = iterator.next();
                if (account.getId() == accountId) {
                    account.addProfile(profiel);
                }
            }
        }
        return success;
    }

    public boolean deleteProfiel(Profile profiel, int accountId) {
        String[] columns = new String[]{"Abonneenummer", "Profielnaam"};
        Object[] value = new Object[]{accountId, profiel.getProfielnaam()};
        boolean success = SQLHelper.deleteObject("Profiel", columns, value);
        if (success) {
            Iterator<Account> iterator = accounts.iterator();
            while (iterator.hasNext()) {
                Account account = iterator.next();
                if (account.getId() == accountId) {
                    account.removeProfiel(profiel);
                }
            }
        }

        return success;
    }
}
