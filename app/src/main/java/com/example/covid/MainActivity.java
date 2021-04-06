package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton Requestbtn,Loginbtn;
    Intent requestbedIntent,loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Requestbtn = findViewById(R.id.btnRequest);
        Loginbtn = findViewById(R.id.loginbtn);
        requestbedIntent = new Intent(this, RequestBed.class);
        loginIntent = new Intent(this,LoginHospital.class);
        Requestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(requestbedIntent);
                finish();
            }
        });
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginIntent);
                finish();
            }
        });
    }
}