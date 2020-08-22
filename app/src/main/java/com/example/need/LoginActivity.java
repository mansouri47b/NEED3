package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    EditText email = (EditText) findViewById(R.id.Email);
    EditText pass = (EditText) findViewById(R.id.Pass);
    Button signIn = (Button) findViewById(R.id.SignIn);
    Button signUp = (Button) findViewById(R.id.SignUp);
    signIn.setOnClickListener(this);
    signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.SignIn: startActivity(new Intent(this,MainActivity.class));
            break;

            case  R.id.SignUp :startActivity(new Intent(this,Register.class));
            break;
        }
    }
}

