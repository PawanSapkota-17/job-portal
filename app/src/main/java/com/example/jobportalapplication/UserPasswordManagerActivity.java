package com.example.jobportalapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserPasswordManagerActivity extends AppCompatActivity {

    private ImageView backButtonUser;
    private EditText etUserCurrentPassword, etUserNewPassword, etUserConfirmPassword;
    private Button btnUserSavePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_password_manager);

        // Initialize views
        backButtonUser = findViewById(R.id.backButtonUser);
        etUserCurrentPassword = findViewById(R.id.etUserCurrentPassword);
        etUserNewPassword = findViewById(R.id.etUserNewPassword);
        etUserConfirmPassword = findViewById(R.id.etUserConfirmPassword);
        btnUserSavePassword = findViewById(R.id.btnUserSavePassword);

        // Back button click → go back to previous screen
        backButtonUser.setOnClickListener(v -> onBackPressed());

        // Save password button click
        btnUserSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePasswordChange();
            }
        });
    }

    /**
     * Handles validation and password change process
     */
    private void handlePasswordChange() {
        String currentPassword = etUserCurrentPassword.getText().toString().trim();
        String newPassword = etUserNewPassword.getText().toString().trim();
        String confirmPassword = etUserConfirmPassword.getText().toString().trim();

        if (currentPassword.isEmpty()) {
            etUserCurrentPassword.setError("Enter current password");
            etUserCurrentPassword.requestFocus();
            return;
        }
        if (newPassword.isEmpty()) {
            etUserNewPassword.setError("Enter new password");
            etUserNewPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            etUserConfirmPassword.setError("Confirm new password");
            etUserConfirmPassword.requestFocus();
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this,
                    "New Password and Confirm Password do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Replace this with DB / SharedPreferences update logic
        Toast.makeText(this,
                "Password updated successfully!", Toast.LENGTH_SHORT).show();

        finish(); // Close activity after saving
    }
}
