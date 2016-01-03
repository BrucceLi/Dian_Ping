package com.dianping.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.dianping.R;
import com.dianping.adapter.DrawerLayoutAdapter;
import com.dianping.events.CitySelectedEvent;
import com.dianping.events.NetEvent;
import com.dianping.events.UerLoginedEvent;
import com.dianping.fragment.*;
import com.dianping.frameworkutils.SharedUtils;
import com.dianping.model.Item;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.layout_main_page)
public class HomePageActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener, OnClickListener, AMapLocationListener, HomeMainPageActionBarFragment.OnSettingClickListener ,MinePersonalSettingsActionBarFragment.OnLoginClickListener {

    @Extra
    String mCityName;

    @ViewById
    RadioGroup rg_main_page_menu;

    @ViewById
    DrawerLayout dl_action_view;

    @ViewById
    ListView lv_action_view;

    @SystemService
    LayoutInflater inflater;


    LocationManagerProxy locationManager;


    public static String cityName;
    PopupWindow window;
    LinearLayout ll;
    Button btn_current_city, btn_switch_city;
    List<Item> mList;

    static int mCheckedId;
    static boolean mIsBackFromActivity;
    private static int[] mImages = {R.mipmap.home_navibar_tips_icon_comment,
            R.mipmap.home_navibar_tips_icon_store,
            R.mipmap.home_navibar_tips_icon_scan,
            R.mipmap.home_add_icon_pay};
    private static String[] mText = {"写点评", "添加商户", "扫一扫", "买单"};




    @Override
    protected void initVariables() {

        configPopUpWindow();

        if (SharedUtils.checkNetwork(this)) {
            getLocation();
        } else {
            Toast.makeText(this, "无法定位当前位置", Toast.LENGTH_SHORT).show();
            cityName = "北京";
        }
        if (mCityName != null) {
            cityName = mCityName;
        }

        commitFragment(new HomeMainPageActionBarFragment_(),
                MianPageOfCategoryForCertainCityFragment_.builder().build());

        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            mList.add(new Item(mImages[i], mText[i]));
        }

        //设置侧拉菜单
        lv_action_view.setAdapter(new DrawerLayoutAdapter(mList, inflater, this));
        rg_main_page_menu.setOnCheckedChangeListener(this);
    }




    //drawerLayout的菜单点击事件
    @ItemClick
    public void lv_action_viewItemClicked() {
        dl_action_view.closeDrawer(Gravity.RIGHT);
    }


    //配置window
    private void configPopUpWindow()
    {
        ll = (LinearLayout) inflater.inflate(R.layout.location_popup_window, null);
        btn_switch_city = (Button) ll.findViewById(R.id.btn_switch_city);
        btn_current_city = (Button) ll.findViewById(R.id.btn_current_city);
        window = new PopupWindow(ll, 300,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        btn_current_city.setOnClickListener(this);
        btn_switch_city.setOnClickListener(this);
    }

    void initListener()
    {

    }

    @Override
    protected void onResume() {
        rg_main_page_menu.check(mCheckedId);
        super.onResume();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if(mIsBackFromActivity)
        {
            mIsBackFromActivity=false;
            return;
        }
        switch (checkedId) {
		case R.id.rb_main_page:

            commitFragment(new HomeMainPageActionBarFragment_(),
                    MianPageOfCategoryForCertainCityFragment_.builder().build()
            );
            mCheckedId=checkedId;
            break;
		case R.id.rb_flash_and_group_buy:

            commitFragment(GroupBuyActionBarFragment_.builder().build(),
                    GroupBuyFragment_.builder().build()
            );
            mCheckedId=checkedId;
            break;
		case R.id.rb_decover:
            DescoveryActivity_.intent(this).start();
            mIsBackFromActivity=true;
            break;
		case R.id.rb_mine:
            commitFragment(MinePersonalSettingsActionBarFragment_.builder().build()
                    , MinePersonalSettingsFragment_.builder().build()
                    );
            mCheckedId=checkedId;
        }
    }

    //提交碎片
    private void commitFragment(BaseFragment actionbar,BaseFragment content)
    {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_action_bar_container,
                        actionbar)
                .replace(R.id.fl_homepage_fragment_container,
                        content).commit();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_current_city:
                window.dismiss();
                break;
            case R.id.btn_switch_city:
                window.dismiss();
                AllCityActivity_.intent(this).start();
        }

    }


    @Override
    public void onLoginClicked() {
        LoginActivity_.intent(this).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseLocationManager();
    }

    //获取位置
    private void getLocation() {
        locationManager = LocationManagerProxy.getInstance(this);
        locationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 2000, 10, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseLocationManager();

    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(AMapLocation arg0) {
        if (arg0 != null) {
            changCityName(arg0.getCity());
        }

    }


    //点击打开侧拉菜单
    @Override
    public void onSettingClicked() {
        if (dl_action_view.isDrawerOpen(lv_action_view))
            dl_action_view.closeDrawer(lv_action_view);
        else
            dl_action_view.openDrawer(lv_action_view);
    }

    @UiThread
    public void changCityName(String pCityName) {
        if (!pCityName.equals(""))
        {
            cityName = pCityName;
            window.showAtLocation(rg_main_page_menu, Gravity.CENTER, 0, 0);
            btn_current_city.setText("当前定位城市-" + cityName);

        }else
        {
            showToast("网络不给力");
        }
        releaseLocationManager();
        //通知菜单改变城市名称
        EventBus.getDefault().post(new CitySelectedEvent(cityName));
    }


    //释放位置管理器
    private void releaseLocationManager()
    {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
            locationManager.destroy();
            locationManager = null;
        }

    }

    public void onEvent(NetEvent event)
    {

        showToast(event.Msg);
    }


    public void onEvent(CitySelectedEvent event) {

        cityName=event.cityName;
    }
}
