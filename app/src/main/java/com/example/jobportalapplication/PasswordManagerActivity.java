package com.example.jobportalapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityPasswordManagerBinding;

public class PasswordManagerActivity extends AppCompatActivity {

    private ActivityPasswordManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Back button functionality
        binding.backButton.setOnClickListener(v -> finish());

        // Save button click
        binding.btnSavePassword.setOnClickListener(v -> {
            String current = binding.etCurrentPassword.getText().toString().trim();
            String newPass = binding.etNewPassword.getText().toString().trim();
            String confirm = binding.etConfirmPassword.getText().toString().trim();

            if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirm)) {
                Toast.makeText(this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
