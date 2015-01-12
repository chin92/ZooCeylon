package com.android.ZooCeylon.com.android.ZooCeylon.Chinthana;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.ZooCeylon.R;

/**
 * Created by Chinthana on 12/12/2014.
 */
public class frg_gallery_main_page extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_gallery_mainpage_listview,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView;
        String[] listitem={"Animals","Events","Places","Staff"};
        int[] imageResources={R.drawable.an3,R.drawable.img_gallery_events,R.drawable.img_gallery_places,R.drawable.img_gallery_staff};
        listView= (ListView) getActivity().findViewById(R.id.gallery_listView);

        listAdaptor adaptor=new listAdaptor(this.getActivity(),listitem,imageResources);
        listView.setAdapter(adaptor);

    }
}

class listAdaptor extends ArrayAdapter<String>
{

    Context c;
    int[] images;
    String[] titleArray;
    public listAdaptor(Context context, String titles[],int images[]) {
        super(context, R.layout.frg_gallery_custom_list_item,titles);
        c=context;
        this.images=images;
        this.titleArray=titles;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.frg_gallery_custom_list_item,parent,false);
        ImageView galleryImage= (ImageView) row.findViewById(R.id.galleryImage);
        TextView galleryText= (TextView) row.findViewById(R.id.galleryText);

        galleryImage.setImageResource(images[position]);
        galleryText.setText(titleArray[position]);

        return row;
    }
}