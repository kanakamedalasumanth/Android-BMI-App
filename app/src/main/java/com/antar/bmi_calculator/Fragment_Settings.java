package com.antar.bmi_calculator;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antar.bmi_calculator.databinding.FragmentSettingsBinding;


public class Fragment_Settings extends Fragment {

    private FragmentSettingsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentSettingsBinding.inflate( inflater, container, false);

        return binding.getRoot() ;

    }
}
