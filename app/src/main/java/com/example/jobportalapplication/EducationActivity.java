package com.example.jobportalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityEducationBinding;
import com.example.jobportalapplication.databinding.DialogEditEducationBinding;

public class EducationActivity extends AppCompatActivity {

    private ActivityEducationBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityEducationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // SharedPreferences
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        loadData();

        // Back arrow click -> navigate to UserProfileActivity
        binding.backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(EducationActivity.this, UserProfileActivity.class);
            startActivity(intent);
            finish();
        });

        // Edit Education button
        binding.btnEditEducation.setOnClickListener(v -> showEditDialog());
    }

    private void loadData() {
        binding.tvDegree.setText(sharedPreferences.getString("degree", "B.Sc Computer Science"));
        binding.tvInstitute.setText(sharedPreferences.getString("institute", "XYZ University"));
        binding.tvYear.setText(sharedPreferences.getString("year", "2018-2022"));
        binding.tvGrade.setText(sharedPreferences.getString("grade", "CGPA: 3.8"));
    }

    private void showEditDialog() {
        // Use dialog binding
        DialogEditEducationBinding dialogBinding = DialogEditEducationBinding.inflate(LayoutInflater.from(this));

        // Set current values
        dialogBinding.etDegree.setText(binding.tvDegree.getText());
        dialogBinding.etInstitute.setText(binding.tvInstitute.getText());
        dialogBinding.etYear.setText(binding.tvYear.getText());
        dialogBinding.etGrade.setText(binding.tvGrade.getText());

        // Create dialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit Education")
                .setView(dialogBinding.getRoot())
                .setPositiveButton("Save", (d, which) -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("degree", dialogBinding.etDegree.getText().toString());
                    editor.putString("institute", dialogBinding.etInstitute.getText().toString());
                    editor.putString("year", dialogBinding.etYear.getText().toString());
                    editor.putString("grade", dialogBinding.etGrade.getText().toString());
                    editor.apply();
                    loadData();
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
    }
}
