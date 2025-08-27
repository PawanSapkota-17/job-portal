package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchFilterActivity extends AppCompatActivity {

    private ImageView backButton;
    private EditText etLocation;
    private Spinner spinnerSort, spinnerNature;
    private Button resetFilterButton, applyFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        // Views
        backButton = findViewById(R.id.backButton);
        etLocation = findViewById(R.id.etLocation);
        spinnerSort = findViewById(R.id.spinnerSort);
        spinnerNature = findViewById(R.id.spinnerNature);
        resetFilterButton = findViewById(R.id.resetFilterButton);
        applyFilterButton = findViewById(R.id.applyFilterButton);

        // Back button -> navigate to previous screen
        backButton.setOnClickListener(v -> finish());

        // Sort By options (without Highest Salary)
        String[] sortOptions = {"Recent", "Popular"};
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sortOptions);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(sortAdapter);

        // Job Nature options
        String[] natureOptions = {"Full Time", "Part Time", "Remote", "Internship"};
        ArrayAdapter<String> natureAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, natureOptions);
        natureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNature.setAdapter(natureAdapter);

        // Apply Filter Button with validation
        applyFilterButton.setOnClickListener(v -> {
            String location = etLocation.getText().toString().trim();
            String sortBy = spinnerSort.getSelectedItem().toString();
            String jobNature = spinnerNature.getSelectedItem().toString();

            if (TextUtils.isEmpty(location)) {
                etLocation.setError("Please enter location");
                etLocation.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(sortBy)) {
                Toast.makeText(this, "Please select sort option", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(jobNature)) {
                Toast.makeText(this, "Please select job nature", Toast.LENGTH_SHORT).show();
                return;
            }

            // Navigate to UserHomePageActivity after validation
            Intent intent = new Intent(SearchFilterActivity.this, UserHomePageActivity.class);
            // Optionally pass the filter data
            intent.putExtra("location", location);
            intent.putExtra("sortBy", sortBy);
            intent.putExtra("jobNature", jobNature);
            startActivity(intent);
        });

        // Reset Button
        resetFilterButton.setOnClickListener(v -> resetFilters());
    }

    private void resetFilters() {
        etLocation.setText("");
        spinnerSort.setSelection(0);
        spinnerNature.setSelection(0);
    }
}
