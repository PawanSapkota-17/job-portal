package com.example.jobportalapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class SettingActivity extends AppCompatActivity {

    private Switch switchDarkMode, switchNotifications;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "app_settings";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_NOTIFICATIONS = "notifications";

    private static final String CHANNEL_ID = "job_portal_notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Init UI
        ImageView backButton = findViewById(R.id.backButton);
         switchNotifications = findViewById(R.id.switchNotifications);
        TextView changePassword = findViewById(R.id.changePassword);
        TextView privacyPolicy = findViewById(R.id.privacyPolicy);
        TextView termsConditions = findViewById(R.id.termsConditions);

        // Init SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load saved states
        boolean isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false);
        boolean isNotificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS, true);

        switchDarkMode.setChecked(isDarkMode);
        switchNotifications.setChecked(isNotificationsEnabled);

        // Apply Dark Mode if saved
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Create Notification Channel (Android 8+)
        createNotificationChannel();

        // Dark Mode Switch
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_DARK_MODE, isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Notifications Switch
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_NOTIFICATIONS, isChecked);
            editor.apply();

        });

        // Back button
        backButton.setOnClickListener(v -> finish());

        // Extra actions
        changePassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, PasswordManagerActivity.class);
            startActivity(intent);
        });

        privacyPolicy.setOnClickListener(v ->
                Toast.makeText(this, "Privacy Policy clicked", Toast.LENGTH_SHORT).show());

        termsConditions.setOnClickListener(v ->
                Toast.makeText(this, "Terms & Conditions clicked", Toast.LENGTH_SHORT).show());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Job Portal Notifications";
            String description = "Channel for Job Portal app notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}
