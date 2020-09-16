package com.example.need;

public class Produit {
    private String Numero;
    private String Nom_produit;
    private String Quantite;
    private String Prix;

    public Produit() {
    }

    public Produit(String Numéro , String Nom_produit, String Quantité ,String Prix) {
        this.Numero = Numéro;
        this.Nom_produit = Nom_produit;
        this.Quantite = Quantité;
        this.Prix = Prix;
    }

    public String getNuméro() {
        return Numero;
    }

    public void setNuméro(String Numéro) {
        this.Numero = Numéro;
    }

    public String getNom_produit() {
        return Nom_produit;
    }

    public void setNom_produit(String Nom_produit) {
        this.Nom_produit = Nom_produit;
    }

    public String getQuantité() {return Quantite;
    }

    public void setQuantité(String Quantité) {
        this.Quantite = Quantité;
    }
    public String getPrix() {
        return Prix;

    }
    public void setPrix(String Prix) {

        this.Prix = Prix;
    }

}
