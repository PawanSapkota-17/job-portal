package com.example.jobportalapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {

    LinearLayout jobListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        jobListContainer = findViewById(R.id.jobListContainer);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Highlight the current selected item
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(this,UserHomePageActivity.class);
                startActivity(intent);                return true;
            } else if (itemId == R.id.nav_add) {
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

        // -----------------
        // STATIC JOBS
        // -----------------
        addJobCard("Software Engineer", "Google", "New York, USA", "Full Time", "$80,000", "Remote", "Entry");
        addJobCard("UI Designer", "Meta", "Seattle, USA", "Full Time", "$70,000", "Onsite", "Mid");
        addJobCard("Frontend Developer", "Apple", "San Francisco, USA", "Full Time", "$75,000", "Hybrid", "Mid");
        addJobCard("Backend Developer", "Amazon", "London, UK", "Full Time", "$65,000", "Remote", "Senior");
        addJobCard("Data Scientist", "Tesla", "Berlin, Germany", "Full Time", "$85,000", "Remote", "Senior");

        // -----------------
        // DYNAMIC JOBS FROM SharedPreferences
        // -----------------
        loadJobsFromPreferences();
    }

    // -----------------
    // METHOD TO ADD JOB CARD
    // -----------------
    private void addJobCard(String title, String company, String location, String type,
                            String salary, String mode, String level) {
        LinearLayout jobCard = new LinearLayout(this);
        jobCard.setOrientation(LinearLayout.VERTICAL);
        jobCard.setBackgroundResource(R.drawable.card_background);
        jobCard.setPadding(32, 32, 32, 32);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        jobCard.setLayoutParams(params);

        TextView tvCompany = new TextView(this);
        tvCompany.setText(company);
        tvCompany.setTextSize(16);
        tvCompany.setTypeface(Typeface.DEFAULT_BOLD);
        jobCard.addView(tvCompany);

        TextView tvLocation = new TextView(this);
        tvLocation.setText(location);
        tvLocation.setTextColor(Color.parseColor("#777777"));
        jobCard.addView(tvLocation);

        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextColor(Color.parseColor("#2196F3"));
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        tvTitle.setTextSize(16);
        jobCard.addView(tvTitle);

        TextView tvTypeLevel = new TextView(this);
        tvTypeLevel.setText(type + " • " + mode + " • " + level);
        tvTypeLevel.setTextColor(Color.parseColor("#4CAF50"));
        tvTypeLevel.setTextSize(12);
        jobCard.addView(tvTypeLevel);

        TextView tvSalary = new TextView(this);
        tvSalary.setText("Salary: " + salary);
        tvSalary.setTextSize(14);
        jobCard.addView(tvSalary);

        Button btnApply = new Button(this);
        btnApply.setText("Apply");
        btnApply.setTextColor(Color.WHITE);
        btnApply.setBackgroundColor(Color.parseColor("#2196F3"));
        btnApply.setAllCaps(false);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnParams.gravity = Gravity.END;
        btnParams.topMargin = 16;
        btnApply.setLayoutParams(btnParams);

        // Simple click feedback
        btnApply.setOnClickListener(v ->
                Toast.makeText(JobListActivity.this, "Applied to " + title, Toast.LENGTH_SHORT).show()
        );

        jobCard.addView(btnApply);
        jobListContainer.addView(jobCard);
    }

    // -----------------
    // LOAD JOBS FROM SharedPreferences
    // -----------------
    private void loadJobsFromPreferences() {
        SharedPreferences prefs = getSharedPreferences("jobs_pref", Context.MODE_PRIVATE);
        String jobsData = prefs.getString("jobs_list", "");

        if (!jobsData.isEmpty()) {
            String[] jobsArray = jobsData.split("\\|\\|");

            for (String job : jobsArray) {
                String[] details = job.split(",");
                if (details.length >= 7) {
                    String title = details[0];
                    String company = details[1];
                    String location = details[2];
                    String type = details[3];
                    String salary = details[4];
                    String mode = details[5];
                    String level = details[6];

                    addJobCard(title, company, location, type, salary, mode, level);
                }
            }
        }
    }
    BottomNavigationView bottomNavigationView;

}
