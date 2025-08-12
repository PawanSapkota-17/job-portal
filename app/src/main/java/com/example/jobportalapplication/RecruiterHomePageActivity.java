package com.example.jobportalapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobportalapplication.databinding.ActivityRecruiterHomePageBinding;

public class RecruiterHomePageActivity extends AppCompatActivity {

    ActivityRecruiterHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecruiterHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 👉 Filter Button Logic
        binding.filterButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecruiterHomePageActivity.this, SearchFilterActivity.class);
            startActivity(intent);
        });

        // Popular Jobs RecyclerView
        binding.popularJobsRecycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        // Recent Jobs RecyclerView
        binding.recentJobsRecycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        // Highlight Home Icon
        setActiveNavIcon(R.id.nav_home);

        // Bottom Navigation click events
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedId = item.getItemId();
            setActiveNavIcon(selectedId);

            if (selectedId == R.id.nav_home) {
                return true;
            } else if (selectedId == R.id.nav_add) {
                startActivity(new Intent(RecruiterHomePageActivity.this, PostJobActivity.class));
                return true;
            } else if (selectedId == R.id.nav_notification) {
//                startActivity(new Intent(RecruiterHomePageActivity.this, PostJobActivity.class));
                return true;
            } else if (selectedId == R.id.nav_profile) {
                startActivity(new Intent(RecruiterHomePageActivity.this, RecruiterProfileActivity.class));
                return true;
            }
            return false;
        });
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
