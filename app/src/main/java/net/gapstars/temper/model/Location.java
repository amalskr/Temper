/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:30 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
