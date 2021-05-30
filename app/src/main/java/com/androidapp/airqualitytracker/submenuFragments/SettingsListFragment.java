package com.androidapp.airqualitytracker.submenuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidapp.airqualitytracker.R;
import com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces.LocationFragment;

import org.jetbrains.annotations.NotNull;
/**
 *This class represents the list of settings
 */

public class SettingsListFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {


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

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     // set aqi index info text
        TextView aqiIndexInfo = getActivity().findViewById(R.id.aqiIndexInfo);
        aqiIndexInfo.setText("US AQI");

        // set unit system info text
        TextView unitSystemInfo = getActivity().findViewById(R.id.unitSystemInfo);
        unitSystemInfo.setText("Metric");

        //get the settings list contents from the table layout
        TableLayout settingsFragmentList = (TableLayout) view.findViewById(R.id.settingsFragmentList);
        for(int i = 0; i < settingsFragmentList.getChildCount(); i++) {
            View view2 = settingsFragmentList.getChildAt(i);
            if (view2 instanceof TableRow) {
                TableRow row = (TableRow) view2;
                View rowChildPart =  row.getChildAt(0);
                if(rowChildPart instanceof TextView){
                    TextView rowChildPartTextView = (TextView)rowChildPart;
                    if(rowChildPartTextView.isClickable()){
                        rowChildPartTextView.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                System.out.println("hello");
                                Fragment selectedFragment = null;
                                switch (rowChildPartTextView.getId()) {
                                    case R.id.managePlaces:
                                        System.out.println("he;;p");
                                        selectedFragment = new LocationFragment();
                                        break;
                                    case R.id.aqiIndex:
                                        changeAqiIndex(view);
                                        break;
                                    case R.id.unitSystem:
                                        changeUnitSystem(view);
                                        break;
                                    case R.id.systemPermissions:
                                        openSystemPemissions();
                                        break;
                           /* case R.id.nav_menu:
                                showPopup();
                                break;*/

                                }
                                if (selectedFragment != null) {
                                    FragmentManager ftMan = getParentFragmentManager();
                                    FragmentTransaction ftTrans = ftMan.beginTransaction();
                                    ftTrans.replace(R.id.settings_fragment_container,
                                            selectedFragment);
                                    ftTrans.commit();
                                }
                            }
                        });
                    }
                }
            }
        }
    }

   //changes thq aqi index
    public void changeAqiIndex(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(this);

        popup.inflate(R.menu.aqi_index_choice_menu);
       // MenuItem aqiIndexMenuItem = getActivity().findViewById(R.id.us_aqi_index);
        //aqiIndexMenuItem.setChecked(true);
        popup.show();
    }
   //changes the uniti system
    public void changeUnitSystem(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(this);

        popup.inflate(R.menu.unit_system_choice_menu);
        // MenuItem aqiIndexMenuItem = getActivity().findViewById(R.id.us_aqi_index);
        //aqiIndexMenuItem.setChecked(true);
        popup.show();
    }

   // handles the menu items of the choices that are given on the settings
    @Override
    public boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()) {
            case R.id.us_aqi_index:
                TextView aqiIndexInfo = getActivity().findViewById(R.id.aqiIndexInfo);
                aqiIndexInfo.setText("US AQI");
                item.setChecked(true);
                //also configure the aqi index for the app
                return true;
            case R.id.cn_aqi_index:
                TextView aqiIndexInfo2 = getActivity().findViewById(R.id.aqiIndexInfo);
                aqiIndexInfo2.setText("CN AQI");
                item.setChecked(true);
                //also configure the aqi index for the app
                return true;
            case R.id.metric_system:
                TextView metric = getActivity().findViewById(R.id.unitSystemInfo);
                metric.setText("Metric");
                item.setChecked(true);
                //also configure the aqi index for the app
                return true;
            case R.id.imperial_system:
                TextView imperial = getActivity().findViewById(R.id.unitSystemInfo);
                imperial.setText("Imperial");
                item.setChecked(true);
                //also configure the aqi index for the app
                return true;
            default:
                return false;

        }
    }


    public void openSystemPemissions(){
        getActivity().startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS), 0);
    }




}