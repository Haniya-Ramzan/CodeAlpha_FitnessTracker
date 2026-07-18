package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WaterActivity extends AppCompatActivity {

    TextView tvWaterCount;
    ProgressBar waterProgress;

    Button btnAdd250, btnAdd500, btnReset;
    ImageButton btnBack;
    DatabaseHelper db;

    Button btnSaveWater;
    Button btnWaterHistory;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;

    int waterIntake = 0;
    int maxWater = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        // CONNECT IDS
        tvWaterCount = findViewById(R.id.tvWaterCount);
        waterProgress = findViewById(R.id.waterProgress);

        btnAdd250 = findViewById(R.id.btnAdd250);
        btnAdd500 = findViewById(R.id.btnAdd500);
        btnReset = findViewById(R.id.btnReset);

        btnBack = findViewById(R.id.btnBack);

        // CHECKBOX IDS
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);
        cb7 = findViewById(R.id.cb7);
        cb8 = findViewById(R.id.cb8);
        db = new DatabaseHelper(this);

        btnSaveWater = findViewById(R.id.btnSaveWater);
        btnWaterHistory = findViewById(R.id.btnWaterHistory);

        // BACK BUTTON
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WaterActivity.this,
                        dashboardActivity.class);

                startActivity(intent);
                finish();
            }
        });

        // ADD 250 ML
        btnAdd250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waterIntake += 250;

                if (waterIntake > maxWater) {
                    waterIntake = maxWater;
                }

                updateUI();

                Toast.makeText(WaterActivity.this,
                        "250 ml added",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ADD 500 ML
        btnAdd500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waterIntake += 500;

                if (waterIntake > maxWater) {
                    waterIntake = maxWater;
                }

                updateUI();

                Toast.makeText(WaterActivity.this,
                        "500 ml added",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // CHECKBOX EVENTS
        View.OnClickListener checkboxListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox checkBox = (CheckBox) v;

                if (checkBox.isChecked()) {

                    waterIntake += 250;

                    if (waterIntake > maxWater) {
                        waterIntake = maxWater;
                    }

                } else {

                    waterIntake -= 250;

                    if (waterIntake < 0) {
                        waterIntake = 0;
                    }
                }

                updateUI();
            }
        };
        // SAVE WATER INTAKE
        btnSaveWater.setOnClickListener(v -> {

            int glasses = waterIntake / 250;

            boolean inserted = db.insertWater(glasses);

            if(inserted){

                Toast.makeText(WaterActivity.this,
                        "Water Intake Saved",
                        Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(WaterActivity.this,
                        "Save Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnWaterHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(WaterActivity.this,
                            WaterHistoryActivity.class);

            startActivity(intent);
        });

        cb1.setOnClickListener(checkboxListener);
        cb2.setOnClickListener(checkboxListener);
        cb3.setOnClickListener(checkboxListener);
        cb4.setOnClickListener(checkboxListener);
        cb5.setOnClickListener(checkboxListener);
        cb6.setOnClickListener(checkboxListener);
        cb7.setOnClickListener(checkboxListener);
        cb8.setOnClickListener(checkboxListener);

        // RESET BUTTON
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waterIntake = 0;

                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
                cb6.setChecked(false);
                cb7.setChecked(false);
                cb8.setChecked(false);

                updateUI();

                Toast.makeText(WaterActivity.this,
                        "Reset Done",
                        Toast.LENGTH_SHORT).show();
            }
        });

        updateUI();
    }

    // UPDATE UI METHOD
    private void updateUI() {

        tvWaterCount.setText(waterIntake + " / " + maxWater + " ml");
        waterProgress.setProgress(waterIntake);
    }
}