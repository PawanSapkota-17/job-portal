package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Login button click
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString().trim();
                String password = binding.password.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    // Navigate to RecruiterHomePageActivity
                    Intent intent = new Intent(LoginActivity.this, RecruiterHomePageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (TextUtils.isEmpty(username)) {
                        binding.username.setError("Required");
                    }
                    if (TextUtils.isEmpty(password)) {
                        binding.password.setError("Required");
                    }
                }
            }
        });

        // SignUp text click
        binding.signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Forget password click
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
