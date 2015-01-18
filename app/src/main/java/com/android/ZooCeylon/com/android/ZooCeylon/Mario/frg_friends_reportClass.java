package com.android.ZooCeylon.com.android.ZooCeylon.Mario;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
    // Progress Dialog
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
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

        b = (Button) getActivity().findViewById(R.id.btn_friend_report);
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


                // getting result from radio Buttons
                if (rb1.isChecked()) {
                    gender = "male";
                }
                if (rb2.isChecked()) {
                    gender = "female";
                }


                new InsertToKidFinder().execute();
            /* Testing the fields
            t = Toast.makeText(getApplicationContext(),""+firstname,Toast.LENGTH_LONG);
            t.show();
            */
            }
        });
    }

    class InsertToKidFinder extends AsyncTask<String, String, Void>
    {
        String firstname= fname.getText().toString();
        String lastname = lname.getText().toString();
        String nickname = nname.getText().toString();
        String age=ag.getText().toString();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Sending Request... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("FIRSTNAME", firstname));
            params.add(new BasicNameValuePair("LASTNAME", lastname));
            params.add(new BasicNameValuePair("NICKNAME", nickname));
            params.add(new BasicNameValuePair("AGE", age));
            params.add(new BasicNameValuePair("GENDER", gender));

            // getting JSON Object
            // Note that create product url accepts GET method
            JSONObject json = jsonParser.makeHttpRequest(url_host, "GET", params);

            // check log cat from response
            Log.d("Insert New Missing person Response...............................................................", json.toString());
            // check for success tag

            try {
                //b4_success= json.get(TAG_SUCCESS).toString();

                success = json.getInt(TAG_SUCCESS);
                Log.d("result of Success...............................................................", String.valueOf(success));
                //t=Toast.makejText(getActivity(),""+success,Toast.LENGTH_LONG);

                if(success==1)
                {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                            t= Toast.makeText(getActivity(), "Successfully Inserted", Toast.LENGTH_LONG);
                            t.show();
                        }
                    });
                }
                else{
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                            t= Toast.makeText(getActivity(), "Failed to Report", Toast.LENGTH_LONG);
                            t.show();
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            //dismiss Dialog once done

            pDialog.dismiss();

        }


    }

}