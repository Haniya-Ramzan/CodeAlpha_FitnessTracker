package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CaloriesHistoryActivity extends AppCompatActivity {

    ListView listViewCalories;
    DatabaseHelper db;

    ArrayList<String> caloriesList;
    ArrayList<Integer> idList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_history);

        listViewCalories = findViewById(R.id.listViewCalories);

        db = new DatabaseHelper(this);

        caloriesList = new ArrayList<>();
        idList = new ArrayList<>();

        loadCaloriesHistory();

        listViewCalories.setOnItemClickListener((parent, view, position, id) -> {

            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenuInflater().inflate(R.menu.calorie_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {

                int selectedId = idList.get(position);

                // ================= DELETE =================
                if (item.getItemId() == R.id.delete) {

                    boolean deleted = db.deleteCalories(selectedId);

                    if (deleted) {
                        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        loadCaloriesHistory();
                    } else {
                        Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }

                // ================= UPDATE =================
                if (item.getItemId() == R.id.update) {

                    EditText input = new EditText(this);

                    new androidx.appcompat.app.AlertDialog.Builder(this)
                            .setTitle("Update Calories")
                            .setMessage("Enter new calories")
                            .setView(input)
                            .setPositiveButton("Update", (dialog, which) -> {

                                int newCalories = Integer.parseInt(input.getText().toString());

                                boolean updated = db.updateCalories(selectedId, newCalories);

                                if (updated) {
                                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                    loadCaloriesHistory();
                                } else {
                                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();

                    return true;
                }

                return false;
            });

            popupMenu.show();
        });
    }

    private void loadCaloriesHistory() {

        caloriesList.clear();
        idList.clear();

        Cursor cursor = db.getCaloriesHistory();

        if (cursor.getCount() == 0) {
            caloriesList.add("No Records Found");
        } else {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                int calories = cursor.getInt(1);
                String date = cursor.getString(2);

                idList.add(id);

                caloriesList.add(
                        "Calories: " + calories + " kcal\nDate: " + date
                );
            }
        }

        cursor.close();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                caloriesList
        );

        listViewCalories.setAdapter(adapter);
    }
}