package com.dianping.fragment;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dianping.R;
import com.dianping.adapter.GoodsListAdapter;
import com.dianping.events.NetEvent;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.SharedUtils;
import com.dianping.frameworkutils.UTF_8StringRequest;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.Goods;
import com.dianping.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EFragment(R.layout.content_group_by_layout)
public class GroupBuyFragment extends BaseFragment {

	@ViewById
	SwipeRefreshLayout pull_to_refresh;
	@ViewById
	RecyclerView rl_goods_list;

	LinearLayoutManager linearLayoutManager;
	static Integer pageIndex = 1;
	GoodsListAdapter adapter;
	List<Goods> goodsList;

	@Override
	protected void initVariables() {
		goodsList = new ArrayList<>();
		adapter=new GoodsListAdapter(getActivity(),goodsList);
		linearLayoutManager = SharedUtils.getVerticalLinearLayoutManager(getActivity());
		rl_goods_list.setLayoutManager(linearLayoutManager);
		rl_goods_list.setAdapter(adapter);
		loadData();

		pull_to_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				loadData();
			}
		});
	}

	private void loadData()
	{
		UTF_8StringRequest stringRequest = new UTF_8StringRequest(Request.Method.GET, Data.PRODUCT_URI + pageIndex, new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {

				Gson gson = new Gson();
				ResponseWrapper<List<Goods>> result = gson.fromJson(s,
						new TypeToken<ResponseWrapper<List<Goods>>>() {
						}.getType());

				if(result.getResult_State()==1) {
					goodsList.addAll(0,result.getData());
					adapter.notifyItemRangeInserted(0, result.getData().size());
					linearLayoutManager.scrollToPosition(0);
					pageIndex++;
					if(pull_to_refresh.isRefreshing())
						pull_to_refresh.setRefreshing(false);
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				if(volleyError!=null)
					EventBus.getDefault().post(new NetEvent("网络出错"));
			}
		});
		VolleySingleTone.getInstance(getContext().getApplicationContext()).addToRequestQueue(stringRequest);
	}


}
