package com.dianping.fragment;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.dianping.adapter.CityAdapter;
import com.dianping.adapter.CityRecyclerAdapter;
import com.dianping.events.CitySelectedEvent;
import com.dianping.events.LetterCheckedEvent;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.UTF_8StringRequest;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.City;
import com.dianping.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.EFragment;

@EFragment
public class CityFragment extends BaseFragment{

	List<City> mList =null;
	RecyclerView view;
	LinearLayoutManager layoutManager;
	CityRecyclerAdapter adapter;

	@Override
	protected void initVariables() {
		mList = new ArrayList<>();


		UTF_8StringRequest stringRequest=new UTF_8StringRequest(Request.Method.GET, Data.CITY_URI , new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				try {

					Gson gson = new Gson();
					ResponseWrapper<List<City>> result=gson.fromJson(s, new TypeToken<ResponseWrapper<List<City>>>(){}.getType());
					if(result.getResult_State()==1)
					{
						mList = result.getData();
						adapter = new CityRecyclerAdapter(mList);
						view.setAdapter(adapter);

					}
					else
					{
						Toast.makeText(getActivity(), "无数据", Toast.LENGTH_SHORT).show();

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Toast.makeText(getActivity(),"检查网络！",Toast.LENGTH_LONG).show();
			}
		});
		VolleySingleTone.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater,container,savedInstanceState);
		view = new RecyclerView(getActivity());
		view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT));
		layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
		view.setLayoutManager(layoutManager);
		return view;
	}



	//接受选中的字母事件
	public void onEvent(LetterCheckedEvent event)
	{
		for (int i = 0; i < mList.size(); i++) {
			if (event.letter.equals(mList.get(i).getShotKey())) {
				layoutManager.scrollToPositionWithOffset(i, 0);
				break;
			}
		}
	}

}

