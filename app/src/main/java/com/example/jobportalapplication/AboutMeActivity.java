package com.example.jobportalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AboutMeActivity extends AppCompatActivity {

    private ImageView backButton, profileImage;
    private EditText editFullName, editRole, editEmail, editPhone, editBio;
    private Button btnSave;

    private SharedPreferences sharedPreferences;

    private ActivityResultLauncher<Intent> pickImageLauncher;
    private byte[] profileImageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        backButton = findViewById(R.id.backButton);
        profileImage = findViewById(R.id.profileImage);
        editFullName = findViewById(R.id.editFullName);
        editRole = findViewById(R.id.editRole);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editBio = findViewById(R.id.editBio);
        btnSave = findViewById(R.id.btnSave);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        // Load saved data
        loadUserData();

        // Back button functionality
        backButton.setOnClickListener(v -> finish());

        // Image picker launcher
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            profileImage.setImageBitmap(bitmap);

                            // Convert bitmap to byte array
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            profileImageBytes = baos.toByteArray();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        // Click on profile image to select new image
        profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        });

        // Save button functionality
        btnSave.setOnClickListener(v -> saveUserData());
    }

    private void loadUserData() {
        editFullName.setText(sharedPreferences.getString("fullName", ""));
        editRole.setText(sharedPreferences.getString("role", ""));
        editEmail.setText(sharedPreferences.getString("email", ""));
        editPhone.setText(sharedPreferences.getString("phone", ""));
        editBio.setText(sharedPreferences.getString("bio", ""));

        String imageBase64 = sharedPreferences.getString("profileImage", "");
        if (!imageBase64.isEmpty()) {
            byte[] bytes = android.util.Base64.decode(imageBase64, android.util.Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            profileImage.setImageBitmap(bitmap);
            profileImageBytes = bytes;
        }
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullName", editFullName.getText().toString().trim());
        editor.putString("role", editRole.getText().toString().trim());
        editor.putString("email", editEmail.getText().toString().trim());
        editor.putString("phone", editPhone.getText().toString().trim());
        editor.putString("bio", editBio.getText().toString().trim());

        // Save profile image
        if (profileImageBytes != null) {
            String imageBase64 = android.util.Base64.encodeToString(profileImageBytes, android.util.Base64.DEFAULT);
            editor.putString("profileImage", imageBase64);
        }

        editor.apply();

        finish(); // Return to UserProfileActivity
    }
}
