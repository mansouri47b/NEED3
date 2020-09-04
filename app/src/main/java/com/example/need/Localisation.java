package com.example.need;

public class Localisation {
    private String Longétude ;
    private String Attitude;

    public Localisation() {
    }

    public Localisation(String longétude, String attitude) {
        Longétude = longétude;
        Attitude = attitude;
    }

    public String getLongétude() {
        return Longétude;
    }

    public void setLongétude(String longétude) {
        Longétude = longétude;
    }

    public String getAttitude() {
        return Attitude;
    }

    public void setAttitude(String attitude) {
        Attitude = attitude;
    }
}
