package com.example.jobportalapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class JobExperinceActivity extends AppCompatActivity {

    TextView tvJobTitle, tvCompany, tvDuration, tvResponsibilities;
    Button btnEditJob;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_experince);

        tvJobTitle = findViewById(R.id.tvJobTitle);
        tvCompany = findViewById(R.id.tvCompany);
        tvDuration = findViewById(R.id.tvDuration);
        tvResponsibilities = findViewById(R.id.tvResponsibilities);
        btnEditJob = findViewById(R.id.btnEditJob);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        loadData();

        btnEditJob.setOnClickListener(v -> showEditDialog());
    }

    private void loadData() {
        tvJobTitle.setText(sharedPreferences.getString("jobTitle", "Software Developer"));
        tvCompany.setText(sharedPreferences.getString("company", "ABC Corp"));
        tvDuration.setText(sharedPreferences.getString("duration", "2022-Present"));
        tvResponsibilities.setText(sharedPreferences.getString("responsibilities", "Develop Android applications"));
    }

    private void showEditDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_job, null);

        EditText etJobTitle = dialogView.findViewById(R.id.etJobTitle);
        EditText etCompany = dialogView.findViewById(R.id.etCompany);
        EditText etDuration = dialogView.findViewById(R.id.etDuration);
        EditText etResponsibilities = dialogView.findViewById(R.id.etResponsibilities);

        etJobTitle.setText(tvJobTitle.getText());
        etCompany.setText(tvCompany.getText());
        etDuration.setText(tvDuration.getText());
        etResponsibilities.setText(tvResponsibilities.getText());

        new AlertDialog.Builder(this)
                .setTitle("Edit Job Experience")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("jobTitle", etJobTitle.getText().toString());
                    editor.putString("company", etCompany.getText().toString());
                    editor.putString("duration", etDuration.getText().toString());
                    editor.putString("responsibilities", etResponsibilities.getText().toString());
                    editor.apply();
                    loadData();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
