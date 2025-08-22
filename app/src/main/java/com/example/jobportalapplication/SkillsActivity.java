package com.example.jobportalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SkillsActivity extends AppCompatActivity {

    TextView tvSkills;
    Button btnEditSkills;
    SharedPreferences sharedPreferences;
    ImageView backArrow; // Added for back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        tvSkills = findViewById(R.id.tvSkillsList);
        btnEditSkills = findViewById(R.id.btnEditSkills);
        backArrow = findViewById(R.id.backArrow); // Link to XML
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        loadData();

        btnEditSkills.setOnClickListener(v -> showEditDialog());

        // Back button click → go to UserProfileActivity
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(SkillsActivity.this, UserProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadData() {
        tvSkills.setText(sharedPreferences.getString("skills", "Java, Android, SQL"));
    }

    private void showEditDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_skills, null);
        EditText etSkills = dialogView.findViewById(R.id.etSkills);
        etSkills.setText(tvSkills.getText());

        new AlertDialog.Builder(this)
                .setTitle("Edit Skills (comma separated)")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("skills", etSkills.getText().toString());
                    editor.apply();
                    loadData();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
