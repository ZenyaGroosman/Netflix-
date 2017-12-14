package nl.avans;

import nl.avans.sql.Account;
import nl.avans.sql.Profiel;

public class SelectedAccount {
    private static Account selectedAccount = null;
    private static Profiel selectedProfiel = null;

    public static Account getSelectedAccount() {
        return selectedAccount;
    }

    public static void setSelectedAccount(Account selectedAccount) {
        SelectedAccount.selectedAccount = selectedAccount;
    }

    public static Profiel getSelectedProfiel() {
        return selectedProfiel;
    }

    public static void setSelectedProfiel(Profiel selectedProfiel) {
        SelectedAccount.selectedProfiel = selectedProfiel;
    }
}
