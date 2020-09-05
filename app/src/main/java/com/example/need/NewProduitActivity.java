package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NewProduitActivity extends AppCompatActivity {
    private EditText addNuméro;
    private EditText addNom;
    private EditText addQuantité;
    private EditText addPrix;
    private Button ADD;
    private Button BACKA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_produit);
        addNuméro = (EditText) findViewById(R.id.addNum);
        addNom = (EditText) findViewById(R.id.addNom);
        addQuantité = (EditText) findViewById(R.id.addQuantité);
        addPrix = (EditText) findViewById(R.id.addPrix);
        ADD = (Button) findViewById(R.id.ADD);
        BACKA = (Button) findViewById(R.id.BACK);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit produit = new Produit();
                produit.setNuméro(addNuméro.getText().toString());
                produit.setNom_produit(addNom.getText().toString());
                produit.setQuantité(addQuantité.getText().toString());
                produit.setPrix(addPrix.getText().toString());
                new FirebaseDatabaseHalper().addProduit(produit, new FirebaseDatabaseHalper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Produit> produits, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewProduitActivity.this, "L'enregistrement du produit a été inséré avec succès"
                        ,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                BACKA.setOnClickListener(new View.OnClickListener() {
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