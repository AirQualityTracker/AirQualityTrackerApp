package com.androidapp.airqualitytracker.submenuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidapp.airqualitytracker.HomeFragment;
import com.androidapp.airqualitytracker.MapFragment;
import com.androidapp.airqualitytracker.R;
import com.androidapp.airqualitytracker.SearchFragment;
import com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces.LocationFragment;

import org.jetbrains.annotations.NotNull;


public class SettingsFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getParentFragmentManager().beginTransaction().replace(R.id.settings_fragment_container,
                new SettingsListFragment()).commit();


    }

    /* public void managePlaces(){
       Fragment aboutFragment = new AboutAQTFragment();
       FragmentManager ftMan = getSupportFragmentManager();
       FragmentTransaction ftTrans = ftMan.beginTransaction();
       ftTrans.replace(R.id.fragment_container,
               aboutFragment);
       ftTrans.commit();
     return new View();

   }*/
}