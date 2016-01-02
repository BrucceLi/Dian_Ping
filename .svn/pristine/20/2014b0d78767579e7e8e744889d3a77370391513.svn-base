package com.dianping.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dianping.application.AssistApplication;
import com.dianping.events.EmptyEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import de.greenrobot.event.EventBus;
@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @AfterViews
    void initStuff()
    {
        EventBus.getDefault().register(this);
        AssistApplication.getActivityStack().addActivity(this);
        initVariables();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        AssistApplication.getActivityStack().removeActivity(this);
        super.onDestroy();
    }
    protected abstract void initVariables();
    public void onEvent(EmptyEvent event){}


    @UiThread
    void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
