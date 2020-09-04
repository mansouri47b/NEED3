package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GestionProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_produit);
    }


    public void GoAjouté(View view) {
        switch (view.getId()){
            case  R.id. GoAjouté : startActivity(new Intent(GestionProduit.this,NewProduitActivity.class));
                break;}

    }
    public void GoModifier(View view) {
        switch (view.getId()){
            case  R.id. GoModifier : startActivity(new Intent(GestionProduit.this,updateProduitActivity.class));
                break;}

    }
    public void GoSupprimer(View view) {
        switch (view.getId()){
            case  R.id. GoSupprimer : startActivity(new Intent(GestionProduit.this,deleteProduitActivity.class));
                break;}

    }

}
