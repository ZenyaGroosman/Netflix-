package nl.avans;

import nl.avans.sql.Account;
import nl.avans.sql.Profile;

public class SelectedAccount {
    private static Account selectedAccount = null;
    private static Profile selectedProfiel = null;

    public static Account getSelectedAccount() {
        return selectedAccount;
    }

    public static void setSelectedAccount(Account selectedAccount) {
        SelectedAccount.selectedAccount = selectedAccount;
    }

    public static Profile getSelectedProfiel() {
        return selectedProfiel;
    }

    public static void setSelectedProfiel(Profile selectedProfiel) {
        SelectedAccount.selectedProfiel = selectedProfiel;
    }
}
