package com.example.jobportalapplication;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyCompanyActivity extends AppCompatActivity {

    private ImageView logoUpload;
    private Button btnSave;
    private Uri selectedLogoUri;

    private SharedPreferences preferences;

    // Launcher to pick image
    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedLogoUri = uri;
                    logoUpload.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_company);

        // Corrected IDs to match XML
        logoUpload = findViewById(R.id.ivCompanyLogo);
        btnSave = findViewById(R.id.btnSubmitVerification);

        preferences = getSharedPreferences("CompanyPrefs", MODE_PRIVATE);

        // Pick logo
        logoUpload.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        // Save logo
        btnSave.setOnClickListener(v -> {
            if (selectedLogoUri != null) {
                preferences.edit().putString("company_logo_uri", selectedLogoUri.toString()).apply();
                Toast.makeText(this, "Logo Saved Successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please select a logo first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
