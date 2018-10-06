/*
 * Created by Amal Shiwantha on 10/6/18 11:50 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 10:23 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("openPositions")
    private int openPositions;

    @SerializedName("url")
    private String url;

    private Location jobLocation;
    private Category jobCategory;
    private Client jobClient;
    private List<Shifts> shiftsList;

    public Job(int id, String title, String webUrl, int openPositions, Location jobLocation, Category jobCategory,
               Client jobClient, List<Shifts> shiftsList) {

        this.id = id;
        this.title = title;
        this.url = webUrl;
        this.openPositions = openPositions;
        this.jobLocation = jobLocation;
        this.jobCategory = jobCategory;
        this.jobClient = jobClient;
        this.shiftsList = shiftsList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getOpenPositions() {
        return openPositions;
    }

    public Location getJobLocation() {
        return jobLocation;
    }

    public Category getJobCategory() {
        return jobCategory;
    }

    public Client getJobClient() {
        return jobClient;
    }

    public List<Shifts> getShiftsList() {
        return shiftsList;
    }
}
