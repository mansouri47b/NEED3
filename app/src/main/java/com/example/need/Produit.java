package com.example.need;

public class Produit {
    private String Numéro;
    private String Nom_produit;
    private String Quantité;
    private String Prix;

    public Produit() {
    }

    public Produit(String Numéro , String Nom_produit, String Quantité ,String Prix) {
        this.Numéro = Numéro;
        this.Nom_produit = Nom_produit;
        this.Quantité = Quantité;
        this.Prix = Prix;
    }

    public String getNuméro() {
        return Numéro;
    }

    public void setNuméro(String Numéro) {
        this.Numéro = Numéro;
    }

    public String getNom_produit() {
        return Nom_produit;
    }

    public void setNom_produit(String Nom_produit) {
        this.Nom_produit = Nom_produit;
    }

    public String getQuantité() {return Quantité;
    }

    public void setQuantité(String Quantité) {
        this.Quantité = Quantité;
    }
    public String getPrix() {
        return Prix;

    }
    public void setPrix(String Prix) {

        this.Prix = Prix;
    }

}
