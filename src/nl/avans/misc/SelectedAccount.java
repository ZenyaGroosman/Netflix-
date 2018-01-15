package nl.avans.misc;

import nl.avans.sql.Account;
import nl.avans.sql.Profile;

//This class keeps track of which account and profile are selected, so they can be used in other pages.
public class SelectedAccount {
    private static Account selectedAccount = null;
    private static Profile selectedProfile = null;

    public static Account getSelectedAccount() {
        return selectedAccount;
    }

    public static void setSelectedAccount(Account selectedAccount) {
        SelectedAccount.selectedAccount = selectedAccount;
        SelectedAccount.selectedProfile = null;
    }

    public static Profile getSelectedProfile() {
        return selectedProfile;
    }

    public static void setSelectedProfile(Profile selectedProfile) {
        SelectedAccount.selectedProfile = selectedProfile;
    }
}
