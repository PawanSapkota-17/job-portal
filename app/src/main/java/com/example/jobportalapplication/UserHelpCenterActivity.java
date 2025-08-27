package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityUserHelpCenterBinding;

public class UserHelpCenterActivity extends AppCompatActivity {

    private ActivityUserHelpCenterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityUserHelpCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.backButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserHelpCenterActivity.this, UserProfileActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
