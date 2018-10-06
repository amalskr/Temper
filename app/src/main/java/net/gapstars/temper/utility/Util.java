/*
 * Created by Amal Shiwantha on 10/6/18 11:51 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/5/18 1:32 PM
 */

package net.gapstars.temper.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import net.gapstars.temper.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static String getJson(Activity activity) {
        InputStream inputStream = activity.getResources().openRawResource(R.raw.jobsearch);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return outputStream.toString();
    }

    public static void goNext(Context context, Activity nextActivity, boolean isFinish,
                              Bundle bundle) {
        Intent intent = new Intent(context, nextActivity.getClass());

        //if bundle is not null need to add bundle to the intent
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        context.startActivity(intent);

        //if isFinish = true need to close the activity
        if (isFinish) {
            ((Activity) context).finish();
        }

    }
}
