package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class bmi_HistoryActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;

    ArrayList<String> bmiList;
    ArrayList<Integer> idList;

    ArrayAdapter<String> adapter;

    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        listView = findViewById(R.id.listViewBMI);
        db = new DatabaseHelper(this);

        bmiList = new ArrayList<>();
        idList = new ArrayList<>();

        loadBMIData();

        // REGISTER CONTEXT MENU
        registerForContextMenu(listView);

        // GET CLICK POSITION
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            return false;
        });
    }

    // CREATE CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bmi_menu, menu);
    }

    // HANDLE MENU CLICK
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int selectedId = idList.get(selectedPosition);

        if (item.getItemId() == R.id.delete) {

            boolean deleted = db.deleteBMI(selectedId);

            if (deleted) {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                loadBMIData();
            } else {
                Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        if (item.getItemId() == R.id.update) {

            Toast.makeText(this, "Update clicked (add dialog here)", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void loadBMIData() {

        bmiList.clear();
        idList.clear();

        Cursor cursor = db.getBMIHistory();

        if (cursor.getCount() == 0) {
            bmiList.add("No BMI records found");
        } else {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                double weight = cursor.getDouble(1);
                double height = cursor.getDouble(2);
                double bmi = cursor.getDouble(3);

                idList.add(id);

                String record =
                        "Weight: " + weight + " kg\n" +
                                "Height: " + height + " m\n" +
                                "BMI: " + String.format("%.2f", bmi);

                bmiList.add(record);
            }
        }

        cursor.close();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bmiList
        );

        listView.setAdapter(adapter);
    }
}