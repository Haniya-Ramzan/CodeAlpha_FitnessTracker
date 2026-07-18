package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch switchDarkMode;
    Switch switchNotifications;
    Switch switchReminder;
    Switch switchWeeklyReport;
    Switch switchSound;

    Button btnGoal;
    Button btnClearProgress;
    Button btnPrivacy;
    Button btnSupport;
    Button btnVersion;

    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Hide Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Connect IDs
        btnMenu = findViewById(R.id.btnMenu);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchNotifications = findViewById(R.id.switchNotifications);
        switchReminder = findViewById(R.id.switchReminder);
        switchWeeklyReport = findViewById(R.id.switchWeeklyReport);
        switchSound = findViewById(R.id.switchSound);

        btnGoal = findViewById(R.id.btnGoal);
        btnClearProgress = findViewById(R.id.btnClearProgress);
        btnPrivacy = findViewById(R.id.btnPrivacy);
        btnSupport = findViewById(R.id.btnSupport);
        btnVersion = findViewById(R.id.btnVersion);

        // Popup Menu
        btnMenu.setOnClickListener(v -> {

            PopupMenu popupMenu =
                    new PopupMenu(SettingsActivity.this, btnMenu);

            popupMenu.getMenuInflater()
                    .inflate(R.menu.settings_menu,
                            popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == R.id.menuDashboard) {

                    startActivity(new Intent(
                            SettingsActivity.this,
                            dashboardActivity.class));

                    finish();
                    return true;
                }

                if (item.getItemId() == R.id.menuAbout) {

                    startActivity(new Intent(
                            SettingsActivity.this,
                            AboutActivity.class));

                    return true;
                }

                if (item.getItemId() == R.id.menuLogout) {

                    startActivity(new Intent(
                            SettingsActivity.this,
                            loginActivity.class));

                    finish();
                    return true;
                }

                return false;
            });

            popupMenu.show();
        });

        // Dark Mode
        switchDarkMode.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked ?
                                    "Dark Mode Enabled" :
                                    "Dark Mode Disabled",
                            Toast.LENGTH_SHORT).show();
                });

        // Notifications
        switchNotifications.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked ?
                                    "Notifications Enabled" :
                                    "Notifications Disabled",
                            Toast.LENGTH_SHORT).show();
                });

        // Daily Reminder
        switchReminder.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked ?
                                    "Daily Reminder Enabled" :
                                    "Daily Reminder Disabled",
                            Toast.LENGTH_SHORT).show();
                });

        // Weekly Report
        switchWeeklyReport.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked ?
                                    "Weekly Report Enabled" :
                                    "Weekly Report Disabled",
                            Toast.LENGTH_SHORT).show();
                });

        // Sound Effects
        switchSound.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked ?
                                    "Sound Effects Enabled" :
                                    "Sound Effects Disabled",
                            Toast.LENGTH_SHORT).show();
                });

        // Set Goal
        btnGoal.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "Daily Goal Updated",
                    Toast.LENGTH_SHORT).show();
        });

        // Clear Progress
        btnClearProgress.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "All Progress Cleared",
                    Toast.LENGTH_SHORT).show();
        });

        // Privacy Policy
        btnPrivacy.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "Privacy Policy",
                    Toast.LENGTH_SHORT).show();
        });

        // Contact Support
        btnSupport.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "support@fitnessapp.com",
                    Toast.LENGTH_LONG).show();
        });

        // App Version
        btnVersion.setOnClickListener(v -> {

            Toast.makeText(
                    SettingsActivity.this,
                    "Fitness App Version 1.0",
                    Toast.LENGTH_SHORT).show();
        });
    }
}