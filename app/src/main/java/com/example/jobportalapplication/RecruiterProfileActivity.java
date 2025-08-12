package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RecruiterProfileActivity extends AppCompatActivity {

    LinearLayout logoutRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recruiter_profile);

        // 🔽 Bottom Navigation Handling
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    startActivity(new Intent(RecruiterProfileActivity.this, RecruiterHomePageActivity.class));
                    return true;
                } else if (id == R.id.nav_add) {
                    startActivity(new Intent(RecruiterProfileActivity.this, PostJobActivity.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    // Already on profile screen
                    return true;
                }

                return false;
            }
        });

        // 🔽 Log Out Row Click Handling
        logoutRow = findViewById(R.id.logoutRow); // make sure this ID exists in your XML

        logoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear session here if needed

                // Redirect to LoginActivity
                Intent intent = new Intent(RecruiterProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
