/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 10:48 AM
 */

package net.gapstars.temper.utility;

public class Constants {

    static {
        System.loadLibrary("native-lib");
    }


    public static native String baseUrl();

    public static String BASE_URL = baseUrl();
    public static final String KEY_JOBS = "selected_job";

}
