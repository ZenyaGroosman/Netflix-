package nl.avans.sql;

import nl.avans.Main;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/*This class is responsible for fetching all the accounts, profiles and watched programs from the database
* it also proides the ability to create, delete and edit accounts, profiles and watched programs
* */
public class AccountSupplier {
    private ArrayList<Account> accounts = new ArrayList<>();
    private SQLConnection sqlConnection;
    private ArrayList<WatchedProgram> watchedPrograms = new ArrayList<>();

    public AccountSupplier(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //this function fetches all the data about accounts, profiles and watched programs from the database and creates objects containing that data
    public void makeAccounts() throws SQLException {
        ResultSet accountsAndProfiles = sqlConnection.executeSql("SELECT Account.Abonneenummer, Naam, Straat, Postcode, Plaats, Huisnummer, Geboortedatum, Profielnaam FROM Profiel FULL JOIN Account ON Account.Abonneenummer = Profiel.Abonneenummer;");

        while (accountsAndProfiles.next()) {

            int id = accountsAndProfiles.getInt("Abonneenummer");
            String name = accountsAndProfiles.getString("Naam");
            String street = accountsAndProfiles.getString("Straat");
            String postcode = accountsAndProfiles.getString("Postcode");
            String place = accountsAndProfiles.getString("Plaats");
            int houseNumber = accountsAndProfiles.getInt("Huisnummer");
            Account account = new Account(id, name, street, postcode, houseNumber, place);

            Date birthday = accountsAndProfiles.getDate("Geboortedatum");
            String profileName = accountsAndProfiles.getString("Profielnaam");
            Profile profile = new Profile(birthday, profileName, account);
            if (!accounts.contains(account)) {
                account.addProfile(profile);
                accounts.add(account);
            } else {
                int i = accounts.indexOf(account);
                accounts.get(i).addProfile(profile);
            }
            ResultSet watched = sqlConnection.executeSql("SELECT Gezien, Percentage FROM Profiel JOIN Bekeken ON Profiel.Profielnaam = Bekeken.Profielnaam AND Profiel.Abonneenummer = Bekeken.Abonneenummer WHERE Profiel.Abonneenummer = " + id + " AND Profiel.Profielnaam = '" + profileName + "'");
            while (watched.next()) {
                WatchedProgram watchedProgram = new WatchedProgram(watched.getInt("Percentage"), Main.programSupplier.getProgramById(watched.getInt("Gezien")), profile);
                watchedPrograms.add(watchedProgram);
            }
        }


    }

    public ArrayList<WatchedProgram> getWatchedPrograms() {
        return watchedPrograms;
    }

    public boolean addWatchedPrograms(WatchedProgram watchedProgram) {
        String[] columns = new String[]{"Abonneenummer", "ProfielNaam", "Gezien", "Percentage"};
        Object[] value = new Object[]{watchedProgram.getProfile().getAccount().getId(), watchedProgram.getProfile().getProfileName(), watchedProgram.getProgram().getId(), watchedProgram.getPercentage()};
        boolean success = SQLHelper.createObject("Bekeken", columns, value);

        if (success) {
            watchedPrograms.add(watchedProgram);
        }
        return success;
    }

    public boolean removeWatchedPrograms(WatchedProgram watchedProgram) {
        String[] columns = new String[]{"Abonneenummer", "ProfielNaam", "Gezien", "Percentage"};
        Object[] value = new Object[]{watchedProgram.getProfile().getAccount().getId(), watchedProgram.getProfile().getProfileName(), watchedProgram.getProgram().getId(), watchedProgram.getPercentage()};
        boolean success = SQLHelper.deleteObject("Bekeken", columns, value);

        if (success) {
            watchedPrograms.remove(watchedProgram);
        }
        return success;
    }

    public boolean updateWatchedPrograms(WatchedProgram oldWatchedProgram, WatchedProgram newWatchedProgram) {
        String[] columns = new String[]{"Abonneenummer", "ProfielNaam", "Gezien", "Percentage"};
        Object[] oldValue = new Object[]{oldWatchedProgram.getProfile().getAccount().getId(), oldWatchedProgram.getProfile().getProfileName(), oldWatchedProgram.getProgram().getId(), oldWatchedProgram.getPercentage()};
        Object[] newValues = new Object[]{newWatchedProgram.getProfile().getAccount().getId(), newWatchedProgram.getProfile().getProfileName(), newWatchedProgram.getProgram().getId(), newWatchedProgram.getPercentage()};
        boolean success = SQLHelper.editObject("Bekeken", columns, oldValue, newValues);

        if (success) {
            watchedPrograms.remove(oldWatchedProgram);
            watchedPrograms.add(newWatchedProgram);
        }
        return success;
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
    public boolean updateAccount(Account oldAccount, Account newAccount) {
        String[] columns = new String[]{"Abonneenummer", "Naam", "Straat", "Postcode", "Plaats", "Huisnummer"};
        Object[] oldValue = new Object[]{oldAccount.getId(), oldAccount.getNaam(), oldAccount.getStreet(), oldAccount.getPostcode(), oldAccount.getPlace(), oldAccount.getHouseNumber()};
        Object[] newValue = new Object[]{newAccount.getId(), newAccount.getNaam(), newAccount.getStreet(), newAccount.getPostcode(), newAccount.getPlace(), newAccount.getHouseNumber()};
        boolean success = SQLHelper.editObject("Account", columns, oldValue, newValue);

        if (success) {
            for (Profile f:oldAccount.getProfiles())
                newAccount.addProfile(f);
        }
        return success;
    }

    public boolean createProfile(Profile profile) {
        String[] columns = new String[]{"Abonneenummer", "Profielnaam", "Geboortedatum"};
        Object[] value = new Object[]{profile.getAccount().getId(), profile.getProfileName(), profile.getBirthday()};
        boolean success = SQLHelper.createObject("Profile", columns, value);

        if (success) {
            profile.getAccount().addProfile(profile);
        }
        return success;
    }

    public boolean updateProfile(Profile oldProfile, Profile newProfile) {
        String[] columns = new String[]{"Abonneenummer", "Profielnaam", "Geboortedatum"};
        Object[] oldValue = new Object[]{oldProfile.getAccount().getId(), oldProfile.getProfileName(), oldProfile.getBirthday()};
        Object[] newValue = new Object[]{newProfile.getAccount().getId(), newProfile.getProfileName(), newProfile.getBirthday()};
        boolean success = SQLHelper.editObject("Profile", columns, oldValue, newValue);

        if (success) {
            oldProfile.getAccount().addProfile(newProfile);
            oldProfile.getAccount().removeProfiel(oldProfile);
        }
        return success;
    }

    public boolean deleteProfile(Profile profiel, int accountId) {
        String[] columns = new String[]{"Abonneenummer", "Profielnaam"};
        Object[] value = new Object[]{accountId, profiel.getProfileName()};
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
