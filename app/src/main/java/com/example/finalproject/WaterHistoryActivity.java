package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WaterHistoryActivity extends AppCompatActivity {


    ListView listViewWater;
    DatabaseHelper db;
    ArrayList<String> waterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        listViewWater = findViewById(R.id.listViewWater);

        db = new DatabaseHelper(this);

        waterList = new ArrayList<>();

        loadWaterHistory();
    }

    private void loadWaterHistory() {

        Cursor cursor = db.getWaterHistory();

        if (cursor == null || cursor.getCount() == 0) {

            waterList.add("No Water Records Found");

        } else {

            while (cursor.moveToNext()) {

                int glasses = cursor.getInt(1);
                String date = cursor.getString(2);

                String record =
                        "Water Intake: " + glasses + " Glasses\n" +
                                "Date: " + date;

                waterList.add(record);
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        waterList);

        listViewWater.setAdapter(adapter);
    }


}
