package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SigninHospital extends AppCompatActivity {
    EditText nameHospital,phoneHospital,pwdHospital,emailHospital;
    Intent linklogin;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    MaterialButton btnSignIn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_hospital);
        nameHospital = findViewById(R.id.nameHospital);
        phoneHospital = findViewById(R.id.phoneHospital);
        pwdHospital = findViewById(R.id.pwdHospital);
        emailHospital = findViewById(R.id.emailHospital);
        btnSignIn = findViewById(R.id.btnSignIn);
        linklogin = new Intent(this,LoginHospital.class);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                finish();
                startActivity(getIntent());

            }
        });
    }
    public void goto_login(View v)
    {
        TextView Linklogin = (TextView) findViewById(R.id.loginlink);
        startActivity(linklogin);
        finish();

    }
    private void registerUser()
    {
        String name = nameHospital.getText().toString().trim();
        String phone = phoneHospital.getText().toString().trim();
        String email = emailHospital.getText().toString().trim();
        String pwd = pwdHospital.getText().toString().trim();

        if(name.isEmpty()){
            nameHospital.setError("Enter name");
            nameHospital.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phoneHospital.setError("Enter phone");
            phoneHospital.requestFocus();
            return;
        }
        if((phone.length()<11) || (phone.length()>11)){
            phoneHospital.setError("Minimum phone number length should be 11 characters");
            phoneHospital.requestFocus();
            return;
        }
        if(email.isEmpty()){
            emailHospital.setError("Enter email");
            emailHospital.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailHospital.setError("Please enter a valid email");
            emailHospital.requestFocus();
            return;
        }
        if(pwd.isEmpty()){
            pwdHospital.setError("Enter password");
            pwdHospital.requestFocus();
            return;
        }
        if(pwd.length()<6)
        {
            pwdHospital.setError("Minimum password length should be 6 characters");
            pwdHospital.requestFocus();
            return;
        }
        Map<String, Object> user = new HashMap<>();
        user.put("Name",name);
        user.put("Phone",phone);
        user.put("Email",email);
        user.put("Password",pwd);
        db.collection("user")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SigninHospital.this,"Hospital registered successfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(SigninHospital.this,"Failed to register hospital",Toast.LENGTH_SHORT).show();
            }
        });
        //progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();


    }
}