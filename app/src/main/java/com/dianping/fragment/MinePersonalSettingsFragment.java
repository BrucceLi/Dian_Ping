package com.dianping.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.dianping.adapter.MineSettingsAdapter;

import org.androidannotations.annotations.EFragment;

@EFragment
public class MinePersonalSettingsFragment extends BaseFragment {


	RecyclerView mRecyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRecyclerView = new RecyclerView(getActivity());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		mRecyclerView.setLayoutParams(params);
		return mRecyclerView;
	}

	@Override
	protected void initVariables() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		mRecyclerView.setAdapter(new MineSettingsAdapter(getActivity()));

	}

}
