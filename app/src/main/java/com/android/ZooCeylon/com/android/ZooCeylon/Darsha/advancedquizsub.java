package com.android.ZooCeylon.com.android.ZooCeylon.Darsha;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.ZooCeylon.R;


public class advancedquizsub extends Activity {



    Button mammal;
    Button aves;
    Button reptiles;
    Button pisces;
    Button fun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancedquizsub);
        addListenerOnButton();
    }







    public void addListenerOnButton() {

        final Context context = this;

        mammal = (Button) findViewById(R.id.mammal);

        pisces = (Button) findViewById(R.id.pisces);

        aves = (Button) findViewById(R.id.aves);
        reptiles = (Button) findViewById(R.id.reptile);
        fun = (Button) findViewById(R.id.fun);





        mammal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context,Advanced_Quiz.class);
                startActivity(intent);






            }
        });


        pisces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Advanced_Quiz.class);
                startActivity(intent);





            }
        });

        reptiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Advanced_Quiz.class);
                startActivity(intent);





            }
        });


        fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Advanced_Quiz.class);
                startActivity(intent);





            }
        });




        aves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Advanced_Quiz.class);
                startActivity(intent);





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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
