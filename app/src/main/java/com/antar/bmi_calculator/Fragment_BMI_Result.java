package com.antar.bmi_calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.antar.bmi_calculator.databinding.FragmentBmiResultBinding;
import com.franmontiel.fullscreendialog.FullScreenDialogContent;
import com.franmontiel.fullscreendialog.FullScreenDialogController;

public class Fragment_BMI_Result extends Fragment implements FullScreenDialogContent {

    private final String TAG = "D_Fragment_BMI_Result";
    private FragmentBmiResultBinding binding;
    private BMI_Model model;

    private double BMI=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentBmiResultBinding.inflate(inflater,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        model =(BMI_Model) getArguments().getSerializable(Fragment_BMI_Calculator.KEY_MODEL);

        calculate(model.getWeight(),model.getHeight(),model.getHeightUnits());
        setUI();

        return binding.getRoot();
    }

    @Override
    public void onDialogCreated(FullScreenDialogController dialogController) {

    }

    @Override
    public boolean onConfirmClick(FullScreenDialogController dialogController) {
        return false;
    }

    @Override
    public boolean onDiscardClick(FullScreenDialogController dialogController) {
        return false;
    }

    void calculate(int weight,int height,BMI_Model.HeightUnits heightUnits)
    {

        double heightInMeter = (BMI_Model.HeightUnits.cm ==heightUnits)?(double)height/100:height;

        double temp = weight / Math.pow(heightInMeter,2);
        BMI = Math.round((temp * 100) / 100);
//        Log.i(TAG,""+BMI);
//        Log.i(TAG,"weight = "+weight + " heightInMeter = "+heightInMeter+ " temp = "+temp);
    }

    void setUI()
    {
        binding.textViewBmi.setText(String.valueOf(BMI));
        binding.textViewWeightKg.setText(String.valueOf(model.getWeight())+" kg's");
        binding.textViewHeightCm.setText(String.valueOf(model.getHeight())+" cm's");
        if (BMI <= 18) {
            int color = ContextCompat.getColor(getActivity(),R.color.underWeight);
            binding.arcSeekBarBmiScale.setProgress((int)BMI);

            binding.imageViewWeightIcon.setColorFilter(color);
            binding.textViewBmi.setTextColor(color);
            binding.weightCategory.setTextColor(color);
            binding.weightCategory.setText(R.string.underWeightText);
//            binding.imageViewWeightLogo.setColorFilter(color);
//            binding.imageViewHeightLogo.setColorFilter(color);

        } else if (BMI >=18.1 && BMI <= 24.9) {
            int color = ContextCompat.getColor(getActivity(),R.color.normalWeight);
            binding.arcSeekBarBmiScale.setProgress((int)BMI);
            binding.imageViewWeightIcon.setColorFilter(color);
            binding.textViewBmi.setTextColor(color);
            binding.weightCategory.setTextColor(color);
            binding.weightCategory.setText(R.string.normalWeightText);
//            binding.imageViewWeightLogo.setColorFilter(color);
//            binding.imageViewHeightLogo.setColorFilter(color);

        } else if (BMI >=25 && BMI <= 29.9) {
            int color = ContextCompat.getColor(getActivity(),R.color.overWeight);
            binding.arcSeekBarBmiScale.setProgress((int)BMI);
            binding.imageViewWeightIcon.setColorFilter(color);
            binding.textViewBmi.setTextColor(color);
            binding.weightCategory.setTextColor(color);
            binding.weightCategory.setText(R.string.overWeightText);
//            binding.imageViewWeightLogo.setColorFilter(color);
//            binding.imageViewHeightLogo.setColorFilter(color);
        } else if (BMI >=30 && BMI <= 34) {
            int color = ContextCompat.getColor(getActivity(),R.color.obese);
            binding.arcSeekBarBmiScale.setProgress((int)BMI);
            binding.imageViewWeightIcon.setColorFilter(color);
            binding.textViewBmi.setTextColor(color);
            binding.weightCategory.setTextColor(color);
            binding.weightCategory.setText(R.string.obeseWeightText);
//            binding.imageViewWeightLogo.setColorFilter(color);
//            binding.imageViewHeightLogo.setColorFilter(color);
        } else if (BMI >=35 ) {
            int color = ContextCompat.getColor(getActivity(),R.color.extremeObese);
            binding.arcSeekBarBmiScale.setProgress((int)BMI);
            binding.imageViewWeightIcon.setColorFilter(color);
            binding.textViewBmi.setTextColor(color);
            binding.weightCategory.setTextColor(color);
            binding.weightCategory.setText(R.string.excessObeseWeightText);
//            binding.imageViewWeightLogo.setColorFilter(color);
//            binding.imageViewHeightLogo.setColorFilter(color);
        }



    }
}
