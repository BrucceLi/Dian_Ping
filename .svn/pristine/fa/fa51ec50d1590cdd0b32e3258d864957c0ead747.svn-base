package com.dianping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.activity.*;

public class MainPageRecyclerViewAdapter extends RecyclerView.Adapter<MainPageRecyclerViewAdapter.ItemViewHolder>{

	public   String ALL_CATEGORYS[] = {
			"美食",
			"电影",
			"休闲娱乐",
			"酒店",
			"景点",
			"丽人",
			"结婚",
			"亲子",
			"购物",
			"运动健身",
			"教育培训",
			" 生活服务",
			"医疗",
			"家装",
			"爱车",
			"宠物"  };
	public    int ALL_CATEGORY_ICONS[] = {
			R.mipmap.food,
			R.mipmap.movie,
			R.mipmap.xiuxian,
			R.mipmap.hotel,
			R.mipmap.jingdian,
			R.mipmap.beauty,
			R.mipmap.imarry,
			R.mipmap.baby,
			R.mipmap.shoppingmall,
			R.mipmap.sport_s,
			R.mipmap.bangdan_green,
			R.mipmap.homework,
			R.mipmap.hospital,
			R.mipmap.jiazhuang,
			R.mipmap.car,
			R.mipmap.pet
	};
	String[]  details={
			"冀菜 川菜  湘菜 ",
			"电影院   私人影院",
			"咖啡厅 酒吧",
			"五星级酒店 四星级酒店 三星级酒店 经济型酒店",
			"名胜古迹 寺院佛塔 展览馆 ",
			"美发 化妆品 美容/SPA 瘦身纤体",
			"婚纱摄影  婚庆公司",
			"亲子摄影 亲子游乐",
			"户外运动 箱包服饰 ",
			"健身中心 游泳馆",
			"音乐 美术 驾校",
			"家政 送水站 公司企业",
			"医院 体检中心",
			"建材 家居卖场 家用电器"
			,"加油站 汽车美容",
			"宠物医院"
	};

	class ItemViewHolder extends RecyclerView.ViewHolder{
		public TextView textView ,textView_details;
		public ImageView image;

		public ItemViewHolder(View itemView) {
			
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.iv_home_icon);
			textView = (TextView) itemView.findViewById(R.id.tv_home_icon_text);
			textView_details = (TextView) itemView.findViewById(R.id.tv_details);
			itemView.setOnClickListener(new OnClickListener() {
						
				@Override
				public void onClick(View v) {
					SubCategoryActivity_.intent(v.getContext())
							.sub_id(getAdapterPosition()+2)
							.title(ALL_CATEGORYS[getAdapterPosition()])
							.start();
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return details.length;
	}

	@Override
	public void onBindViewHolder(ItemViewHolder arg0, int arg1) {
		arg0.image.setImageResource(ALL_CATEGORY_ICONS[arg1]);
		arg0.textView.setText(ALL_CATEGORYS[arg1]);
		arg0.textView_details.setText(details[arg1]);
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.home_page_index_recyclerview_item1, arg0,false);
		return new ItemViewHolder(view);
	}

}
