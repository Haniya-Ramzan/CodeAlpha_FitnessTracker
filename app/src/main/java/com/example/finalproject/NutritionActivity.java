package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionActivity extends AppCompatActivity {

    CheckBox checkBreakfast, checkLunch,
            checkDinner, checkSnacks;

    Button btnUpdate, btnReset;

    ImageButton btnBack;

    ProgressBar nutritionProgress;

    TextView tvMealStatus, tvMotivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        // CONNECT IDS
        checkBreakfast = findViewById(R.id.checkBreakfast);
        checkLunch = findViewById(R.id.checkLunch);
        checkDinner = findViewById(R.id.checkDinner);
        checkSnacks = findViewById(R.id.checkSnacks);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnReset = findViewById(R.id.btnReset);

        btnBack = findViewById(R.id.btnBack);

        nutritionProgress = findViewById(R.id.nutritionProgress);

        tvMealStatus = findViewById(R.id.tvMealStatus);
        tvMotivation = findViewById(R.id.tvMotivation);

        // BACK BUTTON
        btnBack.setOnClickListener(v -> {

            Intent intent = new Intent(NutritionActivity.this,
                    dashboardActivity.class);

            startActivity(intent);
            finish();
        });

        // UPDATE BUTTON
        btnUpdate.setOnClickListener(v -> updateNutrition());

        // RESET BUTTON
        btnReset.setOnClickListener(v -> {

            checkBreakfast.setChecked(false);
            checkLunch.setChecked(false);
            checkDinner.setChecked(false);
            checkSnacks.setChecked(false);

            nutritionProgress.setProgress(0);

            tvMealStatus.setText("0 / 4 Meals Completed");

            tvMotivation.setText("Healthy food = Healthy life");

            Toast.makeText(NutritionActivity.this,
                    "Nutrition Reset",
                    Toast.LENGTH_SHORT).show();
        });
    }

    // UPDATE METHOD
    private void updateNutrition() {

        int completed = 0;

        if (checkBreakfast.isChecked())
            completed++;

        if (checkLunch.isChecked())
            completed++;

        if (checkDinner.isChecked())
            completed++;

        if (checkSnacks.isChecked())
            completed++;

        nutritionProgress.setProgress(completed);

        tvMealStatus.setText(completed +
                " / 4 Meals Completed");

        if (completed == 4) {

            tvMotivation.setText("Excellent Healthy Diet!");

        } else if (completed >= 2) {

            tvMotivation.setText("Good nutrition today!");

        } else {

            tvMotivation.setText("Start eating healthy!");
        }

        Toast.makeText(this,
                "Nutrition Updated",
                Toast.LENGTH_SHORT).show();
    }
}