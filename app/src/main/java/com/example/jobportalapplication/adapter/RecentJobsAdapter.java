package com.example.jobportalapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobportalapplication.R;
import com.example.jobportalapplication.model.RecentJob;

import java.util.List;

public class RecentJobsAdapter extends RecyclerView.Adapter<RecentJobsAdapter.JobViewHolder> {

    private List<RecentJob> jobList;

    public RecentJobsAdapter(List<RecentJob> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_job_card, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        RecentJob job = jobList.get(position);
        holder.title.setText(job.getJobTitle());
        holder.company.setText(job.getCompanyName());
        holder.location.setText(job.getLocation());
        holder.postedDate.setText(job.getPostedDate());
        holder.jobType.setText(job.getJobType());
        holder.salary.setText(String.format("$%,.0f", job.getSalary()));
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // Add this method to update data and refresh the list on filtering
    public void updateList(List<RecentJob> filteredList) {
        this.jobList = filteredList;
        notifyDataSetChanged();
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView title, company, location, postedDate, jobType, salary;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            company = itemView.findViewById(R.id.jobCompany);
            location = itemView.findViewById(R.id.jobLocation);
            postedDate = itemView.findViewById(R.id.jobPostedDate);
            jobType = itemView.findViewById(R.id.jobType);
            salary = itemView.findViewById(R.id.jobSalary);
        }
    }
}
