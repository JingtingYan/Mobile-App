package com.example.mobileApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

/**
 * This NavigationDrawerActivity class extends AppCompatActivity class and is extended by LocationActivity and HouseholdMainActivity.
 * ... other activities as well
 * It contains all necessary functions required to initialise the app's Toolbar along with the navigation drawer.
 * For those activities that extend this class, a toolbar and a navigation drawer are created when that
 * Activity is created.
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 */
public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /* class-scope and package-scope variables */
    private DrawerLayout drawerLayout;
    FrameLayout frameLayout;    // frameLayout is package-scope

    /**
     * This method is called when the NavigationDrawerActivity is first created.
     * It creates the activity according to its layout defined in /res/layout/layout_navigation_drawer.xml.
     * It instantiates some class-scope variables (bind by ButterKnife).
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     *                           This object would be null if the activity has never existed before.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.layout_navigation_drawer);

        // Set up the toolbar to replace the default app bar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = findViewById(R.id.content_frame);
        drawerLayout = findViewById(R.id.layout_nav_drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Register a listener when items in navigation drawer is selected.
        navigationView.setNavigationItemSelectedListener(this);

        // Add the navigation drawer icon (ic_menu) to the toolbar.
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    /**
     * This method adds ItemSelected response to items/icons in the navigation drawer.
     * @param item The item being selected in the navigation drawer.
     * @return boolean: true if a new item is selected and there's action needed;
     *                  false if the item has already been selected or the item selected is not registered.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()){      // if the item is currently selected
            // directly close the drawer and stay on the same page
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        switch (item.getItemId()) {
            case (R.id.nav_dataSync):
                // highlight the selected item as being selected in the drawer
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), "clicked DataSync", Toast.LENGTH_SHORT).show();     // debug
                drawerLayout.closeDrawers();
                // go to the data sync page
                startActivity(new Intent(getApplicationContext(), DataSyncActivity.class));
                return true;
            case (R.id.nav_location):
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), "clicked Location", Toast.LENGTH_SHORT).show();     // debug
                drawerLayout.closeDrawers();
                // go to the location selection page
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                return true;
            case (R.id.nav_hh_home):
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), "clicked Household Home", Toast.LENGTH_SHORT).show();   // debug
                drawerLayout.closeDrawers();
                // go to the household home page
                startActivity(new Intent(getApplicationContext(), HouseholdMainActivity.class));
                return true;
            default:
                return false;
        }
    }

    /**
     * This method is overridden to load the customised toolbar menu contents into toolbar layout.
     * The toolbar menu resources are defined in /res/menu/toolbar_overflow_menu.xml.
     * @param menu The option menu (toolbar menu) that contains customised menu items.
     * @return boolean This returns true for the options menu to be displayed;
     *                              false for not showing the options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_overflow_menu, menu);
        return true;
    }

    /**
     * This methods is called whenever an item in the options menu (toolbar) is selected.
     * It overrides the default implementation which simply returns false and makes the normal processing happen.
     * The normal processing calls the item's Runnable or sends a message to its appropriate Handler.
     * @param item The menu item that was selected.
     * @return boolean This returns true if no longer need to check whether other items are selected or not (i.e. has the similar function as 'break');
     *                      returns false to allow normal menu processing to proceed (i.e. need to check other items' function calls).
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // select the About action in the overflow menu
            case (R.id.action_about):
                Toast.makeText(this, "clicked About", Toast.LENGTH_SHORT).show();
                return true;

            // select the Log out action in the overflow menu
            case (R.id.action_logout):
                // check if has already login first
                Toast.makeText(this, "clicked Logout", Toast.LENGTH_SHORT).show();
                return true;

            // select the Navigation drawer home icon in the toolbar
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

