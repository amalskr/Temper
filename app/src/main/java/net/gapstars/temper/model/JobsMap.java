/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 10:00 AM
 */

package net.gapstars.temper.model;

import java.util.List;

public class JobsMap {

    private String dateKey;
    private List<Job> jobList;

    public JobsMap(String dateKey, List<Job> jobList) {
        this.dateKey = dateKey;
        this.jobList = jobList;
    }

    public String getDateKey() {
        return dateKey;
    }

    public List<Job> getJobList() {
        return jobList;
    }

}
