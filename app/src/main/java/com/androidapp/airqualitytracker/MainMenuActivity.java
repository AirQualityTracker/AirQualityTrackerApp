package com.androidapp.airqualitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class MainMenuActivity extends AppCompatActivity {
    View topNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);


        //Bottom navigation bar actions
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    //Bottom nav bar button listeners
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
                        case R.id.nav_menu:
                            showPopup();
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

/*   @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.addSubMenu(R.menu.bottom_navigation_bar_sub_menu);
        return true;
    }*/


    public void showPopup() {
        View view = findViewById(R.id.nav_menu);
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_bar_sub_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_sub_menu_rank:
                        selectedFragment = new RankFragment();

                    case R.id.nav_sub_menu_help:
                        selectedFragment = new HelpFragment();
                        break;

                    case R.id.nav_sub_menu_notifications:
                        selectedFragment = new NotificationsFragment();
                        break;
                    case R.id.nav_sub_menu_settings:
                        selectedFragment = new SettingsFragment();
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
                return true;
            }
        });
        popup.show();
    }

}
