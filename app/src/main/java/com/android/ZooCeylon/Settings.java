package com.android.ZooCeylon;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.Gallery;
import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.News;
import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.PlanVisit;
import com.android.ZooCeylon.com.android.ZooCeylon.Darsha.Games;
import com.android.ZooCeylon.com.android.ZooCeylon.Gayathri.Events;
import com.android.ZooCeylon.com.android.ZooCeylon.Mario.Friends;
import com.android.ZooCeylon.com.android.ZooCeylon.Tharini.Animals;

import java.util.ArrayList;
import java.util.List;

public class Settings extends MainActivity {private DrawerLayout mDrawerLayout; // defining the drawerLayout
    private ListView mDrawerList;		// defining the listView to add the items in the drawer
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;	// menu drawer title
    private CharSequence mTitle;		// menu title
    CustomDrawerAdapter adapter;		// adapter to link the list items to the navigation bar.

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);


        // Initializing
        getActionBar().setTitle("Settings");
        getActionBar().setIcon(R.drawable.ic_settings);

        dataList = new ArrayList<DrawerItem>();		// ArrayList is a subClass of List
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem("Menu", R.drawable.ic_menu));
        dataList.add(new DrawerItem("Plan Visit", R.drawable.ic_planvisit));
        dataList.add(new DrawerItem("Animals", R.drawable.ic_animals));
        dataList.add(new DrawerItem("Events", R.drawable.ic_events));
        dataList.add(new DrawerItem("Gallery", R.drawable.ic_gallery));
        dataList.add(new DrawerItem("News", R.drawable.ic_news));
        dataList.add(new DrawerItem("Friends", R.drawable.ic_friends));
        dataList.add(new DrawerItem("Games", R.drawable.ic_games));
        dataList.add(new DrawerItem("Contact", R.drawable.ic_contact));
        dataList.add(new DrawerItem("Settings", R.drawable.ic_settings));


        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,dataList);

        mDrawerList.setAdapter(adapter);
        ;



        mDrawerList.setOnItemClickListener(new DrawerItemClickListener()); //Register a callback to be invoked when an item in this AdapterView has been clicked.



        getActionBar().setDisplayHomeAsUpEnabled(true); //Retrieve a reference to this activity's ActionBar.(going a level up in
        // the activity rather than going to the frontpage of the action

        getActionBar().setHomeButtonEnabled(true);		// Enabling the home screen button on the action bar (clickable)

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle("Settings");
                getActionBar().setIcon(R.drawable.ic_settings);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("Menu");
                getActionBar().setIcon(R.drawable.ic_launcher);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            //SelectItem(0);
        }

    }

    public void SelectItem(int position)
    {
        Bundle args = new Bundle();

        if(position!=9)

        {
            switch (position) {
                case 0:

                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                    finish();

                    break;
                case 1:
                    Intent intent11 = new Intent(this, PlanVisit.class);
                    startActivity(intent11);
                    finish();

                    break;
                case 2:

                    Intent intent2 = new Intent(this, Animals.class);
                    startActivity(intent2);
                    finish();


                    break;
                case 3:

                    Intent intent3 = new Intent(this, Events.class);
                    startActivity(intent3);
                    finish();


                    break;
                case 4:

                    Intent intent4 = new Intent(this, Gallery.class);
                    startActivity(intent4);
                    finish();

                    break;
                case 5:

                    Intent intent5 = new Intent(this, News.class);
                    startActivity(intent5);
                    finish();

                    break;
                case 6:

                    Intent intent6 = new Intent(this, Friends.class);
                    startActivity(intent6);
                    finish();

                    break;
                case 7:

                    Intent intent7 = new Intent(this, Games.class);
                    startActivity(intent7);
                    finish();

                    break;
                case 8:

                    Intent intent8 = new Intent(this, Contact.class);
                    startActivity(intent8);
                    finish();

                    break;
                case 9:

                    Intent intent9 = new Intent(this,Settings.class);
                    startActivity(intent9);
                    finish();
                    break;

                default:
                    break;
            }

            mDrawerList.setItemChecked(position, true);
            setTitle(dataList.get(position).getItemName());
            mDrawerLayout.closeDrawer(mDrawerList);
        }

        else
            Toast.makeText(this, "You are currently on settings page", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle); // Setting the title of the action bar as the menu title
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }
}
