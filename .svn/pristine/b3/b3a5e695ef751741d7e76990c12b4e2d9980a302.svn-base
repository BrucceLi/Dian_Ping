package com.dianping.adapter;

import java.util.List;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.activity.*;
import com.dianping.model.Goods;
import com.squareup.picasso.Picasso;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodViewHolder> {

	Context mContext;
	List<Goods> goodsList;

	public GoodsListAdapter(Context context,List<Goods> list)
	{
		this.goodsList=list;
		this.mContext=context;
	}

	@Override
	public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_list_item, parent,false);
		return new GoodViewHolder(view);
	}

	@Override
	public void onBindViewHolder(GoodViewHolder holder, int position) {
		Picasso.with(mContext).load(goodsList.get(position).getImgUrl()).placeholder(R.mipmap.img_null).into(holder.goodsIcon);
		holder.goodsPrice.setText("￥"+goodsList.get(position).getPrice().toString());
		holder.goodsSaledCount.setText(goodsList.get(position).getSaledCount().toString()+"份");
		holder.goodsShortTitle.setText(goodsList.get(position).getShortTitle());
		holder.goodsTitle.setText(goodsList.get(position).getTitle());
		StringBuffer stf = new StringBuffer("￥"+goodsList.get(position).getValue().toString());
		SpannableString spannable=new SpannableString(stf);
		spannable.setSpan(new StrikethroughSpan(), 0,stf.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
		holder.goodsValue.setText(spannable);

	}

	@Override
	public int getItemCount() {
		return goodsList==null?0:goodsList.size();
	}

	class GoodViewHolder extends RecyclerView.ViewHolder {
		public 	TextView goodsPrice,goodsSaledCount,goodsShortTitle,goodsTitle,goodsValue;

		public ImageView  goodsIcon;

		public GoodViewHolder(View itemView) {
			super(itemView);
			goodsIcon=(ImageView) itemView.findViewById(R.id.iv_downlaod_icon);
			goodsShortTitle=(TextView) itemView.findViewById(R.id.tv_short_title);
			goodsTitle=(TextView) itemView.findViewById(R.id.tv_product_title);
			goodsPrice=(TextView) itemView.findViewById(R.id.tv_price);
			goodsValue=(TextView) itemView.findViewById(R.id.tv_value);
			goodsSaledCount=(TextView) itemView.findViewById(R.id.textview_count);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ProductDetailActivity_.intent(mContext).data(goodsList.get(getAdapterPosition())).start();
				}
			});
		}
	}




}
