package com.example.jobportalapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobportalapplication.adapter.PopularJobsAdapter;
import com.example.jobportalapplication.adapter.RecentJobsAdapter;
import com.example.jobportalapplication.databinding.ActivityRecruiterHomePageBinding;
import com.example.jobportalapplication.model.PopularJob;
import com.example.jobportalapplication.model.RecentJob;

import java.util.ArrayList;
import java.util.List;

public class RecruiterHomePageActivity extends AppCompatActivity {

    ActivityRecruiterHomePageBinding binding;
    PopularJobsAdapter popularJobsAdapter;
    RecentJobsAdapter recentJobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecruiterHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 🔹 Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_add) {
                startActivity(new Intent(this, PostJobActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_notification) {
                startActivity(new Intent(this, NotificationActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, RecruiterProfileActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        setActiveNavIcon(R.id.nav_home);

        // 🔹 Filter button
        binding.filterButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SearchFilterActivity.class));
        });

        // 🔹 Demo data for Popular Jobs
        List<PopularJob> popularJobs = new ArrayList<>();
        popularJobs.add(new PopularJob("Google", "Mountain View, CA", "UI/UX Designer", "$120k", "2 days ago", "Design, Full-time"));
        popularJobs.add(new PopularJob("Apple", "Cupertino, CA", "iOS Developer", "$130k", "3 days ago", "Development, Full-time"));
        popularJobs.add(new PopularJob("Microsoft", "Redmond, WA", "Product Manager", "$125k", "1 week ago", "Management, Full-time"));

        popularJobsAdapter = new PopularJobsAdapter(popularJobs);
        binding.popularJobsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.popularJobsRecycler.setAdapter(popularJobsAdapter);

        // 🔹 Demo data for Recent Jobs
        List<RecentJob> recentJobs = new ArrayList<>();
        recentJobs.add(new RecentJob("Android Developer", "Facebook", "Menlo Park, CA", "2 days ago", "Full-time", 115000));
        recentJobs.add(new RecentJob("Frontend Developer", "Netflix", "Los Gatos, CA", "3 days ago", "Full-time", 125000));
        recentJobs.add(new RecentJob("Backend Engineer", "Amazon", "Seattle, WA", "1 week ago", "Full-time", 120000));

        recentJobsAdapter = new RecentJobsAdapter(recentJobs);
        binding.recentJobsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recentJobsRecycler.setAdapter(recentJobsAdapter);
    }

    // Icon coloring logic
    private void setActiveNavIcon(int activeItemId) {
        int activeColor = getResources().getColor(R.color.activeBlue);
        int inactiveColor = getResources().getColor(R.color.inactiveDark);

        for (int i = 0; i < binding.bottomNavigationView.getMenu().size(); i++) {
            int itemId = binding.bottomNavigationView.getMenu().getItem(i).getItemId();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.bottomNavigationView.getMenu().findItem(itemId).setIconTintList(
                        ColorStateList.valueOf(itemId == activeItemId ? activeColor : inactiveColor)
                );
            }
        }
    }
}
