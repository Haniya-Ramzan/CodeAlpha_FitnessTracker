package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvCreateAccount;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =
                        etEmail.getText().toString().trim();

                String password =
                        etPassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty()) {

                    Toast.makeText(
                            loginActivity.this,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                boolean check =
                        db.checkUser(email,
                                password);

                if(check) {

                    Toast.makeText(
                            loginActivity.this,
                            "Login Successful",
                            Toast.LENGTH_SHORT).show();

                    Intent intent =
                            new Intent(
                                    loginActivity.this,
                                    dashboardActivity.class);

                    startActivity(intent);
                    finish();

                }
                else {

                    Toast.makeText(
                            loginActivity.this,
                            "Invalid Email or Password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvCreateAccount.setOnClickListener(v -> {

            Intent intent =
                    new Intent(loginActivity.this,
                            RegistrationActivity.class);

            startActivity(intent);
        });
    }
}