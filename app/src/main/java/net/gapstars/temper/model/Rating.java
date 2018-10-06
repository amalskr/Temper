/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:50 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("average")
    private float average;

    @SerializedName("count")
    private int count;

    public float getAverage() {
        return average;
    }

    public int getCount() {
        return count;
    }
}
