package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.data.DatabaseHelper;

public class DashboardActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText youtubeLinkInput;
        Button playBtn, addBtn, playlistBtn;
        int userId;

        youtubeLinkInput = findViewById(R.id.youtubeLinkInput);
        playBtn = findViewById(R.id.playButton);
        addBtn = findViewById(R.id.addToPlaylistButton);
        playlistBtn = findViewById(R.id.viewPlaylistButton);

        userId = getIntent().getIntExtra("user_id", -1);
        db = new DatabaseHelper(this);

        playBtn.setOnClickListener(v -> {
            String link = youtubeLinkInput.getText().toString().trim();
            Intent intent = new Intent(DashboardActivity.this, PlayerActivity.class);
            intent.putExtra("link", link);
            startActivity(intent);
        });

        addBtn.setOnClickListener(v -> {
            String link = youtubeLinkInput.getText().toString().trim();
            if (!link.isEmpty()) {
                db.addYouTubeLink(userId, link);
                Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show();
                youtubeLinkInput.setText("");
            }
        });

        playlistBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, PlayListActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

    }
}