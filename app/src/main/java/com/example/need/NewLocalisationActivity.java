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
    private EditText addLongétude;
    private EditText addAttitude;
    private Button ADD_L;
    private Button BACKAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_localisation);
        addLongétude = (EditText) findViewById(R.id.addLongétude);
        addAttitude = (EditText) findViewById(R.id.addAttitude);

        ADD_L = (Button) findViewById(R.id.ADD_L);
        BACKAL = (Button) findViewById(R.id.BACKAL);

        ADD_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Localisation localisation = new Localisation();
                localisation.setLongétude(addLongétude.getText().toString());
                localisation.setAttitude(addAttitude.getText().toString());

                new FirebaseDatabaseHelperL().addLocalisations(localisation, new FirebaseDatabaseHelperL.DataStatus() {
                    @Override
                    public void DataIsLoadedL(List<Localisation> localisations, List<String> keys) {
                    }

                    @Override
                    public void DataIsInsertedL() {
                        Toast.makeText(NewLocalisationActivity.this, "The Localisation record has "+ "been inserted Successfully"
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