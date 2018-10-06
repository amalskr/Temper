/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:33 PM
 */

package net.gapstars.temper.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gapstars.temper.R;
import net.gapstars.temper.databinding.ItemJobDateBinding;
import net.gapstars.temper.model.JobsMap;
import net.gapstars.temper.viewmodel.ItemJobDateViewModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<JobsMap> jobsMapArrayList;

    MainAdapter(ArrayList<JobsMap> jobsMapList) {
        this.jobsMapArrayList = jobsMapList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemJobDateBinding jobDateBinding = DataBindingUtil.inflate(LayoutInflater.
                from(viewGroup.getContext()), R.layout.item_job_date, viewGroup, false);
        return new ViewHolder(jobDateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {

        JobsMap jobsMap = jobsMapArrayList.get(index);
        holder.bindJobs(jobsMap);

        //setup inner jobs view
        JobsAdapter jobsAdapter = new JobsAdapter(jobsMap.getJobList());
        holder.itemJobDateBinding.innerRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.itemJobDateBinding.innerRecyclerView.setAdapter(jobsAdapter);
    }

    @Override
    public int getItemCount() {
        return jobsMapArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemJobDateBinding itemJobDateBinding;

        ViewHolder(@NonNull ItemJobDateBinding jobDateBinding) {
            super(jobDateBinding.itemJobDate);
            this.itemJobDateBinding = jobDateBinding;
        }

        void bindJobs(JobsMap jobsMap) {
            itemJobDateBinding.setJobDateViewModel(new ItemJobDateViewModel(jobsMap.getDateKey(),
                    jobsMap.getJobList()));
        }
    }
}
