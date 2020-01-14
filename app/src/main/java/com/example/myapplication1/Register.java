package com.example.myapplication1;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText username, email, password, number;
    private Button register, goToLogin;
    private ProgressBar p;
    private FirebaseAuth fAuth;
   // private FirebaseFirestore fStore;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        register = findViewById(R.id.bRegister);
        goToLogin = findViewById(R.id.bLogin);
        p = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
       // fStore = FirebaseFirestore.getInstance();
        p.setVisibility(View.INVISIBLE);
        /* ## Check for previous loggings##*/
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                final String sUsername = username.getText().toString().trim();
                String sNumber = number.getText().toString().trim();
                if (TextUtils.isEmpty(sEmail)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(sPassword)) {
                    password.setError("Passowrd is Required");
                    return;
                }
                p.setVisibility(View.VISIBLE);
//                fAuth.createUserWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_LONG).show();
//                            userId = fAuth.getCurrentUser().getUid();
//                            DocumentReference dreference = fStore.collection("users").document(userId);
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("name", sUsername);
//                            user.put("number", sUsername);
//                            user.put("email", sUsername);
//                            user.put("passowrd", sUsername);
//                            dreference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//
//                                }
//                            });
//
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        } else {
//                            Toast.makeText(Register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                            p.setVisibility(View.INVISIBLE);
//                        }
//
//                    }
//                });
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}
