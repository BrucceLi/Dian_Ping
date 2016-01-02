package com.dianping.activity;

import java.util.List;

import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dianping.R;

import com.dianping.adapter.SubCategoryGridViewAdapter;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.SharedUtils;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.sub_category_gridview)
public class SubCategoryActivity extends BaseActivity{

	@ViewById
	GridView gv_sub_category;
	@ViewById
	TextView tv_sub_category_top_title;

	@Extra
	int sub_id;
	@Extra
	String title;
	@Override
	protected void initVariables() {


		tv_sub_category_top_title.setText(title);


		if(SharedUtils.checkNetwork(this))
		{
			StringRequest stringRequest = new StringRequest(Request.Method.GET, Data.SUB_CATEGORY + "sub_id=" + sub_id, new Response.Listener<String>() {
				@Override
				public void onResponse(String s) {


					Gson gson = new Gson();
					ResponseWrapper<List<String>> result = gson.fromJson(s,
							new TypeToken<ResponseWrapper<List<String>>>() {
							}.getType());
					if (result.getResult_State() == 0) {
						return;
					}
					ListAdapter adapter = new SubCategoryGridViewAdapter(result.getData());

					gv_sub_category.setAdapter(adapter);
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
					Toast.makeText(SubCategoryActivity.this,volleyError.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
			VolleySingleTone.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
		}

	}

	@Click
	public void tv_sub_category_top_titleClicked(View v) {
		finish();
	}

}
