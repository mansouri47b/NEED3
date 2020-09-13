package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LocalisationDetailsActivity extends AppCompatActivity {
    private EditText mNuméro;
    private EditText mAdresse;
    private EditText mTéléphone;
    private EditText mLongétude;
    private EditText mAttitude;

    private Button mModifir;
    private Button mSupprimer;
    private Button mRetour;

    private String key;
    private String Numéro;
    private String Adresse;
    private String Téléphone;
    private String Longétude;
    private String Attitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation_details);

        key = getIntent().getStringExtra("key");
        Numéro = getIntent().getStringExtra("Numéro");
        Adresse = getIntent().getStringExtra("Adresse");
        Téléphone = getIntent().getStringExtra("Téléphone");
        Longétude = getIntent().getStringExtra("Longétude");
        Attitude = getIntent().getStringExtra("Attitude");

        mNuméro = (EditText) findViewById(R.id.Numéro1);
        mNuméro.setText(Numéro);
        mAdresse = (EditText) findViewById(R.id.Adresse1);
        mAdresse.setText(Adresse);
        mTéléphone = (EditText) findViewById(R.id.Téléphone1);
        mTéléphone.setText(Téléphone);
        mLongétude = (EditText) findViewById(R.id.Longétude1);
        mLongétude.setText(Longétude);
        mAttitude = (EditText) findViewById(R.id.Attitude1);
        mAttitude.setText(Attitude);

        mModifir = (Button) findViewById(R.id.MODIFIER);
        mSupprimer = (Button) findViewById(R.id.SUPPRIMER);
        mRetour = (Button) findViewById(R.id.RETOUR);

        mModifir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Localisation localisation = new Localisation();
                localisation.setNuméro(mNuméro.getText().toString());
                localisation.setAdresse(mAdresse.getText().toString());
                localisation.setTéléphone(mTéléphone.getText().toString());
                localisation.setLongétude(mLongétude.getText().toString());
                localisation.setAttitude(mAttitude.getText().toString());
                new FirebaseDatabaseHelperL().updateLocalisation(key, localisation,new FirebaseDatabaseHelperL.DataStatus() {
                    @Override
                    public void DataIsLoadedL(List<Localisation> localisations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsertedL() {

                    }

                    @Override
                    public void DataIsUpdatedL() {
                        Toast.makeText(LocalisationDetailsActivity.this, "L'enregistrement du point de vente a été mis à jour avec succès", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeletedL() {

                    }
                });
            }
        });
        mSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelperL().deleteLocalisation(key , new FirebaseDatabaseHelperL.DataStatus() {
                    @Override
                    public void DataIsLoadedL(List<Localisation> localisations, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsertedL() {

                    }

                    @Override
                    public void DataIsUpdatedL() {

                    }
                    @Override
                    public void DataIsDeletedL() {
                        Toast.makeText(LocalisationDetailsActivity.this, "L'enregistrement du point de vente a été supprimé avec succès", Toast.LENGTH_LONG).show();
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