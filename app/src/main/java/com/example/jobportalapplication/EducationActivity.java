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

public class EducationActivity extends AppCompatActivity {

    TextView tvDegree, tvInstitute, tvYear, tvGrade;
    Button btnEditEducation;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        tvDegree = findViewById(R.id.tvDegree);
        tvInstitute = findViewById(R.id.tvInstitute);
        tvYear = findViewById(R.id.tvYear);
        tvGrade = findViewById(R.id.tvGrade);
        btnEditEducation = findViewById(R.id.btnEditEducation);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        loadData();

        btnEditEducation.setOnClickListener(v -> showEditDialog());
    }

    private void loadData() {
        tvDegree.setText(sharedPreferences.getString("degree", "B.Sc Computer Science"));
        tvInstitute.setText(sharedPreferences.getString("institute", "XYZ University"));
        tvYear.setText(sharedPreferences.getString("year", "2018-2022"));
        tvGrade.setText(sharedPreferences.getString("grade", "CGPA: 3.8"));
    }

    private void showEditDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_education, null);
        EditText etDegree = dialogView.findViewById(R.id.etDegree);
        EditText etInstitute = dialogView.findViewById(R.id.etInstitute);
        EditText etYear = dialogView.findViewById(R.id.etYear);
        EditText etGrade = dialogView.findViewById(R.id.etGrade);

        etDegree.setText(tvDegree.getText());
        etInstitute.setText(tvInstitute.getText());
        etYear.setText(tvYear.getText());
        etGrade.setText(tvGrade.getText());

        new AlertDialog.Builder(this)
                .setTitle("Edit Education")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("degree", etDegree.getText().toString());
                    editor.putString("institute", etInstitute.getText().toString());
                    editor.putString("year", etYear.getText().toString());
                    editor.putString("grade", etGrade.getText().toString());
                    editor.apply();
                    loadData();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
