/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:30 PM
 */

package net.gapstars.temper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shifts implements Serializable {

    @SerializedName("tempers_needed")
    private String tempers_needed;

    @SerializedName("earnings_per_hour")
    private double earnings_per_hour;

    @SerializedName("duration")
    private int duration;

    @SerializedName("currency")
    private String currency;

    @SerializedName("start_date")
    private String start_date;

    @SerializedName("start_time")
    private String start_time;

    @SerializedName("start_datetime")
    private String start_datetime;

    @SerializedName("end_time")
    private String end_time;

    @SerializedName("end_datetime")
    private String end_datetime;

    @SerializedName("is_auto_accepted_when_applied_for")
    private int is_auto_accepted_when_applied_for;

    public String getTempers_needed() {
        return tempers_needed;
    }

    public double getEarnings_per_hour() {
        return earnings_per_hour;
    }

    public int getDuration() {
        return duration;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public int getIs_auto_accepted_when_applied_for() {
        return is_auto_accepted_when_applied_for;
    }
}
