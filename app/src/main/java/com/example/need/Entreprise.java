package com.example.need;

public class Entreprise {

    private String num;
    private String nom;
    private String type;


    public Entreprise(String num, String nom, String type) {
        this.num = num;
        this.nom = nom;
        this.type = type;
    }


    public String getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }
}
