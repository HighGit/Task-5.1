package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.data.DatabaseHelper;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> linkList;
    DatabaseHelper db;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        userId = getIntent().getIntExtra("user_id", -1);
        db = new DatabaseHelper(this);

        linkList = db.getYouTubeLinks(userId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (String link : linkList) {
            Log.d("LINK_DEBUG", link);
        }
        recyclerView.setAdapter(new PlaylistAdapter(linkList));
    }
}