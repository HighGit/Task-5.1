package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String link = getIntent().getStringExtra("link");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        // Optional: Try to open directly in YouTube app
        intent.setPackage("com.google.android.youtube");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Fallback to browser if YouTube app is not installed
            intent.setPackage(null);
            startActivity(intent);
        }

        // Optional: Close the activity so the user returns to the previous screen after watching
        finish();
    }
}



