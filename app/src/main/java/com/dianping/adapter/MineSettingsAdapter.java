package com.dianping.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianping.R;

public class MineSettingsAdapter extends
		RecyclerView.Adapter<MineSettingsAdapter.ItemViewHolder> {
	static final String itemNames[] = {
			"我的订单",
			"我的团购券",
			"我的抵用券",
			"我的卡包",
			"我的收藏",
			"最近浏览",
			"设置",
	};
  	static final	int itemsIcons[] = {

			R.mipmap.zhang_order_user_icon_normal,
			R.mipmap.my_coupon_user_icon_normal,
			R.mipmap.my_discount_user_icon_normal,
			R.mipmap.my_card_user_icon_normal,
			R.mipmap.my_favorite_user_icon_normal,
			R.mipmap.my_history_user_icon_normal,
			R.mipmap.my_setting_user_icon_normal,

	};
	Context mContext;

	public MineSettingsAdapter(Context context) {
		mContext = context;
	}

	class ItemViewHolder extends RecyclerView.ViewHolder {
		public TextView textView;

		public ItemViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.tv_my_form);
		}

	}

	@Override
	public int getItemCount() {
		return itemNames.length;
	}

	@Override
	public void onBindViewHolder(ItemViewHolder arg0, int arg1) {
		Drawable drawable_left = mContext.getResources().getDrawable(
				itemsIcons[arg1]);
		Drawable drawable_right = mContext.getResources().getDrawable(
				R.mipmap.arrow_normal);

		drawable_left.setBounds(0, 0, 50, 50);
		drawable_right.setBounds(0, 0, 30, 30);
		arg0.textView.setCompoundDrawables(drawable_left, null,
				drawable_right, null);
		arg0.textView.setText(itemNames[arg1]);
		if(arg1==5|arg1==7)
		{

			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(arg0.textView.getLayoutParams());
			params.setMargins(0, 40, 0, 0);

			arg0.textView.setLayoutParams(params);
		}
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext()).inflate(
				R.layout.list_item_mine_settings, arg0, false);
		return new ItemViewHolder(view);
	}


}
