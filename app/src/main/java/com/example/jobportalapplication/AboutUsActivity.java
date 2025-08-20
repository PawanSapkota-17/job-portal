package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus); // your XML file name

        // Corrected ID to match XML
        ivBack = findViewById(R.id.backButton);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to RecruiterProfileActivity
                Intent intent = new Intent(AboutUsActivity.this, RecruiterProfileActivity.class);
                startActivity(intent);
                finish(); // optional: close current activity
            }
        });
    }
}
