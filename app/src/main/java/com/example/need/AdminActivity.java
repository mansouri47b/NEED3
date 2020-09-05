package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button logout=(Button) findViewById(R.id.logoutadmin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.logoutadmin: FirebaseAuth.getInstance().signOut(); startActivity(new Intent(getApplicationContext(),LoginActivity.class));finish();
                        break;
                }
            }
        });


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




    public void GoToHomePage(View view) {
        switch (view.getId()){
            case  R.id.GoToHomePage : startActivity(new Intent(AdminActivity.this,MapsActivity.class));
                break;}
    }
}