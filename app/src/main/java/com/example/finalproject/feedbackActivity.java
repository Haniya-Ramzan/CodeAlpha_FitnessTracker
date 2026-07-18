package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class feedbackActivity extends AppCompatActivity {

    EditText etName, etEmail, etFeedback;
    Spinner spinnerCategory;
    RatingBar ratingBar;
    Button btnSubmit, btnReset, btnMenu;   // ✅ added menu button
    ImageButton btnBack;
    TextView tvStatus;

    DatabaseReference databaseReference;
    String currentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etFeedback = findViewById(R.id.etFeedback);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        ratingBar = findViewById(R.id.ratingBar);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        btnBack = findViewById(R.id.btnBack);
        tvStatus = findViewById(R.id.tvStatus);

        btnMenu = new Button(this); // popup menu button (not in XML needed)

        databaseReference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        String[] categories = {"General Feedback", "Bug Report", "Feature Request"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories);

        spinnerCategory.setAdapter(adapter);

        // ================= SAVE =================
        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String feedback = etFeedback.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();
            float rating = ratingBar.getRating();

            if (name.isEmpty() || email.isEmpty() || feedback.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentId == null) {
                currentId = databaseReference.push().getKey();
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("email", email);
            data.put("feedback", feedback);
            data.put("category", category);
            data.put("rating", rating);

            databaseReference.child(currentId).setValue(data);

            tvStatus.setText("Saved Successfully ✔");
        });

        // ================= RESET =================
        btnReset.setOnClickListener(v -> {
            etName.setText("");
            etEmail.setText("");
            etFeedback.setText("");
            ratingBar.setRating(0);
            currentId = null;
            tvStatus.setText("");
        });

        // ================= BACK =================
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, dashboardActivity.class));
            finish();
        });

        // ================= POPUP MENU =================
        findViewById(R.id.btnSubmit).setOnLongClickListener(v -> {
            showPopupMenu(v);
            return true;
        });
    }

    // 🔥 POPUP MENU FUNCTION
    private void showPopupMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.feedback_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {

            int id = item.getItemId();

            // DELETE
            if (id == R.id.menu_delete) {

                if (currentId != null) {
                    databaseReference.child(currentId).removeValue();
                    Toast.makeText(this, "Deleted ✔", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No data to delete", Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            // UPDATE
            if (id == R.id.menu_update) {

                HashMap<String, Object> update = new HashMap<>();
                update.put("name", etName.getText().toString());
                update.put("email", etEmail.getText().toString());
                update.put("feedback", etFeedback.getText().toString());
                update.put("category", spinnerCategory.getSelectedItem().toString());
                update.put("rating", ratingBar.getRating());

                if (currentId != null) {
                    databaseReference.child(currentId).updateChildren(update);
                    Toast.makeText(this, "Updated ✔", Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            return false;
        });

        popupMenu.show();
    }
}