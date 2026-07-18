package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMIACTIVITY extends AppCompatActivity {


    EditText edt1, edt2, edt3;
    TextView result;
    Button btn, btnHistory;
    LinearLayout bg_color;
    ImageButton btnBack;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        edt1 = findViewById(R.id.weight);
        edt2 = findViewById(R.id.feet);
        edt3 = findViewById(R.id.inches);

        btn = findViewById(R.id.btn);
        btnHistory = findViewById(R.id.btnHistory);

        result = findViewById(R.id.result);
        bg_color = findViewById(R.id.color);
        btnBack = findViewById(R.id.btnBack);

        db = new DatabaseHelper(this);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(BMIACTIVITY.this,
                    dashboardActivity.class);
            startActivity(intent);
            finish();
        });

        btn.setOnClickListener(view -> {

            if (edt1.getText().toString().isEmpty()
                    || edt2.getText().toString().isEmpty()
                    || edt3.getText().toString().isEmpty()) {

                Toast.makeText(BMIACTIVITY.this,
                        "Please enter all values",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double weight =
                    Double.parseDouble(edt1.getText().toString());

            double feet =
                    Double.parseDouble(edt2.getText().toString());

            double inches =
                    Double.parseDouble(edt3.getText().toString());

            double totalInches = feet * 12 + inches;
            double meters = (totalInches * 2.54) / 100;

            double bmi = weight / (meters * meters);

            if (bmi > 25) {

                result.setText("Overweight");
                result.setTextColor(getResources().getColor(android.R.color.holo_red_dark));

                bg_color.setBackgroundColor(
                        getResources().getColor(android.R.color.holo_red_light));

            } else if (bmi < 18) {

                result.setText("Underweight");
                result.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

                bg_color.setBackgroundColor(
                        getResources().getColor(android.R.color.holo_orange_light));

            } else {

                result.setText("Healthy");
                result.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

                bg_color.setBackgroundColor(
                        getResources().getColor(android.R.color.holo_green_light));
            }

            boolean inserted =
                    db.insertBMI(weight, meters, bmi);

            if (inserted) {

                Toast.makeText(BMIACTIVITY.this,
                        "BMI Saved",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(BMIACTIVITY.this,
                        "Save Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(BMIACTIVITY.this,
                            bmi_HistoryActivity.class);

            startActivity(intent);
        });
    }


}
