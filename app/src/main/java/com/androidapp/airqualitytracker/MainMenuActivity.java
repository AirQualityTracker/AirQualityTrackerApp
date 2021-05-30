package com.androidapp.airqualitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import com.androidapp.airqualitytracker.submenuFragments.AboutAQTFragment;
import com.androidapp.airqualitytracker.submenuFragments.HelpFragment;
import com.androidapp.airqualitytracker.submenuFragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {
    View topNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {

            case R.id.nav_sub_menu_settings:
                selectedFragment = new SettingsFragment();
                break;

            case R.id.nav_sub_menu_help:
                selectedFragment = new HelpFragment();
                break;

            case R.id.nav_sub_menu_about:
                selectedFragment = new AboutAQTFragment();
                break;

            default:
                break;
        }
        if (selectedFragment != null) {
            FragmentManager ftMan = getSupportFragmentManager();
            FragmentTransaction ftTrans = ftMan.beginTransaction();
            ftTrans.replace(R.id.fragment_container,
                    selectedFragment);
            ftTrans.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    //Bottom nav bar button listeners
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
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
            };
}
