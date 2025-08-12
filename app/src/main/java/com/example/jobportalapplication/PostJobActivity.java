package com.example.jobportalapplication;

import android.content.Intent;
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
            String desc = binding.editDescription.getText().toString().trim();

            if (title.isEmpty() || salary.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Job posted successfully!", Toast.LENGTH_LONG).show();
                // TODO: Save to DB
            }
        });

        // ✅ Bottom Navigation (using if statements)
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
                    startActivity(new Intent(PostJobActivity.this,
                            RecruiterProfileActivity.class));
                    return true;
                }

                return false;
            }
        });
    }

    // ✅ Spinner adapter
    private void setupSpinner(android.widget.Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
}
