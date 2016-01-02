package com.dianping.frameworkutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import android.app.Activity;

public class ActivityStack {

	private  Stack<Activity> stack=null;
	private static ActivityStack instance=null;
	public boolean addActivity(Activity activity) {

		return stack.add(activity);

	}
	private  ActivityStack() {
		stack = new Stack<Activity>();
	}
	
	public int size()
	{
		return stack.size();
	}
	
	public static ActivityStack getInstance()
	{
		if(instance==null)
		{
			instance= new ActivityStack();
		}
		return instance;
	}
	//移除指定的activity
	public  void finishActivity(Activity activity)
	{
		if(stack.contains(activity))
		{
			if(!activity.isFinishing())
			 stack.get(stack.indexOf(activity)).finish();
		}
	}
	//移除指定的activity
	public  void finishActivityByClass(Class<? extends Activity> actfis)
	{
		Activity activity=findActivityByClass(actfis);
		if(activity!=null)
		{
			finishActivity(activity);
		}
	}
	/**
	 * 根据类名找到指定的activtiy
	 * @param actfind
	 * @return
	 */
	public Activity findActivityByClass(Class<? extends Activity> actfind)
	{
		Iterator<Activity> iter =  stack.iterator();
		Activity activity=null;
		
		while(iter.hasNext())
		{
			activity = iter.next();
			if(activity.getClass().getName().equals(actfind.getName())&!activity.isFinishing())
				return activity;
		}
		return null;
	}
	//停止某个activity 之上的所有activity
	public void finishToActivity(Class<? extends Activity> actfin ,boolean includeSelf)
	{
		
		
		List<Activity> buf=new ArrayList<Activity>();
		
		for(int i=stack.size()-1;i>=0;i--)
		{
			Activity activity=stack.get(i);
			if(activity.getClass().isAssignableFrom(actfin))
			{
				for (Activity act : buf) {
					act.finish();
				}
				if(includeSelf)
					activity.finish();
				
				return ;
			}
			else
			{
				buf.add(activity);
			}
		}
		
		
	}
	//移除
	public void removeActivity(Activity activity)
	{
		stack.remove(activity);
	}
}
