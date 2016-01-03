package com.dianping.activity;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dianping.R;
import com.dianping.adapter.CityRecyclerAdapter;
import com.dianping.events.CitySelectedEvent;
import com.dianping.events.LetterCheckedEvent;

import com.dianping.fragment.*;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.SharedUtils;
import com.dianping.frameworkutils.UTF_8StringRequest;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.City;
import com.dianping.model.ResponseWrapper;
import com.dianping.view.SiderMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.layout_all_cities)
public class AllCityActivity extends BaseActivity implements SiderMenu.OnLetterCheckedListener{
	
	@ViewById
	Button btn_backpress;
	@ViewById
	SiderMenu sm_selector;


	@ViewById
	RecyclerView recyclerview_city;

	List<City> mList= new ArrayList<>();
	LinearLayoutManager layoutManager;
	CityRecyclerAdapter adapter;

	@Override
	protected void initVariables() {


		configRecyclerView();
		sm_selector.setOnLetterCheckedListener(this);
		loadCities();



	}

	void configRecyclerView()
	{
		layoutManager = SharedUtils.getVerticalLinearLayoutManager(this);
		recyclerview_city.setLayoutManager(layoutManager);
		recyclerview_city.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				outRect.set(1,1,1,1);

			}
		});
	}


	void loadCities()
	{
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
						recyclerview_city.setAdapter(adapter);

					}
					else
					{
						showToast("无数据");

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				showToast("检查网络！");
			}
		});
		VolleySingleTone.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

	}

	@Click
	void btn_backpressClicked() {

		finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	@Override
	public void onLetterChecked(String letter) {
		for (int i = 0; i < mList.size(); i++) {
			if (letter.equals(mList.get(i).getShotKey())) {
				layoutManager.scrollToPositionWithOffset(i, 0);
				break;
			}
		}
	}
	@UiThread(delay = 300)
	public void onEvent(CitySelectedEvent event)
	{
		HomePageActivity_.intent(this).mCityName(event.cityName).start();
	}



}
