package com.example.need;

public class Localisation {
    private String numero ;
    private String adresse;
    private String telephone ;
    private String longetude ;
    private String attitude;

    public Localisation() {
    }

    public Localisation(String numero, String adresse, String telephone, String longetude, String attitude) {
        this.numero = numero;
        this.adresse = adresse;
        this.telephone = telephone;
        this.longetude = longetude;
        this.attitude = attitude;
    }

    public String getNuméro() {
        return numero;
    }

    public void setNuméro(String numero) {
        this.numero= numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTéléphone() {
        return telephone;
    }

    public void setTéléphone(String telephone) {
        this.telephone = telephone;
    }

    public String getLongétude() {
        return longetude;
    }

    public void setLongétude(String longetude) {
        this.longetude = longetude;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {  this.attitude = attitude;}
}
