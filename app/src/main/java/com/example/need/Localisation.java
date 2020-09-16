package com.example.need;

public class Localisation {
    private String Numero ;
    private String Adresse;
    private String Telephone ;
    private String Longetude ;
    private String Attitude;

    public Localisation() {
    }

    public Localisation(String numéro, String adresse, String téléphone, String longétude, String attitude) {
        Numero = numéro;
        Adresse = adresse;
        Telephone = téléphone;
        Longetude = longétude;
        Attitude = attitude;
    }

    public String getNuméro() {
        return Numero;
    }

    public void setNuméro(String numéro) {
        Numero = numéro;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTéléphone() {
        return Telephone;
    }

    public void setTéléphone(String téléphone) {
        Telephone = téléphone;
    }

    public String getLongétude() {
        return Longetude;
    }

    public void setLongétude(String longétude) {
        Longetude = longétude;
    }

    public String getAttitude() {
        return Attitude;
    }

    public void setAttitude(String attitude) {
        Attitude = attitude;
    }
}
