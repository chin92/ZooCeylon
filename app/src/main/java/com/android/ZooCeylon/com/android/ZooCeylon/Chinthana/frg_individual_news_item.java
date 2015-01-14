package com.android.ZooCeylon.com.android.ZooCeylon.Chinthana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ZooCeylon.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Chinthana on 19/12/2014.
 */
public class frg_individual_news_item extends Activity
{

    private String newsHeading;
    private String newsContent;
    private String imgURL;
    private String releaseDate;

    TextView heading;
    TextView content;
    ImageView image;
    TextView tv_releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_news_item_activity);

        heading= (TextView) findViewById(R.id.newsHeading);
        content= (TextView) findViewById(R.id.newsContent);
        image= (ImageView) findViewById(R.id.newsImage);
        tv_releaseDate= (TextView) findViewById(R.id.releaseDate);

        ImageView avatar = (ImageView)findViewById(R.id.newsImage);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();



        heading.setText(bundle.getString("NEWSHEADING"));
        content.setText(bundle.getString("NEWSCONTENT"));
        tv_releaseDate.setText(bundle.getString("POSTEDDATE"));




        Picasso.with(this)
                .load(bundle.getString("IMAGEURI"))
                .resize(100, 100)
                .centerCrop()
                .into(avatar);


        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setTv_releaseDate(String releaseDate)
    {
        this.releaseDate=releaseDate;
    }

    public void setHeading(String heading)
    {
        this.newsHeading=heading;
    }

    public void setNewsContent(String content) {
        this.newsContent=content;
    }

    public void setNewsImageUrl(String imageURL)
    {
        this.imgURL=imageURL;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate=releaseDate;
    }








}
