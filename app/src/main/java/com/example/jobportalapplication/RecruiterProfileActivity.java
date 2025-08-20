package com.example.jobportalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityRecruiterProfileBinding;

public class RecruiterProfileActivity extends AppCompatActivity {

    ActivityRecruiterProfileBinding binding;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ✅ Inflate with binding
        binding = ActivityRecruiterProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ✅ Bottom Navigation
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
                startActivity(new Intent(this, RecruiterNotificationActivity.class));
                overridePendingTransition(0,0);
                return true;

            } else if (id == R.id.nav_profile) {
                // Already in profile, do nothing
                return true;
            }

            return false;
        });

        // ✅ SharedPreferences for company logo
        preferences = getSharedPreferences("CompanyPrefs", MODE_PRIVATE);
        String logoUriString = preferences.getString("company_logo_uri", null);
        if (logoUriString != null) {
            binding.companyLogoImageView.setImageURI(Uri.parse(logoUriString));
        }

        // ✅ Log Out Row Click
        binding.logoutRow.setOnClickListener(v -> {
            // Clear session if needed
            Intent intent = new Intent(RecruiterProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // ✅ About Us Row Click
        binding.aboutUsRow.setOnClickListener(v -> {
            Intent intent = new Intent(RecruiterProfileActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }
}
