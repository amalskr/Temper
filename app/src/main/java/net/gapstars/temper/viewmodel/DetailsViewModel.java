/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:41 AM
 */

package net.gapstars.temper.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.gapstars.temper.R;
import net.gapstars.temper.model.Job;
import net.gapstars.temper.model.Shifts;

public class DetailsViewModel extends ViewModel {

    private Job job;

    public DetailsViewModel(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public String getJobTitle() {
        return job.getTitle();
    }

    public Shifts getShift() {
        return job.getShiftsList().get(0);
    }

    @SuppressLint("CheckResult")
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.cover);
        requestOptions.error(R.drawable.cover);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl).into(view);
    }

    public String getImage() {
        return job.getJobClient().getPhotosList().get(0);
    }

    public String getPositions() {

        int posCount = job.getOpenPositions();
        return posCount + " " + (posCount > 1 ? "open positions " : "open position");
    }

    @SuppressLint("DefaultLocale")
    public String getAvarage() {
        float avarage = job.getJobClient().getRating().getAverage();
        return String.format("%.2f", avarage);
    }

    public void readMore(View view) {
        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(job.getUrl())));
    }

    public void fabdoLike(final View view) {
        Snackbar.make(view, "Liked", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Thank You...!", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }).show();
    }
}
