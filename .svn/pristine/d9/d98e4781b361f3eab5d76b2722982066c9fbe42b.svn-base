package com.dianping.frameworkutils;

import java.net.ConnectException;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SharedUtils {

	private static final String SHARE_MSG = "Dianping";
	private static final String HAS_COME_YET = "come_yet";

	public static boolean checkIsFirstComeHere(Context context) {

		if (!context.getSharedPreferences(SHARE_MSG, Context.MODE_PRIVATE)
				.getBoolean(HAS_COME_YET, false)) {

			context.getSharedPreferences(SHARE_MSG, Context.MODE_PRIVATE)
			.edit().putBoolean(HAS_COME_YET, true).commit();
			return true;
		}
		return false;
		
	}
	private static String Letters="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String getRandomCode()
	{
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<4;i++)
		{
			int r = new Random().nextInt(62);
			builder.append(Letters.charAt(r));
		}
		return builder.toString();
	}
	public static void putUserNameTopreference(Context context,String userName,String cityName)
	{
		Editor editor=context.getSharedPreferences(SHARE_MSG,Context.MODE_APPEND).edit();
		editor.putString("userName", userName);
		editor.putString("cityName", cityName);
		editor.commit();
	}
	public static String[] getUserNameFromPreference(Context context)
	{
		String user[]=new String[2];
		user[0]=context.getSharedPreferences(SHARE_MSG,Context.MODE_PRIVATE).getString("userName", null);
		user[1]=context.getSharedPreferences(SHARE_MSG,Context.MODE_PRIVATE).getString("cityName", null);
		
		return user;
}
	public static boolean checkNetwork(Context context)
	{
		ConnectivityManager connection = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo  mob_info = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi_info=connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(mob_info.isConnected()|wifi_info.isConnected())
		{
		return true;
		}
		return false;
	}
	public static final LinearLayoutManager getVerticalLinearLayoutManager(Context context)
	{
		LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
		return layoutManager;
	}
	public static final LinearLayoutManager getHorizontalLinearLayoutManager(Context context)
	{
		LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
		return layoutManager;
	}
}
