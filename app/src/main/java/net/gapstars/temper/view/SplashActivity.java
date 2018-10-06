package net.gapstars.temper.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import net.gapstars.temper.R;
import net.gapstars.temper.databinding.ActivitySplashBinding;
import net.gapstars.temper.viewmodel.SplashViewModel;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        ActivitySplashBinding splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        SplashViewModel splashViewModel = new SplashViewModel(this);
        splashBinding.setSplashVm(splashViewModel);
    }
}
