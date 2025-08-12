package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityRecruiterSignupBinding;

public class RecruiterSignupActivity extends AppCompatActivity {

    private ActivityRecruiterSignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecruiterSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // If user clicks "Job Seeker" button, navigate to SignupActivity
        binding.jobSeekerBtnBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        });

        // Sign Up button click listener
        binding.SignUpBtn.setOnClickListener(v -> {
            String companyName = binding.companyFullName.getText().toString().trim();
            String companyId = binding.idField.getText().toString().trim();
            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String phone = binding.phone.getText().toString().trim();
            String location = binding.location.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(companyName)) {
                Toast.makeText(this, "Enter company full name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(companyId)) {
                Toast.makeText(this, "Enter company ID", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(location)) {
                Toast.makeText(this, "Enter location", Toast.LENGTH_SHORT).show();
                return;
            }

            // All fields valid: show success toast and navigate to LoginActivity
            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
