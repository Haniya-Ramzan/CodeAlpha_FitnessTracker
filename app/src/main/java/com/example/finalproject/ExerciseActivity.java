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

public class ExerciseActivity extends AppCompatActivity {

    CheckBox checkPushups, checkRunning,
            checkYoga, checkCycling,
            checkStretching;

    Button btnComplete, btnReset;

    ImageButton btnBack;

    ProgressBar exerciseProgress;

    TextView tvWorkoutStatus, tvMotivation;
    Button btnVideos;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        checkPushups = findViewById(R.id.checkPushups);
        checkRunning = findViewById(R.id.checkRunning);
        checkYoga = findViewById(R.id.checkYoga);
        checkCycling = findViewById(R.id.checkCycling);
        checkStretching = findViewById(R.id.checkStretching);

        btnComplete = findViewById(R.id.btnComplete);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack);

        exerciseProgress = findViewById(R.id.exerciseProgress);

        tvWorkoutStatus = findViewById(R.id.tvWorkoutStatus);
        tvMotivation = findViewById(R.id.tvMotivation);
        btnVideos = findViewById(R.id.btnVideos);

        db = new DatabaseHelper(this);

        btnBack.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ExerciseActivity.this,
                    dashboardActivity.class);

            startActivity(intent);
            finish();
        });

        btnVideos.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ExerciseActivity.this,
                    VideoActivity.class);

            startActivity(intent);
        });

        btnComplete.setOnClickListener(v -> {

            int completed = updateProgress();

            boolean inserted =
                    db.insertExercise(completed);

            if (inserted) {

                Toast.makeText(
                        ExerciseActivity.this,
                        "Exercise Saved",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(
                        ExerciseActivity.this,
                        "Save Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnReset.setOnClickListener(v -> {

            checkPushups.setChecked(false);
            checkRunning.setChecked(false);
            checkYoga.setChecked(false);
            checkCycling.setChecked(false);
            checkStretching.setChecked(false);

            exerciseProgress.setProgress(0);

            tvWorkoutStatus.setText(
                    "0 / 5 Exercises Completed");

            tvMotivation.setText(
                    "Stay strong and stay healthy!");

            Toast.makeText(
                    ExerciseActivity.this,
                    "Exercises Reset",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private int updateProgress() {

        int completed = 0;

        if (checkPushups.isChecked())
            completed++;

        if (checkRunning.isChecked())
            completed++;

        if (checkYoga.isChecked())
            completed++;

        if (checkCycling.isChecked())
            completed++;

        if (checkStretching.isChecked())
            completed++;

        exerciseProgress.setProgress(completed);

        tvWorkoutStatus.setText(
                completed + " / 5 Exercises Completed");

        if (completed == 5) {

            tvMotivation.setText(
                    "Excellent! Workout Completed!");

        } else if (completed >= 3) {

            tvMotivation.setText(
                    "Great Job! Keep Going!");

        } else {

            tvMotivation.setText(
                    "Start your workout today!");
        }

        return completed;
    }
}