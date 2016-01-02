package com.dianping.fragment;

import com.dianping.R;
import com.dianping.events.UerLoginedEvent;
import com.dianping.frameworkutils.SharedUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.layout_mine_actionbar)
public class MinePersonalSettingsActionBarFragment extends BaseFragment {


	
	private OnLoginClickListener listener;

	@ViewById
	ImageView iv_login_icon;
	@ViewById
	TextView tv_login_indicator;
	@ViewById
	TextView tv_changjudi;
	@ViewById
	ImageView iv_login;

	@Override
	public void onAttach(Context context) {
		if(context instanceof OnLoginClickListener)
		{
			listener= (OnLoginClickListener)context;
		}else
		{
			try {
				throw new Exception("ativity 没有实现" +
						"onloginclicklistener接口");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onAttach(context);
	}

	@Override
	protected void initVariables() {

		String user[] = SharedUtils.getUserNameFromPreference(getActivity().getApplicationContext());
		if(user[0]!=null&&user[1]!=null)
		{

			iv_login_icon.setImageResource(R.drawable.logined_icon_layerlist);
			tv_login_indicator.setText(user[0]);
			tv_changjudi.setText(user[1]);
		}
	}

	public interface OnLoginClickListener
	{
		public void onLoginClicked();
	}

	@Click
	void iv_login(View v)
	{
		if(listener!=null)
			listener.onLoginClicked();
	}

	public void onEvent(UerLoginedEvent event)
	{
		iv_login_icon.setImageResource(R.drawable.logined_icon_layerlist);
		tv_login_indicator.setText(event.userName);
		tv_changjudi.setText(event.cityName);
	}
}
