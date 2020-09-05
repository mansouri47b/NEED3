package com.example.need;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

EditText nom,EmailSignUp,PassSignUp;
Button SignUpp;
ProgressBar Progrs;
FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom=(EditText) findViewById(R.id.nom);
        EmailSignUp=(EditText) findViewById(R.id.EmailSignUp);
        PassSignUp=(EditText) findViewById(R.id.PassSignUp);
        SignUpp=(Button) findViewById(R.id.SignUpp);
        Progrs=(ProgressBar) findViewById(R.id.Progrs);

        fAuth=FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser()!= null){

            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        SignUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=EmailSignUp.getText().toString().trim();
                String password=PassSignUp.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    EmailSignUp.setError("Email obligatoire");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    PassSignUp.setError("mot de passe obligatoire");
                    return;
                }
                 if (password.length()< 6){

                     PassSignUp.setError("mot de passe doit etre > 6 caracteres");
                     return;
                 }

                 Progrs.setVisibility(View.VISIBLE);
                 //register the user in firebase
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                Toast.makeText(Register.this,"Compte créé",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }else {

                                Toast.makeText(Register.this,"Erreur!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            }
        });




    }

    @Override
    public void onClick(View view) {

    }

}
