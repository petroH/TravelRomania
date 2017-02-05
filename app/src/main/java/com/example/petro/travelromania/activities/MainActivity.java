package com.example.petro.travelromania.activities;

import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.events.DrawerSelectionItemEvent;
import com.example.petro.travelromania.fragments.GalleryFragment;
import com.example.petro.travelromania.fragments.MapFragment;
import com.example.petro.travelromania.fragments.RegiuniFragment;
import com.example.petro.travelromania.regiuni.Regions;
import com.example.petro.travelromania.utils.EventBus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawLayout;
    private ActionBarDrawerToggle mActBarDrawToggle;
    private String mCurrentFragmentTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawLayout= (DrawerLayout)findViewById(R.id.drawer_layout);
        mActBarDrawToggle= new ActionBarDrawerToggle(this, mDrawLayout, R.string.draw_opened, R.string.draw_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.draw_opened);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.draw_closed);
                }
            }
        };
        mDrawLayout.addDrawerListener(mActBarDrawToggle);
        displayInitialFragment();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace( R.id.container, MapFragment.getInstance() ).commit();
        mCurrentFragmentTitle = "Maps";
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActBarDrawToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActBarDrawToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(mActBarDrawToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop(){
        EventBus.getInstance().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onDrawSelectItemClickEvent(DrawerSelectionItemEvent event){
        mDrawLayout.closeDrawers();

        if( event == null || TextUtils.isEmpty( event.section ) || event.section.equalsIgnoreCase( mCurrentFragmentTitle ) ) {
            return;
        }

        Toast.makeText( this, "MainActivity: Section Clicked: " + event.section, Toast.LENGTH_SHORT ).show();

        if( event.section.equalsIgnoreCase( "maps" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, MapFragment.getInstance() ).commit();
        } else if( event.section.equalsIgnoreCase( "gallery" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, GalleryFragment.getInstance() ).commit();
        }else if( event.section.equalsIgnoreCase( "regiuni" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, RegiuniFragment.getInstance() ).commit();
        } else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
    }

