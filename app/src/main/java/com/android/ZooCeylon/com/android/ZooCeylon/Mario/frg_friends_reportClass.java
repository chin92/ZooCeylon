package com.android.ZooCeylon.com.android.ZooCeylon.Mario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ZooCeylon.R;

/**
 * Created by Chinthana on 30/12/2014.
 */
public class frg_friends_reportClass extends Fragment {

    public View frg_report_v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frg_report_v=inflater.inflate(R.layout.frg_friends_report,container,false);

        return frg_report_v;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}