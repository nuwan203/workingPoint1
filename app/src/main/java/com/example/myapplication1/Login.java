package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email, password;
    private Button login, goToRegistration;
    private ProgressBar p;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        goToRegistration = findViewById(R.id.bRegister);
        login = findViewById(R.id.bLogin);
        p = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        p.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                if (TextUtils.isEmpty(sEmail)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(sPassword)) {
                    password.setError("Passowrd is Required");
                    return;
                }
                p.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in User", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            p.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });


    }
}
