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
        ResultSet accountsAndProfiles = sqlConnection.executeSql("SELECT * FROM Profiel LEFT JOIN Account ON Account.Abonneenummer = Profiel.Abonneenummer;");

        while (accountsAndProfiles.next()) {
            Date birthday = accountsAndProfiles.getDate("Geboortedatum");
            String profileName = accountsAndProfiles.getString("Profielnaam");
            Profile profile = new Profile(birthday, profileName);

            int id = accountsAndProfiles.getInt("Abonneenummer");
            String name = accountsAndProfiles.getString("Naam");
            String street = accountsAndProfiles.getString("Straat");
            String postcode = accountsAndProfiles.getString("Postcode");
            String place = accountsAndProfiles.getString("Plaats");
            int houseNumber = accountsAndProfiles.getInt("Huisnummer");
            Account account = new Account(id, name, street, postcode, houseNumber, place);
            if (!accounts.contains(account)) {
                account.addProfile(profile);
                accounts.add(account);
            } else {
                int i = accounts.indexOf(account);
                accounts.get(i).addProfile(profile);
            }
            ResultSet watched = sqlConnection.executeSql("SELECT Gezien, Percentage FROM Profiel JOIN Bekeken ON Profiel.Profielnaam = Bekeken.Profielnaam AND Profiel.Abonneenummer = Bekeken.Abonneenummer WHERE Profiel.Abonneenummer = " + id + " AND Profiel.Profielnaam = '" + profileName + "'");
            while (watched.next()){
                WatchedProgram watchedProgram = new WatchedProgram(watched.getInt("Percentage"), watched.getInt("Gezien"));
                profile.addProgram(watchedProgram);
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
