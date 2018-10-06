package net.gapstars.temper.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.gapstars.temper.R;
import net.gapstars.temper.TemperApplication;
import net.gapstars.temper.databinding.ActivityMainBinding;
import net.gapstars.temper.model.JobsMap;
import net.gapstars.temper.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<JobsMap> jobsMapArrayList;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        getDataList();
        setSupportActionBar(mainBinding.toolbarMain);
    }

    private void initDataBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel();
        mainBinding.setMainViewModel(mainViewModel);
    }

    private void getDataList() {
        jobsMapArrayList = TemperApplication.JOBS_LIST;
        setupListJobsView(mainBinding.jobsRecyclerView);
    }

    private void setupListJobsView(RecyclerView jobsRecyclerView) {
        MainAdapter adapter = new MainAdapter(jobsMapArrayList);

        /*HeaderItemDecoration sectionItemDecoration =
                new HeaderItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(jobsMapArrayList));

        jobsRecyclerView.addItemDecoration(sectionItemDecoration);*/
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        jobsRecyclerView.setAdapter(adapter);

    }


    // Item header
    /*private HeaderItemDecoration.SectionCallback getSectionCallback(final ArrayList<JobsMap> arrayList) {
        return new HeaderItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || arrayList.get(position).getDateKey()
                        != arrayList.get(position - 1).getDateKey();
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return arrayList.get(position).getDateKey();
            }
        };
    }*/
}
