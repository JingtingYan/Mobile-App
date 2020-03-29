package com.example.mobileApp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The AboutActivity class initialises and adds functions for views defined in activity_about.xml.
 *
 * Functions:
 *  1. It displays the About page.
 *  2. It extends ToolbarMenuActivity class to load customised toolbar.
 *  3. It has the DataSyncActivity as parent activity (i.e. go back to DataSyncActivity when clicking back).
 *
 *  @author Jingting Yan
 *  @version 1.0
 *  @since March 2020
 */
public class AboutActivity extends ToolbarMenuActivity {

    /* view */
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        AboutActivity.this.setTitle(R.string.title_activity_about);

        setSupportActionBar(toolbar);

        // enable the 'back arrow' in toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
