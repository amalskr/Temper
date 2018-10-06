/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:20 AM
 */

package net.gapstars.temper;

import android.app.Application;
import android.content.Context;

import net.gapstars.temper.model.JobsMap;

import java.util.ArrayList;

public class TemperApplication extends Application {

    public static ArrayList<JobsMap> JOBS_LIST;

    private static TemperApplication get(Context context) {
        return (TemperApplication) context.getApplicationContext();
    }

    public static TemperApplication create(Context context) {
        return TemperApplication.get(context);
    }

}
