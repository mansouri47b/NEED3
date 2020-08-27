package com.example.need;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity implements View.OnClickListener{
      Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button=(Button) findViewById(R.id.SignUpp);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.SignUpp : startActivity(new Intent(this,MapsActivity.class));
                break;

        }
    }
}
