package com.dianping.frameworkutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

/**
 * Created by Administrator on 2015/12/14.
 */
public class PreferenceMap {
    public static boolean isFirstTime(Context context)
    {

        SharedPreferences sharedPreferences=context.getSharedPreferences("Login_History", Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("Login_History",false))
        {
            sharedPreferences.edit().putBoolean("Login_History",true).commit();
            return true;
        }
        return false;
    }
    public static void addAlarmClockInstance(Context context,long alarmTime)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("Alarm_Clock",Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong("alarm_time",alarmTime).commit();
    }
    public static long getAlarmClockInstance(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("Alarm_Clock",Context.MODE_PRIVATE);
       long  rsValue =  sharedPreferences.getLong("alarm_time",-1);
        return rsValue;
    }
}
