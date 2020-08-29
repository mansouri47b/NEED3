package com.example.need;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG ="LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    final EditText emaill = (EditText) findViewById(R.id.Email);
    final EditText password = (EditText) findViewById(R.id.Pass);
    Button signIn = (Button) findViewById(R.id.SignIn);
    Button signUp = (Button) findViewById(R.id.SignUp);
    mAuth = FirebaseAuth.getInstance();
    signIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           String email = emaill.getText().toString();
            String pass = password.getText().toString();
            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(), "Enter email And password",
                        Toast.LENGTH_LONG).show();
                return;
            }

            
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    });
    }





    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            updateUI(currentUser);
        }

    }

    public void updateUI(FirebaseUser currentUser) {
        Intent profileIntent = new Intent(LoginActivity.this, MapsActivity.class);
        profileIntent.putExtra("email", currentUser.getEmail());
        startActivity(profileIntent);


    }

    @Override
    public void onClick(View view) {

    }

    public void goNext(View v) {
        switch (v.getId()){
            case  R.id.SignUp : startActivity(new Intent(LoginActivity.this,Register.class));
                break;

        }
    }

}

