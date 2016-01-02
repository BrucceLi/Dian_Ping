package com.dianping.application;

import android.app.Application;

import com.dianping.frameworkutils.ActivityStack;

/**
 * Created by Administrator on 2015/12/14.
 */
public class AssistApplication extends Application {

    private static ActivityStack stack;
    @Override
    public void onCreate() {
        super.onCreate();

    }
    public static ActivityStack getActivityStack()
    {
        if(stack==null)
        {
            stack=ActivityStack.getInstance();
        }
        return stack;
    }
}
