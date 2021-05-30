package com.androidapp.airqualitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidapp.airqualitytracker.submenuFragments.HelpFragment;
import com.androidapp.airqualitytracker.submenuFragments.NotificationsFragment;
import com.androidapp.airqualitytracker.submenuFragments.RankFragment;
import com.androidapp.airqualitytracker.submenuFragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * This class is used to handle all the events that happen on the main menu activity
 */
public class MainMenuActivity extends AppCompatActivity {
    View topNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        //create a card view model
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        //get the toolbal from the xml
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom navigation bar actions
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_bar_sub_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Bottom navigation bar button listeners
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_map:
                            selectedFragment = new MapFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;

                    }
                    if (selectedFragment != null) {
                        FragmentManager ftMan = getSupportFragmentManager();
                        FragmentTransaction ftTrans = ftMan.beginTransaction();
                        ftTrans.replace(R.id.fragment_container,
                                selectedFragment);
                        ftTrans.commit();
                    }

                    return true;
                }
            };


}
