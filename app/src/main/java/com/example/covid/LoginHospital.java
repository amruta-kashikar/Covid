package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginHospital extends AppCompatActivity {

    Intent forgotpwd,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_hospital);
        forgotpwd = new Intent(this, ForgotPassword.class);
        register = new Intent(this,SigninHospital.class);
    }
    public void perform_action(View v)
    {
        TextView Forgotpwd = (TextView) findViewById(R.id.forgotPassword);

        startActivity(forgotpwd);
    }
    public void register_user(View v)
    {
        TextView Userregister = (TextView) findViewById(R.id.newUser);
        startActivity(register);
    }
}