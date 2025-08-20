package com.example.jobportalapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserPrivacyActivity extends AppCompatActivity {

    ImageView backButtonPrivacy;
    TextView tvPrivacyPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_privacy);

        backButtonPrivacy = findViewById(R.id.backButton);
        tvPrivacyPolicy = findViewById(R.id.tvPrivacyPolicy);  // <-- Add this line

        backButtonPrivacy.setOnClickListener(v -> finish());

        // Sample privacy policy text
        tvPrivacyPolicy.setText(
                "Privacy Policy\n\n" +
                        "1. We respect your personal data.\n" +
                        "2. Your profile information is visible to recruiters only.\n" +
                        "3. We use cookies to improve your experience.\n" +
                        "4. You can request deletion of your data anytime."
        );
    }
}
