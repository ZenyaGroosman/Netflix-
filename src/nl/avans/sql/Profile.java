package nl.avans.sql;

import java.sql.Date;

//This class is responsable for keeping track of all the data about profiles
public class Profile {
    private Date birthDate;
    private String profileName;
    private Account account;

    public Profile(Date birthDate, String profileName, Account account) {
        this.birthDate = birthDate;
        this.profileName = profileName;

        this.account = account;
    }

    public Date getBirthday() {
        return birthDate;
    }

    public String getProfileName() {
        return profileName;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Profile && ((Profile) obj).profileName.equals(profileName);
    }

    public Account getAccount() {
        return account;
    }
}
