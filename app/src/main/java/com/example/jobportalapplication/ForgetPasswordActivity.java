package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityForgetPasswordBinding;
import com.example.jobportalapplication.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {

    private com.example.jobportalapplication.databinding.ActivityForgetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(ForgetPasswordActivity.this, OTPVerificationActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        // Optional: back button logic
        binding.backButton.setOnClickListener(v -> onBackPressed());
    }
}