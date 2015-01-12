package com.android.ZooCeylon.com.android.ZooCeylon.Gayathri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.android.ZooCeylon.R;

/**
 * Created by Gayathri on 12/12/2014.
 */
public class Frag_Events_Calendar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_events_calendar,container,false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalendarView calendarView=(CalendarView)getActivity().findViewById(R.id.calendarView1);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
               /*Toast.makeText(getActivity().getApplicationContext(),
                        dayOfMonth +"/"+(month+1)+"/"+ year,Toast.LENGTH_LONG).show();*/

               Intent intent1 = new Intent(getActivity(), Calendar.class);
                startActivity(intent1);


            }
        });
    }
}



