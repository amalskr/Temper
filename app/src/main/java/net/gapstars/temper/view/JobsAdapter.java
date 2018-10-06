/*
 * Created by Amal Shiwantha on 10/6/18 11:49 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:25 AM
 */

package net.gapstars.temper.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gapstars.temper.R;
import net.gapstars.temper.databinding.ItemJobBinding;
import net.gapstars.temper.model.Job;
import net.gapstars.temper.viewmodel.ItemJobViewModel;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private List<Job> jobList;

    JobsAdapter(List<Job> nameList) {
        this.jobList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemJobBinding jobBinding = DataBindingUtil.inflate(LayoutInflater.
                from(viewGroup.getContext()), R.layout.item_job, viewGroup, false);
        return new ViewHolder(jobBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindJobs(jobList.get(position));
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemJobBinding itemJobBinding;

        ViewHolder(@NonNull ItemJobBinding jobDateBinding) {
            super(jobDateBinding.jobdetails);
            this.itemJobBinding = jobDateBinding;
        }

        void bindJobs(Job job) {
            itemJobBinding.setJobViewModel(new ItemJobViewModel(job));
        }
    }

}
