package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class updateProduitActivity extends AppCompatActivity {
    private EditText updateNuméro;
    private EditText updateNom;
    private EditText updateQuantité;
    private EditText updatePrix;

    private Button UPDATE;
    private Button BACK;

    private String key;
    private String Numéro;
    private String Nom_produit;
    private String Quantité;
    private String Prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produit);

        key = getIntent().getStringExtra("key");
        Numéro = getIntent().getStringExtra("Numéro");
        Nom_produit = getIntent().getStringExtra("Nom_produit");
        Quantité = getIntent().getStringExtra("Quantité");
        Prix = getIntent().getStringExtra("Prix");


        updateNuméro = (EditText) findViewById(R.id.updateNum);
        updateNuméro.setText(Numéro);
        updateNom = (EditText) findViewById(R.id.updateNom);
        updateNom.setText(Nom_produit);
        updateQuantité = (EditText) findViewById(R.id.updateQuantité);
        updateQuantité.setText(Quantité);
        updatePrix = (EditText) findViewById(R.id.updatePrix);
        updatePrix.setText(Prix);
        

        UPDATE = (Button) findViewById(R.id.UPDATE);
        BACK = (Button) findViewById(R.id.BACK);

        UPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit produit = new Produit();
                produit.setNuméro(updateNuméro.getText().toString());
                produit.setNom_produit(updateNom.getText().toString());
                produit.setQuantité(updateQuantité.getText().toString());
                produit.setPrix(updatePrix.getText().toString());

                new FirebaseDatabaseHalper().updateProduit(key, produit, new FirebaseDatabaseHalper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Produit> produits, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(updateProduitActivity.this, "Produit record has "+ "been updated Successfully"
                                ,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                BACK.setOnClickListener(new View.OnClickListener() {
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