package com.antar.bmi_calculator;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.antar.bmi_calculator.databinding.FragmentAboutusBinding;

public class Fragment_Aboutus  extends Fragment implements View.OnClickListener {

    private FragmentAboutusBinding binding;
    private static final String SAI_GITHUB_LINK = "https://github.com/daruvurisai";
    private static final String SAI_TWITTER_LINK = "https://twitter.com/daruvurisaik";
    private static final String SAI_LINKEDIN_LINK = "https://www.linkedin.com/in/daruvurisaikrishna";
    private static final String SAI_FB_LINK = "https://www.facebook.com/daruvurisai";

    private static final String SUMANTH_GITHUB_LINK = "https://github.com/kanakamedalasumanth";
    private static final String SUMANTH_TWITTER_LINK = "https://twitter.com/Sumanth_Kanak";
    private static final String SUMANTH_LINKEDIN_LINK = "https://www.linkedin.com/in/sumanth-kanakamedala-955a571b/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutusBinding.inflate(inflater,container,false);
        binding.imageButtonSaiGithub.setOnClickListener(this);
        binding.imageButtonSaiFacebook.setOnClickListener(this);
        binding.imageButtonSaiLinkedin.setOnClickListener(this);
        binding.imageButtonSaiTwitter.setOnClickListener(this);

        binding.imageButtonSumanthGithub.setOnClickListener(this);
        binding.imageButtonSumanthLinkedin.setOnClickListener(this);
        binding.imageButtonSumanthTwitter.setOnClickListener(this);

        return binding.getRoot();
    }

    private void openURI(String url)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.imageButton_sai_github:
                  openURI(SAI_GITHUB_LINK);
                  break;
              case R.id.imageButton_sai_facebook:
                  openURI(SAI_FB_LINK);
                  break;
              case R.id.imageButton_sai_linkedin:
                  openURI(SAI_LINKEDIN_LINK);
                  break;
              case R.id.imageButton_sai_twitter:
                  openURI(SAI_TWITTER_LINK);
                  break;
              case R.id.imageButton_sumanth_github:
                  openURI(SUMANTH_GITHUB_LINK);
                  break;
              case R.id.imageButton_sumanth_linkedin:
                  openURI(SUMANTH_LINKEDIN_LINK);
                  break;
              case R.id.imageButton_sumanth_twitter:
                  openURI(SUMANTH_TWITTER_LINK);
                  break;
          }
    }
}
