package nl.avans.sql;

import java.util.ArrayList;

//this class keeps track of all the information about accounts
public class Account {
    private int id;
    private String name;
    private String street;
    private String postcode;
    private int houseNumber;
    private String place;
    private ArrayList<Profile> profiles;

    public Account(int id, String name, String street, String postcode, int houseNumber, String place) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        this.place = place;
        profiles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    /**
     * voegt een profiel toe aan het account. Een account mag niet meer dan 5 profielen hebben
     * @param profiel
     * @return true als er minder dan 5 profielen inzitten
     */
    public boolean addProfile(Profile profiel){
        if (profiles.size() + 1 <= 5){
            profiles.add(profiel);
            return true;
        }
        return false;
    }

    public void removeProfiel(Profile profile){
        profiles.remove(profile);
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPlace() {
        return place;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Account && ((Account) obj).id == this.id;
    }

    @Override
    public String toString() {
        return name;
    }
}
