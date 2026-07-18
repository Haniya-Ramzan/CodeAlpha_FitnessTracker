package com.example.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DietActivity extends AppCompatActivity {

    ProgressBar calorieProgress;
    TextView tvCalories, tvGoal, tvMotivation;
    EditText etCalories;

    Button btnAdd, btnReset;
    Button btnDietVideo, btnCalorieVideo;
    Button btnSaveCalories, btnCaloriesHistory;

    ImageButton btnBack;

    DatabaseHelper db;

    LinearLayout mainLayout;

    int totalCalories = 0;
    int goal = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        calorieProgress = findViewById(R.id.calorieProgress);
        tvCalories = findViewById(R.id.tvCalories);
        tvGoal = findViewById(R.id.tvGoal);
        tvMotivation = findViewById(R.id.tvMotivation);

        etCalories = findViewById(R.id.etCalories);

        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnDietVideo = findViewById(R.id.btnDietVideo);
        btnCalorieVideo = findViewById(R.id.btnCalorieVideo);
        btnBack = findViewById(R.id.btnBack);

        btnSaveCalories = findViewById(R.id.btnSaveCalories);
        btnCaloriesHistory = findViewById(R.id.btnCaloriesHistory);

        mainLayout = findViewById(R.id.mainLayout);

        db = new DatabaseHelper(this);

        btnAdd.setOnClickListener(v -> {

            String input = etCalories.getText().toString().trim();

            if (input.isEmpty()) {

                Toast.makeText(
                        DietActivity.this,
                        "Enter calories first",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            totalCalories += Integer.parseInt(input);

            updateUI();

            etCalories.setText("");
        });

        btnReset.setOnClickListener(v -> {

            totalCalories = 0;

            updateUI();

            Toast.makeText(
                    DietActivity.this,
                    "Calories Reset",
                    Toast.LENGTH_SHORT).show();
        });

        btnDietVideo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=1bF7z1wZx3Q"));

            startActivity(intent);
        });

        btnCalorieVideo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI"));

            startActivity(intent);
        });

        btnSaveCalories.setOnClickListener(v -> {

            boolean inserted =
                    db.insertCalories(totalCalories);

            if (inserted) {

                Toast.makeText(
                        DietActivity.this,
                        "Calories Saved",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(
                        DietActivity.this,
                        "Save Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCaloriesHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            DietActivity.this,
                            CaloriesHistoryActivity.class);

            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            DietActivity.this,
                            dashboardActivity.class);

            startActivity(intent);
            finish();
        });

        updateUI();
    }

    private void updateUI() {

        calorieProgress.setProgress(
                Math.min(totalCalories, goal));

        tvCalories.setText(
                totalCalories + " kcal consumed");

        if (totalCalories == 0) {

            tvMotivation.setText(
                    "Start your healthy journey 🌱");

            mainLayout.setBackgroundColor(
                    Color.parseColor("#FFFFFF"));

        }
        else if (totalCalories < 1000) {

            tvMotivation.setText(
                    "Good start 👍");

            mainLayout.setBackgroundColor(
                    Color.parseColor("#C8E6C9"));

        }
        else if (totalCalories < 1800) {

            tvMotivation.setText(
                    "Almost there 🔥");

            mainLayout.setBackgroundColor(
                    Color.parseColor("#FFF9C4"));

        }
        else if (totalCalories <= 2000) {

            tvMotivation.setText(
                    "Goal achieved 🎉💪");

            mainLayout.setBackgroundColor(
                    Color.parseColor("#BBDEFB"));
        }
        else {

            tvMotivation.setText(
                    "Calories Limit Exceeded ⚠");

            mainLayout.setBackgroundColor(
                    Color.parseColor("#FFCDD2"));

            Toast.makeText(
                    DietActivity.this,
                    "Warning! Calories Goal Exceeded",
                    Toast.LENGTH_LONG).show();
        }
    }
}