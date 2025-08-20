package com.example.jobportalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 101;

    private ImageView backButton, profileImage, editImageIcon;
    private TextView profileName, profileRole, profileEmail, profilePhone;
    private LinearLayout aboutMeRow, passwordManagerRow, educationRow, skillsRow, jobExpRow;
    private LinearLayout settingsRow, privacyRow, helpRow, logoutRow;
    BottomNavigationView bottomNavigationView;


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Highlight the current selected item
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(this, UserHomePageActivity.class);
                startActivity(intent);
            }
            else if (itemId == R.id.nav_add) {
                startActivity(new Intent(this, JobListActivity.class));
            } else if (itemId == R.id.nav_notification) {
                startActivity(new Intent(this, NotificationActivity.class));
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, UserProfileActivity.class));
            }
            overridePendingTransition(0, 0); // no animation
            finish(); // close current activity
            return true;
        });


        // Views
        backButton = findViewById(R.id.backButton);
        profileImage = findViewById(R.id.profileImage);
        editImageIcon = findViewById(R.id.editImageIcon);
        profileName = findViewById(R.id.profileName);
        profileRole = findViewById(R.id.profileRole);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);

        aboutMeRow = findViewById(R.id.aboutMeRow);
        passwordManagerRow = findViewById(R.id.passwordManagerRow);
        educationRow = findViewById(R.id.educationRow);
        skillsRow = findViewById(R.id.skillsRow);
        jobExpRow = findViewById(R.id.jobExpRow);

        settingsRow = findViewById(R.id.settingsRow);
        privacyRow = findViewById(R.id.privacyRow);
        helpRow = findViewById(R.id.helpRow);
        logoutRow = findViewById(R.id.logoutRow);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        // Load saved user data
        loadUserData();

        // Back button functionality
        backButton.setOnClickListener(v -> finish());

        // Row navigation
        aboutMeRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, AboutMeActivity.class)));
        passwordManagerRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, UserPasswordManagerActivity.class)));
        educationRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, EducationActivity.class)));
        skillsRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, SkillsActivity.class)));
        jobExpRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, JobExperinceActivity.class)));
        settingsRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, UserSettingActivity.class)));
        privacyRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, UserPrivacyActivity.class)));
        helpRow.setOnClickListener(v -> startActivity(new Intent(UserProfileActivity.this, UserHelpCenterActivity.class)));

        // Log out functionality
        logoutRow.setOnClickListener(v -> {
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Profile image and edit icon click - pick image from gallery
        profileImage.setOnClickListener(v -> openImagePicker());
        editImageIcon.setOnClickListener(v -> openImagePicker());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        profileName.setText(sharedPreferences.getString("fullName", "User Name"));
        profileRole.setText(sharedPreferences.getString("role", "Role/Position"));
        profileEmail.setText(sharedPreferences.getString("email", "Email"));
        profilePhone.setText(sharedPreferences.getString("phone", "Phone"));

        String imageBase64 = sharedPreferences.getString("profileImage", "");
        if (!imageBase64.isEmpty()) {
            byte[] bytes = Base64.decode(imageBase64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            profileImage.setImageBitmap(bitmap);
        } else {
            profileImage.setImageResource(R.drawable.ic_person);
        }
    }

    // Open gallery to pick an image
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    Bitmap selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    profileImage.setImageBitmap(selectedBitmap);
                    saveImageToPreferences(selectedBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Convert bitmap to Base64 and save to SharedPreferences
    private void saveImageToPreferences(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        String imageBase64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        sharedPreferences.edit().putString("profileImage", imageBase64).apply();
    }
}
