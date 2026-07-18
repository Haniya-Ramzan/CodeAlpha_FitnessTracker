package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvAge, tvHeight, tvWeight, tvBMI;
    Button btnEdit, btnLogout, btnBack;

    // Firebase
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvBMI = findViewById(R.id.tvBMI);

        btnEdit = findViewById(R.id.btnEdit);
        btnLogout = findViewById(R.id.btnLogout);
        btnBack = findViewById(R.id.btnBack);

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Profiles");

        // EDIT PROFILE + SAVE TO FIREBASE
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = "Edited User";
                String age = "21";
                String height = "172 cm";
                String weight = "66 kg";
                String bmi = "22.3 (Normal)";

                tvName.setText("Name: " + name);
                tvAge.setText("Age: " + age);
                tvHeight.setText("Height: " + height);
                tvWeight.setText("Weight: " + weight);
                tvBMI.setText("BMI: " + bmi);

                // Save to Firebase
                String id = databaseReference.push().getKey();

                HashMap<String, Object> profile = new HashMap<>();
                profile.put("name", name);
                profile.put("age", age);
                profile.put("height", height);
                profile.put("weight", weight);
                profile.put("bmi", bmi);

                databaseReference.child(id).setValue(profile)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(ProfileActivity.this,
                                        "Profile Saved to Firebase ✔",
                                        Toast.LENGTH_SHORT).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(ProfileActivity.this,
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_SHORT).show()
                        );
            }
        });

        // LOGOUT
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, dashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}