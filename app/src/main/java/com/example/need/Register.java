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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button button;
     EditText Nom = (EditText) findViewById(R.id.nom);
     EditText EmailSigUp = (EditText) findViewById(R.id.EmailSignUp);
     EditText PassSignUp = (EditText) findViewById(R.id.PassSignUp);
    public FirebaseDatabase database;
    public DatabaseReference mDatabase;
    public FirebaseAuth mAuth;
    private static final String USER = "user";
    private static final String TAG ="Register";
    private User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = findViewById(R.id.SignUpp);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = EmailSigUp.getText().toString();
                String password = PassSignUp.getText().toString();
                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter email And password",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String nom = Nom.getText().toString();
                user = new User(email,password,nom);
                registerUser(email,password);
            }
        });
    }
    public void registerUser(String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "SignUp failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }
    public void updateUI(FirebaseUser currentUser){
        String KeyId = mDatabase.push().getKey();
        mDatabase.child(KeyId).setValue(user);
        Intent LoginIntet = new Intent(Register.this,LoginActivity.class);



    }

    @Override
    public void onClick(View view) {

    }
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.SignUpp : startActivity(new Intent(this,MapsActivity.class));
                break;

        }
    }

     */
}
