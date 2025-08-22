package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Back arrow click listener
        binding.backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Navigate to RecruiterSignupActivity when jobProviderBtn clicked
        binding.jobProviderBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, RecruiterSignupActivity.class));
            finish();
        });

        // Sign Up button click listener with validation
        binding.SignUpBtn.setOnClickListener(v -> {
            String username = binding.usernameField.getText().toString().trim(); // ✅ FIXED
            String fullName = binding.fullName.getText().toString().trim();
            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String phone = binding.phone.getText().toString().trim();
            String skills = binding.skills.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(fullName)) {
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(skills)) {
                Toast.makeText(this, "Please enter your skills or experience", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

            // Navigate to LoginActivity
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
