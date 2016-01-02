package com.dianping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianping.R;

public class MainPageActionAdapter extends RecyclerView.Adapter<MainPageActionAdapter.MyViewHolder> {

	int icons[]={R.mipmap.groupon_u,R.mipmap.hotel_u,R.mipmap.takeaway_u,R.mipmap.film_u,
			R.mipmap.discount_u,R.mipmap.comment_u
	};
	String texts[] = {"团购优惠","订酒店","定外卖","看电影","找优惠","写点评"};

	
	
	class MyViewHolder extends RecyclerView.ViewHolder
	{
		public ImageView imageView;
		public TextView textView;
		public MyViewHolder(View itemView) {
			super(itemView);
			imageView = (ImageView) itemView.findViewById(R.id.iv_home_icon);
			textView = (TextView) itemView.findViewById(R.id.tv_home_icon_text);

			itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return icons.length;
	}

	@Override
	public void onBindViewHolder(MyViewHolder arg0, int arg1) {
		arg0.imageView.setImageResource(icons[arg1]);
		arg0.textView.setText(texts[arg1]);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.home_page_index_gridview_item,arg0,false);
		return new MyViewHolder(view);
	}
}
