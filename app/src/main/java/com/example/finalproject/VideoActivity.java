package com.example.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    Button btnPushupVideo, btnYogaVideo, btnCardioVideo;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // CONNECT IDS
        btnPushupVideo = findViewById(R.id.btnPushupVideo);
        btnYogaVideo = findViewById(R.id.btnYogaVideo);
        btnCardioVideo = findViewById(R.id.btnCardioVideo);

        btnBack = findViewById(R.id.btnBack);

        // BACK BUTTON
        btnBack.setOnClickListener(v -> {

            Intent intent = new Intent(VideoActivity.this,
                    ExerciseActivity.class);

            startActivity(intent);
            finish();
        });

        // PUSHUP VIDEO
        btnPushupVideo.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=IODxDxX7oi4"));

            startActivity(intent);
        });

        // YOGA VIDEO
        btnYogaVideo.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=v7AYKMP6rOE"));

            startActivity(intent);
        });

        // CARDIO VIDEO
        btnCardioVideo.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI"));

            startActivity(intent);
        });
    }
}