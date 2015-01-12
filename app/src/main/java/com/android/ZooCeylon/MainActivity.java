package com.android.ZooCeylon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.Gallery;
import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.News;
import com.android.ZooCeylon.com.android.ZooCeylon.Chinthana.PlanVisit;
import com.android.ZooCeylon.com.android.ZooCeylon.Darsha.Games;
import com.android.ZooCeylon.com.android.ZooCeylon.Gayathri.Events;
import com.android.ZooCeylon.com.android.ZooCeylon.Mario.Friends;
import com.android.ZooCeylon.com.android.ZooCeylon.Tharini.Animals;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        ViewFlipper vf1 = (ViewFlipper) findViewById(R.id.viewflipper1);
        ViewFlipper vf2 = (ViewFlipper) findViewById(R.id.viewflipper2);

        vf1.startFlipping();
        vf1.setFlipInterval(2000);
        vf1.setInAnimation(this,R.anim.view_transition_in_right);
        vf1.setOutAnimation(this,R.anim.view_transition_out_right);

        vf2.startFlipping();
        vf2.setFlipInterval(3000);
        vf2.setInAnimation(this,R.anim.view_transition_in_left);
        vf2.setOutAnimation(this,R.anim.view_transition_out_left);

        ImageButton bt_zooMap= (ImageButton) findViewById(R.id.imagebutton1);
        ImageButton bt_events = (ImageButton) findViewById(R.id.imagebutton2);
        ImageButton bt_planvisit= (ImageButton) findViewById(R.id.imagebutton3);
        ImageButton bt_animals= (ImageButton) findViewById(R.id.imagebutton4);
        ImageButton bt_newsFeed= (ImageButton) findViewById(R.id.imagebutton5);
        ImageButton bt_friendFinder= (ImageButton) findViewById(R.id.imagebutton6);
        ImageButton bt_games= (ImageButton) findViewById(R.id.imagebutton7);
        ImageButton bt_gallery= (ImageButton) findViewById(R.id.imagebutton8);
        ImageButton bt_settings= (ImageButton) findViewById(R.id.imagebutton9);
        ImageButton bt_share= (ImageButton) findViewById(R.id.imagebutton10);

        bt_zooMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       final Intent event=new Intent(this,Events.class);
        final Intent planvisit=new Intent(this,PlanVisit.class);
        final Intent animals=new Intent(this,Animals.class);
        final Intent news=new Intent(this,News.class);
        final Intent friend=new Intent(this,Friends.class);
        final Intent games=new Intent(this,Games.class);
        final Intent gallery=new Intent(this,Gallery.class);
        final Intent settings=new Intent(this,Settings.class);
        final Intent share=new Intent(this,Share.class);
        //final Intent event=new Intent(this,Events.class); Zoo map



        bt_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(event);

      }

        });

        bt_planvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(planvisit);

            }
        });

        bt_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  startActivity(animals);

            }
        });

        bt_newsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(news);

            }
        });

        bt_friendFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(friend);

            }
        });

        bt_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(games);

            }
        });

        bt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gallery);

            }
        });

        bt_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settings);

            }
        });

        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(share);

            }
        });


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    @Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.

	}

}
