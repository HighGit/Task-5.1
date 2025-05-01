package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.data.DatabaseHelper;
import com.example.myapplication.model.User;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText sUsernameEditText = findViewById(R.id.sUsernameEditText);
        EditText sPasswordEditText = findViewById(R.id.sPasswordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswprdEditText);
        Button saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sUsernameEditText.getText().toString();
                String password = sPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (password.equals(confirmPassword))
                {
                    long result = db.insertUser(new User(username, password));
                    if (result > 0)
                    {
                        Toast.makeText(SignupActivity.this, "User created", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(SignupActivity.this, "Failed to create a user", Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(SignupActivity.this, "Two passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}