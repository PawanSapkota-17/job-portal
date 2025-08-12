package com.example.jobportalapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobportalapplication.R;
import com.example.jobportalapplication.model.PopularJob;

import java.util.List;

public class PopularJobsAdapter extends RecyclerView.Adapter<PopularJobsAdapter.PopularJobViewHolder> {
    List<PopularJob> jobList;

    public PopularJobsAdapter(List<PopularJob> jobList) {
        this.jobList = jobList;
    }

    @Override
    public PopularJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card, parent, false);
        return new PopularJobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularJobViewHolder holder, int position) {
        PopularJob job = jobList.get(position);
        holder.jobTitle.setText(job.getJobTitle());
        holder.companyName.setText(job.getCompanyName());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    static class PopularJobViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, companyName;

        public PopularJobViewHolder(View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.jobTitle);         // ✅ Correct ID
            companyName = itemView.findViewById(R.id.companyName);   // ✅ Correct ID
        }
    }
}
