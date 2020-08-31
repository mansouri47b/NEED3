package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }



    public void goGestionEtreprise(View view) {
        switch (view.getId()){
            case  R.id.goGestionE : startActivity(new Intent(AdminActivity.this,GestionEntreprise.class));
                break;}
    }

    public void goGestionProduit(View view) {
        switch (view.getId()){
            case  R.id.goGestionP : startActivity(new Intent(AdminActivity.this,GestionProduit.class));
                break;}
    }

    public void logoutAdmin(View view) {
        switch (view.getId()){
            case  R.id.logoutadmin : startActivity(new Intent(AdminActivity.this,LoginActivity.class));
                break;}

    }

    public void GoToHomePage(View view) {
        switch (view.getId()){
            case  R.id.GoToHomePage : startActivity(new Intent(AdminActivity.this,MapsActivity.class));
                break;}
    }
}