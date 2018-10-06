/*
 * Created by Amal Shiwantha on 10/6/18 11:50 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:49 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private Rating rating;

    private List<String> photosList;

    @SerializedName("description")
    private String description;

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public List<String> getPhotosList() {
        return photosList;
    }

    public String getDescription() {
        return description;
    }

    public void setPhotosList(List<String> photosList) {
        this.photosList = photosList;
    }
}
