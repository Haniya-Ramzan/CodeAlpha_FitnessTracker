package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText etRegEmail, etRegPassword, etConfirmPassword;
    Button btnRegister;
    TextView tvLogin;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DatabaseHelper(this);

        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> {

            String email =
                    etRegEmail.getText().toString().trim();

            String password =
                    etRegPassword.getText().toString().trim();

            String confirmPassword =
                    etConfirmPassword.getText().toString().trim();

            if(email.isEmpty() ||
                    password.isEmpty() ||
                    confirmPassword.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if(!password.equals(confirmPassword)) {

                Toast.makeText(this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            boolean inserted =
                    dbHelper.insertUser(email,password);

            if(inserted) {

                Toast.makeText(this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(RegistrationActivity.this,
                                loginActivity.class);

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(this,
                        "Registration Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tvLogin.setOnClickListener(v -> {

            Intent intent =
                    new Intent(RegistrationActivity.this,
                            loginActivity.class);

            startActivity(intent);
            finish();
        });
    }
}