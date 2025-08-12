package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordManagerActivity extends AppCompatActivity {

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager); // this should match your XML file name

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to RecruiterProfileActivity
                Intent intent = new Intent(PasswordManagerActivity.this, RecruiterProfileActivity.class);
                startActivity(intent);
                finish(); // closes the current activity
            }
        });
    }
}
