package com.antar.bmi_calculator;

//import android.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import com.antar.bmi_calculator.databinding.FragmentWeightCalculatorBinding;
import com.franmontiel.fullscreendialog.FullScreenDialogFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.Unit;

public class Fragment_BMI_Calculator extends Fragment implements View.OnClickListener, View.OnTouchListener {
    private FragmentWeightCalculatorBinding binding;
    private static final String TAG = "D_Fragment_BMI_Calculator";
    private final int max_kg =180;
    private final int min_kg =0;
    private final int total = max_kg - min_kg;
    BMI_Model bmi_model = new BMI_Model(30,70,160,BMI_Model.Gender.MALE,BMI_Model.WeightUnits.kg,BMI_Model.HeightUnits.cm);

    public static final String KEY_MODEL = "BMI_Model.data";

    private Handler mHandler;

    OnLongClickRunner onLongClickRunner = new OnLongClickRunner(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentWeightCalculatorBinding.inflate( inflater, container, false);


        binding.cardViewMale.setOnClickListener(this);
        binding.cardViewFemale.setOnClickListener(this);
        binding.buttonCalculate.setOnClickListener(this);


        binding.fluidSliderWeight.setStartText(String.valueOf(min_kg));
        binding.fluidSliderWeight.setEndText(String.valueOf(max_kg));
        binding.imageButtonHeightAdd.setOnTouchListener(this);
        binding.imageButtonHeightRemove.setOnTouchListener(this);
        binding.imageButtonAgeAdd.setOnTouchListener(this);
        binding.imageButtonAgeRemove.setOnTouchListener(this);

        binding.fluidSliderWeight.setPositionListener(pos -> {
            setWeight((int)(min_kg + (total  * pos)));
            return Unit.INSTANCE;
        });

        setWeight(bmi_model.getWeight());
        setHeight(bmi_model.getHeight());
        setAge(bmi_model.getAge());
        setGender(bmi_model.getGender());

        return binding.getRoot() ;

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.card_view_female:
                setGender(BMI_Model.Gender.FEMALE);
                break;
            case R.id.card_view_male:
                setGender(BMI_Model.Gender.MALE);
                break;
            case R.id.button_calculate:
                setBMIResultFragment();
                break;



        }


    }

    void setBMIResultFragment()
    {

        Log.i(TAG,"Came Here");
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_MODEL,bmi_model);
        new FullScreenDialogFragment.Builder(getActivity())
                .setTitle(R.string.bmi_result_heading)
                //.setOnConfirmListener(this)
                //.setOnDiscardListener(this)
                .setContent(Fragment_BMI_Result.class, bundle)
                .build().show(getFragmentManager(),"dialogTag");

                ;
    }

    void setWeight(int weight)
    {
        binding.textViewWeight.setText(String.valueOf(weight));
        bmi_model.setWeight(weight);
        bmi_model.setUnits(BMI_Model.WeightUnits.kg);
        binding.fluidSliderWeight.setBubbleText(String.valueOf(weight));
    }

    void setHeight(int height)
    {
        bmi_model.setHeight(height);
        binding.textViewHeight.setText(String.valueOf(height));
    }

    void setGender(BMI_Model.Gender gender)
    {
        bmi_model.setGender(gender);
        if(gender == BMI_Model.Gender.MALE)
        {
            binding.cardViewMale.setCardBackgroundColor(getActivity().getColor(R.color.genderMaleSelect));
            binding.cardViewFemale.setCardBackgroundColor(getActivity().getColor(R.color.cardDefaultBackground));

            binding.fluidSliderWeight.setColorBar(getActivity().getColor(R.color.genderMaleSelect));

            binding.imageButtonAgeAdd.setColorFilter(getActivity().getColor(R.color.genderMaleSelect));
            binding.imageButtonAgeRemove.setColorFilter(getActivity().getColor(R.color.genderMaleSelect));

            binding.imageButtonHeightAdd.setColorFilter(getActivity().getColor(R.color.genderMaleSelect));
            binding.imageButtonHeightRemove.setColorFilter(getActivity().getColor(R.color.genderMaleSelect));
            binding.buttonCalculate.setBackgroundColor(getActivity().getColor(R.color.genderMaleSelect));
            binding.fluidSliderWeight.setPosition(bmi_model.getWeight());
        }
        else
        {
            binding.cardViewMale.setCardBackgroundColor(getActivity().getColor(R.color.cardDefaultBackground));
            binding.cardViewFemale.setCardBackgroundColor(getActivity().getColor(R.color.genderFemaleSelect));

            binding.fluidSliderWeight.setColorBar(getActivity().getColor(R.color.genderFemaleSelect));
            binding.imageButtonAgeAdd.setColorFilter(getActivity().getColor(R.color.genderFemaleSelect));
            binding.imageButtonAgeRemove.setColorFilter(getActivity().getColor(R.color.genderFemaleSelect));

            binding.imageButtonHeightAdd.setColorFilter(getActivity().getColor(R.color.genderFemaleSelect));
            binding.imageButtonHeightRemove.setColorFilter(getActivity().getColor(R.color.genderFemaleSelect));

            binding.buttonCalculate.setBackgroundColor(getActivity().getColor(R.color.genderFemaleSelect));
            binding.fluidSliderWeight.setPosition(bmi_model.getWeight());
        }

    }

    void setAge(int age)
    {
        bmi_model.setAge(age);
        binding.textViewAge.setText(String.valueOf(age));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {



        if(event.getAction() == MotionEvent.ACTION_UP){
            //Log.i(TAG,"ACTION_UP");
            v.setPressed(false);
            onLongClickRunner.unSetView();
            mHandler.removeCallbacksAndMessages(null);
            mHandler=null;
            return true;
        }
        else if(event.getAction() ==MotionEvent.ACTION_DOWN){
            //Log.i(TAG,"ACTION_DOWN");
            v.setPressed(true);
            onLongClickRunner.setView(v);
            mHandler = new Handler();
            mHandler.post(onLongClickRunner);

            return true;
        }

        return false;
    }

    private static class OnLongClickRunner implements Runnable{
       private Fragment_BMI_Calculator context;
       View v = null;

        OnLongClickRunner(Fragment_BMI_Calculator context)
       {
           this.context = context;
       }

       void setView(View v)
       {
        this.v= v;
       }
        void unSetView()
        {
            this.v= null;
        }

       @Override
       public void run() {

           switch (v.getId()) {
               case R.id.imageButton_age_add:
                   int age = Integer.parseInt(context.binding.textViewAge.getText().toString()) + 1;
                   context.setAge(age);
                   break;
               case R.id.imageButton_age_remove:
                   int age1 = Integer.parseInt(context.binding.textViewAge.getText().toString()) - 1;
                   context.setAge(age1);
                   break;
               case R.id.imageButton_height_add:
                   int height = Integer.parseInt(context.binding.textViewHeight.getText().toString()) + 1;
                   context.setHeight(height);
                   break;
               case R.id.imageButton_height_remove:
                   int height1 = Integer.parseInt(context.binding.textViewHeight.getText().toString()) - 1;
                   context.setHeight(height1);
                   break;
           }
           context.mHandler.postDelayed(OnLongClickRunner.this,200);
       }
   }

}
