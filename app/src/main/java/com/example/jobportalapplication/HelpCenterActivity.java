package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpCenterActivity extends AppCompatActivity {

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center); // Your XML layout

        // Corrected ID to match XML
        ivBack = findViewById(R.id.backButton);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to RecruiterProfileActivity
                Intent intent = new Intent(HelpCenterActivity.this, RecruiterProfileActivity.class);
                startActivity(intent);
                finish(); // Prevent returning here with back button
            }
        });

        // You can add other logic here later if needed
    }
}
