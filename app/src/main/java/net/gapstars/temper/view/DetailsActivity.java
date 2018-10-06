package net.gapstars.temper.view;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.gapstars.temper.R;
import net.gapstars.temper.databinding.ActivityDetailsBinding;
import net.gapstars.temper.model.Job;
import net.gapstars.temper.utility.Constants;
import net.gapstars.temper.viewmodel.DetailsViewModel;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding detailsBinding;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSelectedData();
        initDataBinding();
        setSupportActionBar(detailsBinding.toolbar);
        displayHomeAsUpEnabled();

        //TRANSITION ANIMATION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(R.id.banner_image).setTransitionName(Constants.KEY_JOBS);
        }
    }


    private void initDataBinding() {
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        DetailsViewModel splashViewModel = new DetailsViewModel(job);
        detailsBinding.setDetailViewModel(splashViewModel);
    }

    private void getSelectedData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        job = (Job) bundle.getSerializable(Constants.KEY_JOBS);
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
