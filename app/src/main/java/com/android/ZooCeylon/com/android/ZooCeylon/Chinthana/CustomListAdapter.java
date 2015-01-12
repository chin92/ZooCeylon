package com.android.ZooCeylon.com.android.ZooCeylon.Chinthana;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ZooCeylon.R;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<NewsItem> newsItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<NewsItem> movieItems) {
		this.activity = activity;
		this.newsItems = movieItems;
	}

	@Override
	public int getCount() {
		return newsItems.size();
	}

	@Override
	public Object getItem(int location) {
		return newsItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.content);
		//TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        TextView imageUrl= (TextView) convertView.findViewById(R.id.imageURL);

		// getting movie data for the row
		NewsItem m = newsItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(m.getTitle());
		
		// content
		rating.setText(String.valueOf(m.getContent()));
		
        imageUrl.setText(String.valueOf(m.getThumbnailUrl()));
		
		//release date
		year.setText(String.valueOf(m.getDateAdded()));

		return convertView;
	}

}