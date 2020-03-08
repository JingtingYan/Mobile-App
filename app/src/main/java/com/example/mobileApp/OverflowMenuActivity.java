package com.example.mobileApp;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The OverflowMenuActivity class extends AppCompatActivity class and is extended by MainActivity and DataSyncActivity.
 * It contains common functions required to implement the app's toolbar (not including navigation drawer).
 *
 * @author Jingting Yan
 * @version 1.0
 * @since March 2020
 */
public class OverflowMenuActivity extends AppCompatActivity {

    /**
     * This method is overridden to customise the contents of the app's toolbar (options menu).
     * The toolbar menu resources are defined in /res/menu/toolbar_overflow_menu.xml.
     * @param menu The option menu that contains customised menu items.
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
     * This methods is called whenever an item in the options menu is selected.
     * It overrides the default implementation which simply returns false and makes the normal processing happen.
     * The normal processing calls the item's Runnable or sends a message to its appropriate Handler.
     * @param item The menu item that was selected.
     * @return boolean This returns true if no longer need to check whether other items are selected or not (i.e. has the similar function as 'break');
     *                      returns false to allow normal menu processing to proceed (i.e. need to check other items' function calls).
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_about):
                Toast.makeText(this, "clicked About", Toast.LENGTH_SHORT).show();   // debug
                return true;

            case (R.id.action_logout):
                // check if has already login first
                Toast.makeText(this, "clicked Logout", Toast.LENGTH_SHORT).show();  // debug
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
