package com.android.ZooCeylon.com.android.ZooCeylon.Chinthana;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.ZooCeylon.Contact;
import com.android.ZooCeylon.CustomDrawerAdapter;
import com.android.ZooCeylon.DrawerItem;
import com.android.ZooCeylon.MainActivity;
import com.android.ZooCeylon.R;
import com.android.ZooCeylon.Settings;
import com.android.ZooCeylon.com.android.ZooCeylon.Darsha.Games;
import com.android.ZooCeylon.com.android.ZooCeylon.Gayathri.Events;
import com.android.ZooCeylon.com.android.ZooCeylon.Mario.Friends;
import com.android.ZooCeylon.com.android.ZooCeylon.Tharini.Animals;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chinthana on 06/12/2014.
 */
public class PlanVisit extends Activity implements OnMapReadyCallback{


    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    LatLng currentLocation;
    LocationManager locMgr;
    LocationListener locationListener;
    private final LatLng zoo = new LatLng(6.857066, 79.873795);
    ProgressBar progressBar;
    private DrawerLayout mDrawerLayout; // defining the drawerLayout
    private ListView mDrawerList;		// defining the listView to add the items in the drawer
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;	// menu drawer title
    private CharSequence mTitle;		// menu title
    CustomDrawerAdapter adapter;		// customDrawerAdapter to link the list items to the navigation bar.

    List<DrawerItem> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_planvisit);
         getActionBar().setTitle("Plan your visit");
        getActionBar().setIcon(R.drawable.ic_planvisit);

        Button bt_myLoc= (Button) findViewById(R.id.btn_myLocation);
        bt_myLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    map.addMarker(new MarkerOptions()
                            .title("Your Location")
                            .snippet("You are currently here ! ")
                            .position(currentLocation)).showInfoWindow();

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocation, 16);
                    map.animateCamera(cameraUpdate);
                }
                catch (Exception e)
                {
                    showLocationError();
                }

            }
        });

        Button bt_path= (Button) findViewById(R.id.btn_getDirections);
        bt_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    String url = getDirectionsUrl(currentLocation, zoo);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }
                catch (Exception exp)
                {
                    showLocationError();
                }

            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        map=mapFragment.getMap();

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


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
        dataList.add(new DrawerItem("News", R.drawable.ic_news_white));
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
                getActionBar().setTitle("Plan your visit");
                getActionBar().setIcon(R.drawable.ic_planvisit);
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

        locMgr = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

    }

    private class MyLocationListener implements LocationListener
    {
        public void onLocationChanged(Location loc) {
            if (loc != null) {

                currentLocation=new LatLng(loc.getLatitude(),loc.getLongitude());
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    }


        private void showLocationError()
        {
            Toast.makeText(this,"Please wait until your location is captured",Toast.LENGTH_LONG).show();
        }
        private void showInfo()
        {
            Toast.makeText(this,"Showing the route from your current location",Toast.LENGTH_SHORT);
        }





    @Override
    public void onResume() {
        super.onResume();
//---request for location updates---
        locMgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0,
                locationListener);
    }
    @Override
    public void onPause() {
        super.onPause();
//---remove the location listener---
        locMgr.removeUpdates(locationListener);

    }

    public void SelectItem(int position)
    {
        Bundle args = new Bundle();

        if(position!=1)

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
            Toast.makeText(this, "You are currently on planning your visit", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title)
    {
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

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {

            progressBar.setVisibility(View.INVISIBLE);
            showInfo();

            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

               // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.GREEN);
            }

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }

    @Override
    public void onMapReady(GoogleMap map)
    {

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }

        else {

            map.getUiSettings().setZoomControlsEnabled(true);
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(zoo, 16));


            map.addMarker(new MarkerOptions()
                    .title("Dehiwala Zoo")
                    .snippet("The national zoo of Sri Lanka")
                    .position(zoo)).showInfoWindow();



            /*// You can customize the marker image using images bundled with
            // your app, or dynamically generated bitmaps.
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_contact))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(41.889, -87.622)));*/



        }



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

