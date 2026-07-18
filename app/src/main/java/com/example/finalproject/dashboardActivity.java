package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.GridView;
import androidx.appcompat.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class dashboardActivity extends AppCompatActivity {

    CardView cardBMI, cardWater, cardExercise,
            cardNutrition, cardVideos, cardDiet,
            cardProfile, cardFeedback, cardSettings;
    GridView gridTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        cardBMI = findViewById(R.id.cardBMI);
        cardWater = findViewById(R.id.cardWater);
        cardExercise = findViewById(R.id.cardExercise);
        cardNutrition = findViewById(R.id.cardNutrition);
        cardVideos = findViewById(R.id.cardVideos);
        cardDiet = findViewById(R.id.cardDiet);
        cardProfile = findViewById(R.id.cardProfile);
        cardFeedback = findViewById(R.id.cardFeedback);
        cardSettings = findViewById(R.id.cardSettings);
        gridTips = findViewById(R.id.gridTips);

        String[] tips = {
                "💧 Drink Water",
                "🏃 Daily Walk",
                "🥗 Healthy Food",
                "😴 Sleep Well",
                "🧘 Reduce Stress",
                "❤️ Stay Active"
        };

        HealthTipAdapter adapter =
                new HealthTipAdapter(this, tips);

        gridTips.setAdapter(adapter);

        gridTips.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this,
                    tips[position],
                    Toast.LENGTH_SHORT).show();
        });

        cardBMI.setOnClickListener(v ->
                startActivity(new Intent(this, BMIACTIVITY.class)));

        cardWater.setOnClickListener(v ->
                startActivity(new Intent(this, WaterActivity.class)));

        cardExercise.setOnClickListener(v ->
                startActivity(new Intent(this, ExerciseActivity.class)));

        cardNutrition.setOnClickListener(v ->
                startActivity(new Intent(this, NutritionActivity.class)));

        cardVideos.setOnClickListener(v ->
                startActivity(new Intent(this, VideoActivity.class)));

        cardDiet.setOnClickListener(v ->
                startActivity(new Intent(this, DietActivity.class)));

        cardProfile.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        cardFeedback.setOnClickListener(v ->
                startActivity(new Intent(this, feedbackActivity.class)));

        cardSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dashboard_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);

        SearchView searchView =
                (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search Features");

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        query = query.toLowerCase();

                        if (query.contains("bmi"))
                            startActivity(new Intent(dashboardActivity.this, BMIACTIVITY.class));

                        else if (query.contains("water"))
                            startActivity(new Intent(dashboardActivity.this, WaterActivity.class));

                        else if (query.contains("exercise"))
                            startActivity(new Intent(dashboardActivity.this, ExerciseActivity.class));

                        else if (query.contains("nutrition"))
                            startActivity(new Intent(dashboardActivity.this, NutritionActivity.class));

                        else if (query.contains("video"))
                            startActivity(new Intent(dashboardActivity.this, VideoActivity.class));

                        else if (query.contains("diet"))
                            startActivity(new Intent(dashboardActivity.this, DietActivity.class));

                        else if (query.contains("profile"))
                            startActivity(new Intent(dashboardActivity.this, ProfileActivity.class));

                        else if (query.contains("feedback"))
                            startActivity(new Intent(dashboardActivity.this, feedbackActivity.class));

                        else if (query.contains("setting"))
                            startActivity(new Intent(dashboardActivity.this, SettingsActivity.class));

                        else
                            Toast.makeText(
                                    dashboardActivity.this,
                                    "Feature Not Found",
                                    Toast.LENGTH_SHORT
                            ).show();

                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

        return true;
    }   // <-- onCreateOptionsMenu ends here


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }

        if (id == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.menu_about) {
            Toast.makeText(this,
                    "Fitness App v1.0",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_logout) {

            Intent intent = new Intent(
                    dashboardActivity.this,
                    loginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

            Toast.makeText(this,
                    "Logged Out Successfully",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}