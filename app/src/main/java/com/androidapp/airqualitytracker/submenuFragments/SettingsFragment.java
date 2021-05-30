package com.androidapp.airqualitytracker.submenuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.airqualitytracker.R;


public class SettingsFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_list_fragment, container, false);
    }


   /*public View showAbout(){
      *//*  Fragment aboutFragment = new AboutAQTFragment();
       FragmentManager ftMan = getSupportFragmentManager();
       FragmentTransaction ftTrans = ftMan.beginTransaction();
       ftTrans.replace(R.id.fragment_container,
               aboutFragment);
       ftTrans.commit();*//*

   }*/
}