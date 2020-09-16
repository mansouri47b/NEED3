package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class deleteProduitActivity extends AppCompatActivity {
    private EditText deleteNuméro;
    private EditText deleteNom;
    private EditText deleteQuantité;
    private EditText deletePrix;

    private Button DELETE;
    private Button BACKD;

    private String key;
    private String Numero;
    private String Nom_produit;
    private String Quantite;
    private String Prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_produit);

        key = getIntent().getStringExtra("key");
        Numero = getIntent().getStringExtra("Numero");
        Nom_produit = getIntent().getStringExtra("Nom_produit");
        Quantite = getIntent().getStringExtra("Quantite");
        Prix = getIntent().getStringExtra("Prix");


        deleteNuméro = (EditText) findViewById(R.id.deleteNum);
        deleteNuméro.setText(Numero);
        deleteNom = (EditText) findViewById(R.id.deleteNom);
        deleteNom.setText(Nom_produit);
        deleteQuantité = (EditText) findViewById(R.id.deleteQuantité);
        deleteQuantité.setText(Quantite);
        deletePrix = (EditText) findViewById(R.id.deletePrix);
        deletePrix.setText(Prix);


        DELETE = (Button) findViewById(R.id.DELETE);
        BACKD = (Button) findViewById(R.id.BACK);

        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit produitD = new Produit();
                produitD.setNuméro(deleteNuméro.getText().toString());
                produitD.setNom_produit(deleteNom.getText().toString());
                produitD.setQuantité(deleteQuantité.getText().toString());
                produitD.setPrix(deletePrix.getText().toString());

                new FirebaseDatabaseHalper().deleteProduit(key, produitD, new FirebaseDatabaseHalper.DataStatus() {
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
                        Toast.makeText(deleteProduitActivity.this, "Produit record has "+ "been deleted Successfully"
                                ,Toast.LENGTH_LONG).show();

                    }
                });
                BACKD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        return;
                    }
                });
            }
        });
    }


}