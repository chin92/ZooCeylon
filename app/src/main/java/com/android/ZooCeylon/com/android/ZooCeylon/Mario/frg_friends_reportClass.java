package com.android.ZooCeylon.com.android.ZooCeylon.Mario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.ZooCeylon.JSONParser;
import com.android.ZooCeylon.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chinthana on 30/12/2014.
 */
public class frg_friends_reportClass extends Fragment {
    Button b;
    Toast t;
    private String url_host;
    private EditText fname;
    private EditText lname;
    private EditText nname;
    private EditText ag;
    private RadioButton rb2;
    private RadioButton rb1;
    public View frg_report_v;
    String gender = "male";
    private String b4_success;
    private int success;
    private static final String TAG_SUCCESS="success";
    android.support.v4.app.Fragment fragment=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frg_report_v=inflater.inflate(R.layout.frg_friends_report,container,false);

        return frg_report_v;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        b= (Button) getActivity().findViewById(R.id.btn_friend_report);
        url_host = "http://mariohosted.orgfree.com/database/friends_insert_kidfinder.php";

        //Assigning the radio buttons and Edit Text Fields
        fname = (EditText) getActivity().findViewById(R.id.txt_fr_report_name);
        lname = (EditText) getActivity().findViewById(R.id.txt_fr_report_surname);
        nname = (EditText) getActivity().findViewById(R.id.txt_fr_report_nickName);
        ag = (EditText) getActivity().findViewById(R.id.txt_fr_report_age);
        rb1 = (RadioButton) getActivity().findViewById(R.id.radioBtn_fr_report_1);
        rb2 = (RadioButton) getActivity().findViewById(R.id.radioBtn_fr_report_2);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String url_host = "http://mariohosted.orgfree.com/database/friends_insert_kidfinder.php";
                    JSONParser jsonParser = new JSONParser();
                    String TAG_SUCCESS = "success";


                    t = Toast.makeText(getActivity(),""+fname.getText().toString(),Toast.LENGTH_LONG);
                    t.show();


                    String firstname= fname.getText().toString();
                    String lastname = lname.getText().toString();
                    String nickname = nname.getText().toString();
                    String age=ag.getText().toString();
                    String gender="male";

                    // getting result from radio Buttons
                    if(rb1.isChecked())
                    {
                        gender="male";
                    }
                    else if(rb2.isChecked())
                    {
                        gender="female";
                    }
            /* Testing the fields
            t = Toast.makeText(getApplicationContext(),""+firstname,Toast.LENGTH_LONG);
            t.show();
            */

                    ///////////////////////////////////////////////////////////
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("FIRSTNAME", firstname));
                    params.add(new BasicNameValuePair("LASTNAME", lastname));
                    params.add(new BasicNameValuePair("NICKNAME",nickname));
                    params.add(new BasicNameValuePair("AGE",age));
                    params.add(new BasicNameValuePair("GENDER",gender));

                    JSONObject json = jsonParser.makeHttpRequest(url_host,"POST", params);

                    // check log cat from response
                    Log.d("Create Response", json.toString());

                    // check for success tag

                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        // successfully created a user
                        Intent i = new Intent(getActivity(), frg_friends_reportClass.class);
                        startActivity(i);

                    } else {
                        // failed to create user
                        Log.d("failed updateDB", json.toString());
                        t = Toast.makeText(getActivity(), "fAILED", Toast.LENGTH_LONG);
                        t.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    t = Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

    }

}