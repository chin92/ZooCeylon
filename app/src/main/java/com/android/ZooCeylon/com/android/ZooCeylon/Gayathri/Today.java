package com.android.ZooCeylon.com.android.ZooCeylon.Gayathri;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.ZooCeylon.R;

/**
 * Created by Chinthana on 30/12/2014.
 */
public class Today extends Activity {

    private CheckBox chkIos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_sub);
        final CheckBox chk=(CheckBox)findViewById(R.id.chk_today);

        chk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (chk.isChecked()) {
                    Toast.makeText(Today.this,
                            "Bro, try Android :)", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
