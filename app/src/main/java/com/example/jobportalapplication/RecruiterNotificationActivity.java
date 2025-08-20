package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityRecruiterNotificationBinding;

public class RecruiterNotificationActivity extends AppCompatActivity {
    ActivityRecruiterNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRecruiterNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 🔙 Back button
        binding.backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecruiterProfileActivity.class);
            startActivity(intent);
            finish();
        });

        // 🔽 Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, RecruiterHomePageActivity.class));
                overridePendingTransition(0,0);
                return true;

            } else if (id == R.id.nav_add) {
                startActivity(new Intent(this, PostJobActivity.class));
                overridePendingTransition(0,0);
                return true;

            } else if (id == R.id.nav_notification) {
                // ✅ Stay on this page (RecruiterNotificationActivity)
                return true;

            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, RecruiterProfileActivity.class));
                overridePendingTransition(0,0);
                return true;
            }

            return false;
        });
    }
}
