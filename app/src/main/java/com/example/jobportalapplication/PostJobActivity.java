package com.example.jobportalapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityPostJobBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PostJobActivity extends AppCompatActivity {

    ActivityPostJobBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
                startActivity(new Intent(this, RecruiterProfileActivity.class));
                overridePendingTransition(0,0);
                return true;
            }

            return false;
        });

        // 🔙 Back Button
        binding.backButton.setOnClickListener(v -> {
            startActivity(new Intent(PostJobActivity.this, RecruiterHomePageActivity.class));
            finish();
        });

        // ✅ Spinner Setup
        setupSpinner(binding.spinnerJobType, new String[]{"Full Time", "Part Time", "Freelance"});
        setupSpinner(binding.spinnerExperience, new String[]{"Fresher", "Mid-Level", "5+ Years"});
        setupSpinner(binding.spinnerJobLevel, new String[]{"Entry", "Mid", "Senior"});
        setupSpinner(binding.spinnerModel, new String[]{"Remote", "Onsite", "Hybrid"});

        // ✅ Post Job Button
        binding.buttonPostJob.setOnClickListener(v -> {
            String title = binding.editJobTitle.getText().toString().trim();
            String salary = binding.editSalary.getText().toString().trim();
            String location = binding.editLocation.getText().toString().trim();
            String company = "Your Company"; // Optional: add input for company
            String type = binding.spinnerJobType.getSelectedItem().toString();
            String experience = binding.spinnerExperience.getSelectedItem().toString();
            String level = binding.spinnerJobLevel.getSelectedItem().toString();
            String model = binding.spinnerModel.getSelectedItem().toString();

            if (title.isEmpty() || salary.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Job posted successfully!", Toast.LENGTH_LONG).show();

                // 🔔 Send notification
                sendJobNotification(title);

                // 🟢 Save job to SharedPreferences
                saveJobToPreferences(title, company, location, type, salary, model, level);

                // 🟢 Navigate to JobListActivity
                Intent intent = new Intent(PostJobActivity.this, RecruiterHomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // ✅ Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    startActivity(new Intent(PostJobActivity.this, RecruiterHomePageActivity.class));
                    return true;
                } else if (id == R.id.nav_add) {
                    return true; // current screen
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(PostJobActivity.this, RecruiterProfileActivity.class));
                    return true;
                }

                return false;
            }
        });
    }

    // ✅ Spinner adapter
    private void setupSpinner(android.widget.Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    // 🔔 Send notification method
    private void sendJobNotification(String jobTitle) {
        SharedPreferences prefs = getSharedPreferences("notifications_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Get existing notifications
        String current = prefs.getString("notifications_list", "");

        // Append new notification at the start
        String newNotification = jobTitle + "||" + current;
        editor.putString("notifications_list", newNotification);
        editor.apply();
    }

    // 🟢 Save job to SharedPreferences for JobListActivity
    private void saveJobToPreferences(String title, String company, String location,
                                      String type, String salary, String mode, String level) {
        SharedPreferences prefs = getSharedPreferences("jobs_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Get existing jobs
        String existingJobs = prefs.getString("jobs_list", "");

        // Format: Title,Company,Location,Type,Salary,Mode,Level
        String newJob = title + "," + company + "," + location + "," + type + "," + salary + "," + mode + "," + level;

        // Append new job using || separator
        if (!existingJobs.isEmpty()) {
            existingJobs += "||" + newJob;
        } else {
            existingJobs = newJob;
        }

        editor.putString("jobs_list", existingJobs);
        editor.apply();
    }
}
