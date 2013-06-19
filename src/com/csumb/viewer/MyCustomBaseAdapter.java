package com.csumb.viewer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {
	
	
	private Integer[] imgid = {
			R.drawable.up2,
			R.drawable.up3,
			R.drawable.up4,
			R.drawable.up5,
			R.drawable.up6,
			R.drawable.up7,
			R.drawable.up8,
			R.drawable.up9,
			R.drawable.up10,
			R.drawable.up11,
			R.drawable.upnull,
			R.drawable.down2,
			R.drawable.down3,
			R.drawable.down4,
			R.drawable.down5,
			R.drawable.down6,
			R.drawable.down7,
			R.drawable.down8,
			R.drawable.down9,
			R.drawable.down10,
			R.drawable.down11,
			R.drawable.downnull};
	
	
	private static ArrayList<DisplayResults> searchArrayList;
	
	private LayoutInflater mInflater;

	public MyCustomBaseAdapter(Context context, List<DisplayResults> list) {
		searchArrayList = (ArrayList<DisplayResults>) list;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.ServiceName);
			holder.imgPhoto = (ImageView) convertView.findViewById(R.id.Up);
			holder.imgPhoto2= (ImageView) convertView.findViewById(R.id.Down);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		System.out.println(searchArrayList.get(position).getName());
		System.out.println(imgid[searchArrayList.get(position).getImageNumber()]);
		System.out.println(imgid[searchArrayList.get(position).getImageNumber2()]);

		holder.txtName.setText(searchArrayList.get(position).getName());		
		holder.imgPhoto.setImageResource(imgid[searchArrayList.get(position).getImageNumber()]);
		holder.imgPhoto2.setImageResource(imgid[searchArrayList.get(position).getImageNumber2()]);
		
		System.out.println(searchArrayList.size());

		return convertView;
	}

	static class ViewHolder {
		TextView txtName;
		ImageView imgPhoto;
		ImageView imgPhoto2;
	}
}
