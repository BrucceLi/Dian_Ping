package com.dianping.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.dianping.events.EmptyEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;


import de.greenrobot.event.EventBus;
@EFragment
public abstract class BaseFragment extends Fragment {

	@AfterViews
	void initSutff()
	{
		EventBus.getDefault().register(this);
		initVariables();

	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		//将fragment注册到事件总线上
		EventBus.getDefault().unregister(this);
	}

	/**
	 * 事件订阅函数 由事件总线调用
	 */
	public void onEvent(EmptyEvent event){}


	protected abstract void initVariables();
}
