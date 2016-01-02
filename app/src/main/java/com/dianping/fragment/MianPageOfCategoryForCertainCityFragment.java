package com.dianping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.dianping.R;

import com.dianping.adapter.MainPageActionAdapter;
import com.dianping.adapter.MainPageRecyclerViewAdapter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.home_page_view)
public class MianPageOfCategoryForCertainCityFragment extends BaseFragment {

	@ViewById
	RecyclerView  title_viewpager;
	@ViewById
	RecyclerView recyclerview_home_fragment_view;

	@Override
	protected void initVariables() {

		LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		title_viewpager.setLayoutManager(layoutManager);
		title_viewpager.setHorizontalScrollBarEnabled(true);
		title_viewpager.setAdapter(new MainPageActionAdapter());
		LinearLayoutManager layoutManagerForRecyclerView=new LinearLayoutManager(getActivity());
		layoutManagerForRecyclerView.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview_home_fragment_view.setLayoutManager(layoutManagerForRecyclerView);

		recyclerview_home_fragment_view.setAdapter(new MainPageRecyclerViewAdapter());

	}
}
