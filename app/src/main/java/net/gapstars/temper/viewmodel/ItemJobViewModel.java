/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 10:47 AM
 */

package net.gapstars.temper.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.gapstars.temper.R;
import net.gapstars.temper.model.Category;
import net.gapstars.temper.model.Client;
import net.gapstars.temper.model.Job;
import net.gapstars.temper.model.Shifts;
import net.gapstars.temper.utility.Constants;
import net.gapstars.temper.view.DetailsActivity;

public class ItemJobViewModel extends BaseObservable {

    private Job job;

    public ItemJobViewModel(Job selectedJob) {
        this.job = selectedJob;
    }

    @SuppressLint("CheckResult")
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.cover);
        requestOptions.error(R.drawable.cover);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url).into(imageView);
    }

    public String getTitle() {
        return job.getTitle();
    }

    public String getPositions() {
        int posCount = job.getOpenPositions();
        return posCount + " " + (posCount > 1 ? "open positions " : "open position");
    }

    public String getImage() {
        return job.getJobClient().getPhotosList().get(0);
    }

    public Client getClient() {
        return job.getJobClient();
    }

    public Category getCategory() {
        return job.getJobCategory();
    }

    public Shifts getShift() {
        return job.getShiftsList().get(0);
    }

    public void onItemClick(View view) {

        //start next activity with transfering data and animation
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_JOBS, job);

        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        intent.putExtras(bundle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(((Activity) view.getContext()), view, Constants.KEY_JOBS);
            view.getContext().startActivity(intent, options.toBundle());
        } else {
            view.getContext().startActivity(intent);
        }
    }

}
