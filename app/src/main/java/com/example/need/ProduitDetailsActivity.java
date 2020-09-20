package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ProduitDetailsActivity extends AppCompatActivity {
    private EditText mNom_produit;
    private EditText mNuméro;
    private EditText mPrix;
    private EditText mQuantite;

    private Button mModifir;
    private Button mSupprimer;
    private Button mRetour;

    private String key;
    private String Numero;
    private String Nom_produit;
    private String Prix;
    private String Quantite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_details);

        key = getIntent().getStringExtra("key");
        Numero = getIntent().getStringExtra("Numero");
        Nom_produit = getIntent().getStringExtra("Adresse");
        Prix = getIntent().getStringExtra("Telephone");
        Quantite = getIntent().getStringExtra("Longetude");

        mNuméro = (EditText) findViewById(R.id.NuméroP1);
        mNuméro.setText(Numero);
        mNom_produit = (EditText) findViewById(R.id.Nom_produit1);
        mNom_produit.setText(Nom_produit);
        mPrix = (EditText) findViewById(R.id.Prix1);
        mPrix.setText(Prix);
        mQuantite = (EditText) findViewById(R.id.Quantité1);
        mQuantite.setText(Quantite);

        mModifir = (Button) findViewById(R.id.MODIFIER);
        mSupprimer = (Button) findViewById(R.id.SUPPRIMER);
        mRetour = (Button) findViewById(R.id.RETOUR);

        mModifir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit produit = new Produit();
                produit.setNuméro(mNuméro.getText().toString());
                produit.setNom_produit(mNom_produit.getText().toString());
                produit.setPrix(mPrix.getText().toString());
                produit.setQuantité(mQuantite.getText().toString());

                new FirebaseDatabaseHalper().updateProduit(key,produit,new FirebaseDatabaseHalper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Produit> produits, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(ProduitDetailsActivity.this, "L'enregistrement du produit a été mis à jour avec succès", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHalper().deleteProduit(key , new FirebaseDatabaseHalper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Produit> produits, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }
                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(ProduitDetailsActivity.this, "L'enregistrement du produit a été supprimé avec succès", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
        mRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); return;
            }
        });
    }

}

