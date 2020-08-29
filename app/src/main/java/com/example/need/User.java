package com.example.need;

public class User {
        private String email ;
        private String password ;
        private String nom ;
    public User(String email,String password){
        this.email = email ;
        this.password = password ;

    }

    public User(String email, String password, String nom) {
        this.email = email;
        this.password = password;
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public User() {



    }
}
