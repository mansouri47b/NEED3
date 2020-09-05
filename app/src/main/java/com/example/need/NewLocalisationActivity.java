package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NewLocalisationActivity extends AppCompatActivity {
    private EditText addNuméroL;
    private EditText addAdresse;
    private EditText addTéléphone;
    private EditText addLongétude;
    private EditText addAttitude;
    private Button ADD_L;
    private Button BACKAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_localisation);
        addNuméroL = (EditText) findViewById(R.id.addNuméroL);
        addAdresse = (EditText) findViewById(R.id.addAdresse);
        addTéléphone = (EditText) findViewById(R.id.addTéléphone);
        addLongétude = (EditText) findViewById(R.id.addLongétude);
        addAttitude = (EditText) findViewById(R.id.addAttitude);

        ADD_L = (Button) findViewById(R.id.ADD_L);
        BACKAL = (Button) findViewById(R.id.BACKAL);

        ADD_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Localisation localisation = new Localisation();
                localisation.setNuméro(addNuméroL.getText().toString());
                localisation.setAdresse(addAdresse.getText().toString());
                localisation.setTéléphone(addTéléphone.getText().toString());
                localisation.setAttitude(addAttitude.getText().toString());
                localisation.setLongétude(addLongétude.getText().toString());

                new FirebaseDatabaseHelperL().addLocalisations(localisation, new FirebaseDatabaseHelperL.DataStatus() {
                    @Override
                    public void DataIsLoadedL(List<Localisation> localisations, List<String> keys) {
                    }

                    @Override
                    public void DataIsInsertedL() {
                        Toast.makeText(NewLocalisationActivity.this, "L'enregistrement du point de vente a été inséré avec succès"
                                ,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdatedL() {

                    }

                    @Override
                    public void DataIsDeletedL() {

                    }
                });
                BACKAL.setOnClickListener(new View.OnClickListener() {
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