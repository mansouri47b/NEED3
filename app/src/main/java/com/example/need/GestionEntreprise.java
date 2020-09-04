package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GestionEntreprise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_entreprise);
    }
    public void GoAjoutéL(View view) {
        switch (view.getId()){
            case  R.id. GoAjoutéL : startActivity(new Intent(GestionEntreprise.this,NewLocalisationActivity.class));
                break;}

    }
    public void GoModifierL(View view) {
        switch (view.getId()){
            case  R.id. GoModifierL : startActivity(new Intent(GestionEntreprise.this,LocalisationDetailsActivity.class));
                break;}

    }
    public void GoSupprimerL(View view) {
        switch (view.getId()){
            case  R.id. GoSupprimerL : startActivity(new Intent(GestionEntreprise.this,LocalisationDetailsActivity.class));
                break;}

    }
}
