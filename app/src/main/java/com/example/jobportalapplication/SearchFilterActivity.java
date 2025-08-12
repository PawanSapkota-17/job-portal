package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivitySearchFilterBinding;

public class SearchFilterActivity extends AppCompatActivity {

    ActivitySearchFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 🧩 Populate dropdowns
        setupSpinner(binding.spinnerLocation, new String[]{"Kathmandu", "Chitwan", "Pokhera"});
        setupSpinner(binding.spinnerSort, new String[]{"Most Recent", "Highest Salary", "Popular"});
        setupSpinner(binding.spinnerNature, new String[]{"Full Time", "Part Time", "Remote"});

        // 🔙 Back navigation
        binding.backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchFilterActivity.this, RecruiterHomePageActivity.class);
            startActivity(intent);
            finish();
        });

        // ♻️ Reset filters to default
        binding.resetFilterButton.setOnClickListener(v -> {
            binding.spinnerLocation.setSelection(0);
            binding.spinnerSort.setSelection(0);
            binding.spinnerNature.setSelection(0);
            Toast.makeText(this, "Filters reset!", Toast.LENGTH_SHORT).show();
        });

        // ✅ Apply filters and send to RecruiterHomePageActivity
        binding.applyFilterButton.setOnClickListener(v -> {
            String location = binding.spinnerLocation.getSelectedItem().toString();
            String sort = binding.spinnerSort.getSelectedItem().toString();
            String nature = binding.spinnerNature.getSelectedItem().toString();

            Intent intent = new Intent(SearchFilterActivity.this, RecruiterHomePageActivity.class);
            intent.putExtra("filter_location", location);
            intent.putExtra("filter_sort", sort);
            intent.putExtra("filter_nature", nature);

            Toast.makeText(this, "Filter Applied:\n" + location + ", " + sort + ", " + nature, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        });
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
}
