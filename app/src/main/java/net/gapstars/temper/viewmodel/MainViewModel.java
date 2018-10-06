/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:25 AM
 */

package net.gapstars.temper.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.gapstars.temper.R;

public class MainViewModel extends ViewModel {


    public MainViewModel() {
    }

    public String getCoverImage() {
        return "https://temper.works/assets/img/frontpages/about-us/for-freelancers.jpg";
    }

    @SuppressLint("CheckResult")
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.cover);
        requestOptions.error(R.drawable.cover);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl).into(imageView);
    }
}
