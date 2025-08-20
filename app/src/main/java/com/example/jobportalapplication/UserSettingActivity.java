package com.example.jobportalapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserSettingActivity extends AppCompatActivity {

    ImageView backButtonSettings;
    Switch switchNotifications, switchDarkMode;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        // ✅ View bindings
        backButtonSettings = findViewById(R.id.backButtonSettings);
        switchNotifications = findViewById(R.id.switchNotifications);
        switchDarkMode = findViewById(R.id.switchDarkMode); // ✅ YOU MISSED THIS

        // ✅ SharedPreferences
        sharedPreferences = getSharedPreferences("UserSettings", MODE_PRIVATE);

        // ✅ Load saved settings
        switchNotifications.setChecked(sharedPreferences.getBoolean("notifications", true));
        switchDarkMode.setChecked(sharedPreferences.getBoolean("darkMode", false));

        // ✅ Button back
        backButtonSettings.setOnClickListener(v -> finish());

        // ✅ Listeners
        switchNotifications.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            sharedPreferences.edit().putBoolean("notifications", isChecked).apply();
            Toast.makeText(this, "Notifications " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
        });

        switchDarkMode.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            sharedPreferences.edit().putBoolean("darkMode", isChecked).apply();
            Toast.makeText(this, "Dark Mode " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
        });
    }
}
