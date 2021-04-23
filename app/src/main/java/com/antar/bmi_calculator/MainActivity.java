package com.antar.bmi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.antar.bmi_calculator.databinding.ActivityMainBinding;
import com.franmontiel.fullscreendialog.FullScreenDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;



    static final String TAG = "D_MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        binding =  ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);


        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.item_bmi_calculator);





    }



    public void disableBottomNavigationText()
    {
        binding.bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
    }

    public void enableBottomNavigationText()
    {
        binding.bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i(TAG,"Navigation Clicked");
        switch (item.getItemId())
        {
            case R.id.item_bmi_calculator:
                setBMIFragment();
                break;
//            case R.id.item_setting:
//                setSettingFragment();
//                break;
            case R.id.item_aboutus:
                setAboutusFragment();
                break;
        }



        return true;
    }

    void setBMIFragment()
    {
        Fragment_BMI_Calculator calculator = new Fragment_BMI_Calculator();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContainer, calculator);
        transaction.commit();

    }

    void setSettingFragment(){
        Fragment_Settings settings = new Fragment_Settings();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContainer, settings);
        transaction.commit();
    }
    void setAboutusFragment(){
        Fragment_Aboutus aboutus = new Fragment_Aboutus();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContainer, aboutus);
        transaction.commit();
    }




}