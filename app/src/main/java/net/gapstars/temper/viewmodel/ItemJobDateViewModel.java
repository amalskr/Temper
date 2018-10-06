/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:25 AM
 */

package net.gapstars.temper.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import net.gapstars.temper.model.Job;

import java.util.List;

public class ItemJobDateViewModel extends BaseObservable {


    private String dateTitle;
    private List<Job> jobList;
    private boolean isExpand;

    public ItemJobDateViewModel(String dateKey, List<Job> jobList) {
        this.dateTitle = dateKey;
        this.jobList = jobList;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public String getJobsSize() {
        return String.valueOf(jobList.size());
    }

    public void onItemClick(View view) {
        setExpand(isExpand);
    }

    @Bindable
    public boolean isExpand() {
        return isExpand;
    }

    private void setExpand(boolean expand) {
        isExpand = !expand;
        notifyPropertyChanged(BR.expand);
    }
}
