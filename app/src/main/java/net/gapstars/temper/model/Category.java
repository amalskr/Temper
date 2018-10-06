/*
 * Created by Amal Shiwantha on 10/6/18 11:50 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:30 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("description")
    private String description;

    @SerializedName("icon_path")
    private String icon_path;

    @SerializedName("slug")
    private String slug;

    public String getDescription() {
        return description;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public String getSlug() {
        return slug;
    }
}
