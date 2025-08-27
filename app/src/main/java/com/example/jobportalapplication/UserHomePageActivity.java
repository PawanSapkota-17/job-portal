package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobportalapplication.databinding.ActivityUserHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserHomePageActivity extends AppCompatActivity {

    private ActivityUserHomePageBinding binding;


    private List<Job> allJobs; // All jobs
    private List<Job> filteredJobs; // Jobs after applying filter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityUserHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Highlight the current selected bottom nav item
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        binding.seeMorePopular.setOnClickListener(v->{
            Intent intent=new Intent(UserHomePageActivity.this,JobListActivity.class);
            startActivity(intent);
        });

        binding.seeMoreRecent.setOnClickListener(v->{
            Intent intent=new Intent(UserHomePageActivity.this,JobListActivity.class);
            startActivity(intent);
        });

        // Bottom navigation listener
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) return true;

            if (itemId == R.id.nav_add) startActivity(new Intent(this, JobListActivity.class));
            else if (itemId == R.id.nav_notification) startActivity(new Intent(this, NotificationActivity.class));
            else if (itemId == R.id.nav_profile) startActivity(new Intent(this, UserProfileActivity.class));

            overridePendingTransition(0,0);
            finish();
            return true;
        });

        // Initialize job data
        initJobData();

        // Display jobs initially
        displayJobs(allJobs);

        // Filter icon click -> open SearchFilterActivity
        binding.filterIcon.setOnClickListener(v -> {
            Intent intent = new Intent(UserHomePageActivity.this, SearchFilterActivity.class);
            startActivityForResult(intent, 1001);
        });
    }

    // Handle result from SearchFilterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            String location = data.getStringExtra("filter_location");
            String sort = data.getStringExtra("filter_sort");
            String nature = data.getStringExtra("filter_nature");

            Toast.makeText(this, "Filters Applied:\n" + location + ", " + sort + ", " + nature, Toast.LENGTH_SHORT).show();

            // Filter job list
            filteredJobs = new ArrayList<>();
            for (Job job : allJobs) {
                boolean matchesLocation = job.getLocation().equalsIgnoreCase(location);
                boolean matchesNature = job.getNature().equalsIgnoreCase(nature);

                if (matchesLocation && matchesNature) {
                    filteredJobs.add(job);
                }
            }

            // TODO: Implement sorting based on "sort" value if needed

            displayJobs(filteredJobs);
        }
    }

    // Simulate displaying jobs (for now just Toasts, you can replace with RecyclerView)
    private void displayJobs(List<Job> jobs) {
        // Clear previous jobs
//        binding.jobScrollView.removeAllViews();

        // TODO: Replace with dynamic RecyclerView or HorizontalScrollView
        // For simplicity, showing job count
        Toast.makeText(this, "Displaying " + jobs.size() + " jobs", Toast.LENGTH_SHORT).show();
    }

    // Initialize sample jobs
    private void initJobData() {
        allJobs = new ArrayList<>();
        allJobs.add(new Job("Google Inc", "Bharatpur, Chitwan", "Full Time", "UI/UX Designer"));
        allJobs.add(new Job("Meta", "Kathmandu", "Full Time", "Frontend Developer"));
        allJobs.add(new Job("Apple", "Biratnagar", "Full Time", "iOS Developer"));
        allJobs.add(new Job("Amazon", "Pokhara", "Full Time", "Backend Developer"));
        allJobs.add(new Job("Microsoft", "Lalitpur", "Hybrid", "Software Engineer"));
        allJobs.add(new Job("Tesla", "Butwal", "Remote", "Data Scientist"));
    }

    // Sample Job class
    private static class Job {
        private final String company;
        private final String location;
        private final String nature;
        private final String role;

        public Job(String company, String location, String nature, String role) {
            this.company = company;
            this.location = location;
            this.nature = nature;
            this.role = role;
        }

        public String getCompany() { return company; }
        public String getLocation() { return location; }
        public String getNature() { return nature; }
        public String getRole() { return role; }
    }
}
