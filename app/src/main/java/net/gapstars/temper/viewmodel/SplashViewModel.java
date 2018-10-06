/*
 * Created by Amal Shiwantha on 10/6/18 11:52 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/6/18 12:11 AM
 */

package net.gapstars.temper.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import net.gapstars.temper.R;
import net.gapstars.temper.TemperApplication;
import net.gapstars.temper.model.Category;
import net.gapstars.temper.model.Client;
import net.gapstars.temper.model.Job;
import net.gapstars.temper.model.JobsMap;
import net.gapstars.temper.model.Location;
import net.gapstars.temper.model.Shifts;
import net.gapstars.temper.utility.Util;
import net.gapstars.temper.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private Activity activity;
    public ObservableInt jobProgress;
    public ObservableInt jobStatus;
    public ObservableField<String> messageLabel;

    public SplashViewModel(@NonNull Activity activity) {
        this.activity = activity;
        jobProgress = new ObservableInt(View.GONE);
        jobStatus = new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>(activity.getString(R.string.loading));

        initializeViews();

        new CountDownTimer(500, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                fetchJobs();
            }
        }.start();


    }

    private void initializeViews() {
        jobProgress.set(View.VISIBLE);
        jobStatus.set(View.GONE);
    }

    private void stopProgress() {
        jobProgress.set(View.GONE);
        jobStatus.set(View.VISIBLE);
    }

    private void fetchJobs() {

        final ArrayList<JobsMap> jobsMapArrayList = new ArrayList<>();

        Observable<String> jobsObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) {
                        String jsonStr = Util.getJson(activity);
                        emitter.onNext(jsonStr);
                        emitter.onComplete();
                    }
                });

        jobsObservable.subscribe(new Observer<String>()

        {
            @Override
            public void onSubscribe(Disposable d) {
                //for test
            }

            @Override
            public void onNext(String jsonStr) {

                try {
                    Gson gson = new Gson();
                    String dataSet1 = "2018-05-22";
                    String dataSet2 = "2018-05-23";
                    String dataSet3 = "2018-05-24";

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject jsonData = jsonObject.getJSONObject("data");

                    JSONArray jsonAry1 = jsonData.getJSONArray(dataSet1);
                    JSONArray jsonAry2 = jsonData.getJSONArray(dataSet2);
                    JSONArray jsonAry3 = jsonData.getJSONArray(dataSet3);

                    LinkedHashMap<String, JSONArray> linkedList = new LinkedHashMap<>();
                    linkedList.put(dataSet1, jsonAry1);
                    linkedList.put(dataSet2, jsonAry2);
                    linkedList.put(dataSet3, jsonAry3);

                    for (Map.Entry<String, JSONArray> entry : linkedList.entrySet()) {

                        String key = entry.getKey();
                        JSONArray jsonArray = entry.getValue();

                        List<Job> jobList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobObj = jsonArray.getJSONObject(i);

                            int id = jobObj.getInt("id");
                            String title = jobObj.getString("title");
                            String webUrl = jobObj.getString("url");
                            int openPositions = jobObj.getInt("open_positions");

                            Location jobLocation = gson.fromJson(jobObj.getJSONObject("location").toString(), Location.class);
                            Category jobCategory = gson.fromJson(jobObj.getJSONObject("job_category").toString(), Category.class);
                            Client jobClient = gson.fromJson(jobObj.getJSONObject("client").toString(), Client.class);

                            //get clients photos
                            List<String> photoList = new ArrayList<>();
                            JSONArray photoJsnAry = jobObj.getJSONObject("client").getJSONArray("photos");
                            for (int p = 0; p < photoJsnAry.length(); p++) {

                                String photoUrl = photoJsnAry.getJSONObject(p).
                                        getJSONArray("formats").getJSONObject(0).getString("cdn_url");
                                photoList.add(photoUrl);
                            }

                            jobClient.setPhotosList(photoList);

                            //get job shift data
                            List<Shifts> shiftsList = new ArrayList<>();
                            JSONArray shiftAry = jobObj.getJSONArray("shifts");
                            Shifts jobShift;
                            for (int x = 0; x < shiftAry.length(); x++) {
                                jobShift = gson.fromJson(shiftAry.get(x).toString(), Shifts.class);
                                shiftsList.add(jobShift);
                            }

                            jobList.add(new Job(id, title, webUrl, openPositions, jobLocation, jobCategory,
                                    jobClient, shiftsList));
                        }
                        jobsMapArrayList.add(new JobsMap(key, jobList));
                    }

                } catch (Exception e) {
                    messageLabel.set(e.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                messageLabel.set(e.getMessage());
            }

            @Override
            public void onComplete() {
                stopProgress();

                TemperApplication.JOBS_LIST = jobsMapArrayList;
                Util.goNext(activity, new MainActivity(), true, null);
            }
        });


    }


}
