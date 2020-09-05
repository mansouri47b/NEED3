package com.example.need;

public class Localisation {
    private String Numéro ;
    private String Adresse;
    private String Téléphone ;
    private String Longétude ;
    private String Attitude;

    public Localisation() {
    }

    public Localisation(String numéro, String adresse, String téléphone, String longétude, String attitude) {
        Numéro = numéro;
        Adresse = adresse;
        Téléphone = téléphone;
        Longétude = longétude;
        Attitude = attitude;
    }

    public String getNuméro() {
        return Numéro;
    }

    public void setNuméro(String numéro) {
        Numéro = numéro;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTéléphone() {
        return Téléphone;
    }

    public void setTéléphone(String téléphone) {
        Téléphone = téléphone;
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
