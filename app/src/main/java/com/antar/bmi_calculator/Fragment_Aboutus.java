package com.antar.bmi_calculator;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.antar.bmi_calculator.databinding.FragmentAboutusBinding;

public class Fragment_Aboutus  extends Fragment {

    private FragmentAboutusBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutusBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
