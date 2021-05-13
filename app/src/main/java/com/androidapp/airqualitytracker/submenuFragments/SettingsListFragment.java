package com.androidapp.airqualitytracker.submenuFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.androidapp.airqualitytracker.R;
import com.androidapp.airqualitytracker.submenuFragments.settingsFragments.managePlaces.LocationFragment;

import org.jetbrains.annotations.NotNull;

public class SettingsListFragment extends Fragment {


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
        TableLayout settingsFragmentList = (TableLayout) view.findViewById(R.id.settingsFragmentList);
        for(int i = 0; i < settingsFragmentList.getChildCount(); i++) {
            View view2 = settingsFragmentList.getChildAt(i);
            if (view2 instanceof TableRow) {
                // then, you can remove the the row you want...
                // for instance...
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
                           /* case R.id.:
                                selectedFragment = new MapFragment();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new SearchFragment();
                                break;
                            case R.id.nav_menu:
                                showPopup();
                                break;*/

                                }
                                if(selectedFragment != null) {
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

}