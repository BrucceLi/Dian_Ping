package com.dianping.activity;


import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import de.greenrobot.event.EventBus;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dianping.R;
import com.dianping.application.AssistApplication;
import com.dianping.events.UerLoginedEvent;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.SharedUtils;
import com.dianping.frameworkutils.UTF_8StringRequest;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.ResponseWrapper;
import com.dianping.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.layout_login)
public class LoginActivity extends BaseActivity implements
        PlatformActionListener {

    @ViewById
    TextView tv_check_code;
    @ViewById
    EditText et_username;
    @ViewById
    EditText et_password;
    @ViewById
    EditText et_checkcode;
    @ViewById
    Button btn_login_to_dianping;
    @ViewById
    Button btn_login_by_phone;
    @ViewById
    LinearLayout ll_login_by_QQ;
    @ViewById
    LinearLayout ll_login_by_weixin;


    @Override
    protected void initVariables() {
        String str = SharedUtils.getRandomCode();
        tv_check_code.setText(str);
    }


    @Click({R.id.btn_login_to_dianping,
            R.id.btn_login_by_phone,
            R.id.ll_login_by_QQ,
            R.id.ll_login_by_weixin,
            R.id.tv_check_code,
            })
    public void clicked(View v) {
        switch (v.getId()) {
            case R.id.tv_check_code:
                String str = SharedUtils.getRandomCode();
                tv_check_code.setText(str);
                break;
            case R.id.btn_login_to_dianping:
                handleLogin();
                break;
            case R.id.ll_login_by_QQ:
                loginByQQ();
                break;
            case R.id.ll_login_by_weixin:
                loginByWeixin();
                break;
            case R.id.btn_login_by_phone:
                PhoneLoginActivity_.intent(this).start();
        }
    }

    private void handleLogin() {
        String userName = et_username.getText().toString();
        String userPassword = et_password.getText().toString();

        if (TextUtils.isEmpty(userName)| TextUtils.isEmpty(userPassword)) {
            showToast("用户名 密码不能为空");
            return;
        } else if (!et_checkcode.getText().toString().equals(tv_check_code.getText().toString())) {
            showToast("验证码输入错误！");
            return;
        }

        UTF_8StringRequest utf_8StringRequest = new UTF_8StringRequest(Request.Method.GET, Data.LOGIN_URI + "login" + "&name=" + userName + "&password=" + userPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Gson gson = new GsonBuilder().create();
                ResponseWrapper<User> result = gson.fromJson(s, new TypeToken<ResponseWrapper<User>>() {
                }.getType());
                if (result.getResult_State() == 1) {
                    User user = result.getData();
                    //更新用户的登录状态
                    loginSuccess(user.getUserName(), user.getCityName());
                } else {
                    showToast(result.getMsg());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                if(volleyError!=null)
                {
                    showToast(volleyError.getMessage());
                }
            }
        });

        VolleySingleTone.getInstance(getApplicationContext()).addToRequestQueue(utf_8StringRequest);
    }

    private void loginSuccess(String userName, String cityName) {
        //如果登录成功 我们就将密码和用户id存储到本地
        SharedUtils.putUserNameTopreference(getApplicationContext(), userName, cityName);
        ShareSDK.stopSDK(this);
        EventBus.getDefault().post(new UerLoginedEvent(userName, cityName));
        AssistApplication.getActivityStack().finishActivity(this);
    }

    private void loginByQQ() {
        ShareSDK.initSDK(this);
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        platform.setPlatformActionListener(this);
        if (platform.isAuthValid()) {
            String userId = platform.getDb().getUserId();
            String userName = platform.getDb().getUserName();
            loginSuccess(userName, userId);
        } else {
            platform.showUser(null);
        }
    }

    private void loginByWeixin() {

    }


    @Override
    public void onCancel(Platform arg0, int arg1) {

        showToast("授权取消");
    }


    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
        platformCompleted(arg0);
    }

    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {

        showToast("连接服务器失败");
    }

    @UiThread
    void platformCompleted(Platform platform)
    {
        platform.removeAccount();
        ShareSDK.stopSDK(this);
        loginSuccess(
                platform.getDb().getUserId(),
                platform.getDb().getUserName()
        );

    }
}
