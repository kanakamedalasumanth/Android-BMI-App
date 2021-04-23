package com.antar.bmi_calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.antar.bmi_calculator.databinding.SplashActivityBinding;

public class SplashActivity extends AppCompatActivity implements Runnable {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    SplashActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        binding = SplashActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String versionName = "N/A";
        try {
            versionName = getPackageManager().getPackageInfo(this.getPackageName(),0).versionName;

//
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.textViewVersionValue.setText(versionName);

        new Handler().postDelayed(this,SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void run() {
        Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(mainIntent);
        this.finish();
    }
}
