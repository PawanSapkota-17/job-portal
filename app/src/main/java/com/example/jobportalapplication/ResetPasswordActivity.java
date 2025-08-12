package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.eyeNewPassword.setOnClickListener(v -> togglePasswordVisibility(
                binding.newPassword, binding.eyeNewPassword, true));

        binding.eyeConfirmPassword.setOnClickListener(v -> togglePasswordVisibility(
                binding.confirmPassword, binding.eyeConfirmPassword, false));

        binding.resetButton.setOnClickListener(v -> {
            String newPassword = binding.newPassword.getText().toString().trim();
            String confirmPassword = binding.confirmPassword.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.backButton.setOnClickListener(v -> onBackPressed());
    }

    private void togglePasswordVisibility(View field, View eyeIcon, boolean isNew) {
        if (field instanceof android.widget.EditText) {
            android.widget.EditText editText = (android.widget.EditText) field;
            boolean isVisible = isNew ? isNewPasswordVisible : isConfirmPasswordVisible;
            if (isVisible) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ((android.widget.ImageView) eyeIcon).setImageResource(R.drawable.ic_eye);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ((android.widget.ImageView) eyeIcon).setImageResource(R.drawable.ic_eye);
            }
            editText.setSelection(editText.getText().length());
            if (isNew) {
                isNewPasswordVisible = !isNewPasswordVisible;
            } else {
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
            }
        }
    }
}
