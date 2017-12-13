package nl.avans.sql;

import java.util.ArrayList;

public class Account {
    private int abonnementID;
    private String naam;
    private String straat;
    private String postcode;
    private int huisnummer;
    private String plaats;
    private ArrayList<Profiel> profielen;

    public Account(int abonnementID, String naam, String straat, String postcode, int huisnummer, String plaats) {
        this.abonnementID = abonnementID;
        this.naam = naam;
        this.straat = straat;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.plaats = plaats;
        profielen = new ArrayList<>();
    }

    public int getAbonnementID() {
        return abonnementID;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getPostcode() {
        return postcode;
    }

    /**
     * voegt een profiel toe aan het account. Een account mag niet meer dan 5 profielen hebben
     * @param profiel
     * @return true als er minder dan 5 profielen inzitten
     */
    public boolean addProfiel(Profiel profiel){
        if (profielen.size() + 1 <= 5){
            profielen.add(profiel);
            return true;
        }
        return false;
    }

    public ArrayList<Profiel> getProfielen() {
        return profielen;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getPlaats() {
        return plaats;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Account && ((Account) obj).abonnementID == this.abonnementID;
    }
}
