package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VerifyCompanyActivity extends AppCompatActivity {

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_company); // match this to your XML filename

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to RecruiterProfileActivity
                Intent intent = new Intent(VerifyCompanyActivity.this, RecruiterProfileActivity.class);
                startActivity(intent);
                finish(); // Close current activity
            }
        });
    }
}
