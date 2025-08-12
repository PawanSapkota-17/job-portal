package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.R;
import com.example.jobportalapplication.RecruiterProfileActivity;

public class SettingActivity extends AppCompatActivity {

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting); // your layout XML filename

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to RecruiterProfileActivity
                Intent intent = new Intent(SettingActivity.this, RecruiterProfileActivity.class);
                startActivity(intent);
                finish(); // optional: finish this activity to remove it from back stack
            }
        });
    }
}
