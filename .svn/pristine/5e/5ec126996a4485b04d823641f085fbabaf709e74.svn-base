package com.dianping.activity;


import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dianping.R;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.Goods;
import com.dianping.model.PerProductImages;
import com.dianping.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.layout_product_detail)
public class ProductDetailActivity extends BaseActivity{

	@ViewById
	ViewFlipper vf_product_icons;
	@ViewById
	Button  btn_back_to_products;
	@ViewById
	Button	btn_online_service;
	@ViewById
	Button  btn_online_buy_now;
	@ViewById
	TextView tv_product_short_title;
	@ViewById
	TextView tv_product_detail_title;
	@ViewById
	TextView tv_detail_price, tv_detail_value;
	@ViewById
	RatingBar rbar_rating_suggestion;

	@Extra
	Goods data;

	@Override
	protected void initVariables() {

		vf_product_icons.setFlipInterval(3000);

		vf_product_icons.setHorizontalScrollBarEnabled(true);
		vf_product_icons.setHorizontalFadingEdgeEnabled(true);
		vf_product_icons.setInAnimation(AnimationUtils.makeInAnimation(this, false));
		vf_product_icons.setOutAnimation(AnimationUtils.makeOutAnimation(this, false));
		bindData(data);
	}

	private void bindData(Goods goods)
	{
		
		tv_product_short_title.setText(goods.getShortTitle());
		tv_product_detail_title.setText(goods.getTitle());
		tv_detail_price.setText("￥"+goods.getPrice().toString());
		tv_detail_value.getPaint().setStrikeThruText(true);
		tv_detail_value.setText("￥"+goods.getValue());
		float grade = goods.getGrade()%10;
		float rating =  (grade/2f);
		rbar_rating_suggestion.setRating(rating);
		int productId = goods.getProductId();

		StringRequest stringRequest = new StringRequest(Request.Method.GET, Data.PRODUCT_IMAGES_UI + productId, new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				dataDownLoaded(s);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {

			}
		});
		VolleySingleTone.getInstance(this).addToRequestQueue(stringRequest);
	}

	@Click({R.id.btn_back_to_products,R.id.btn_online_buy_now,R.id.btn_online_service})
	public void clicked(View v) {
		switch (v.getId()) {
		case R.id.btn_back_to_products:
			finish();
			break;
		case R.id.btn_online_service:
			Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+data.getShop().getTel()));
			startActivity(intent);
		}
	}

	public void dataDownLoaded(String json) {
			Gson gson = new Gson();
			ResponseWrapper<List<PerProductImages>> result = gson.fromJson(json, new TypeToken<ResponseWrapper<List<PerProductImages>>>(){}.getType());
			List<PerProductImages> list=null;
		if(result.getResult_State()==1)
			{
				 list=result.getData();
			}
			else
			{
				return ;
			}
			for(int i=0;i<list.size();i++)
			{
				ImageView view=new ImageView(this);
				view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				Picasso.with(this).load(list.get(i).getImagUrl()).placeholder(R.mipmap.img_null).into(view);
				vf_product_icons.addView(view, i);
			}

		vf_product_icons.startFlipping();
			
			
	}
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}
