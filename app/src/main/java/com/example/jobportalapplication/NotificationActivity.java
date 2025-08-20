package com.example.jobportalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobportalapplication.databinding.ActivityNotificationBinding;
import com.example.jobportalapplication.databinding.NotificationItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;
    private NotificationAdapter adapter;
    private final List<Notification> notificationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new NotificationAdapter(notificationList);
        binding.rvNotifications.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNotifications.setAdapter(adapter);

        // Back button click
        binding.backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this,UserHomePageActivity.class);
            startActivity(intent);
            finish();
        });


        // Highlight the current selected item

      binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                // Already in home, do nothing
                return true;
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

        // Add sample notifications
        addNotification("Welcome to Job Portal!");
        addNotification("New job posted: UI/UX Designer");
    }

    private void addNotification(String title) {
        String time = new SimpleDateFormat("hh:mm a").format(new Date());
        notificationList.add(0, new Notification(title)); // Add to top
        adapter.notifyItemInserted(0);
    }

    private static class Notification {
        String title;
        Notification(String title) {
            this.title = title;
         }
    }

    private static class NotificationAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

        private final List<Notification> notificationList;

        NotificationAdapter(List<Notification> notificationList) {
            this.notificationList = notificationList;
        }

        @NonNull
        @Override
        public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            NotificationItemBinding itemBinding = NotificationItemBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new NotificationViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
            Notification notification = notificationList.get(position);
            holder.binding.tvNotificationTitle.setText(notification.title);
             holder.binding.ivNotificationIcon.setImageResource(R.drawable.ic_notification);
        }

        @Override
        public int getItemCount() {
            return notificationList.size();
        }

        static class NotificationViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            final NotificationItemBinding binding;

            NotificationViewHolder(NotificationItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}
