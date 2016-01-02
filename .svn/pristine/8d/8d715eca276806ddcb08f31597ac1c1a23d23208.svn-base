package com.dianping.activity;

import java.util.regex.Pattern;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
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

@EActivity(R.layout.layout_phone_check)
public class PhoneLoginActivity extends BaseActivity implements OnClickListener {

    @ViewById
    TextView tv_top_title;
    @ViewById
    TextView tv_get_check_code;
    @ViewById
    EditText et_phone_number;
    @ViewById
    EditText et_phone_check_code;
    @ViewById
    EditText et_acount_password;
    @ViewById
    Button btn_phone_login_to_dianping;

    CheckCodeCountDownTimer timer;
    final String phoneMatcher = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    String phonenumber;
    String password;
    final int SUBMIT_CODE_OK = 1;
    final int GET_CODE_OK = 2;
    EventHandler evenHandler;

    @Override
    protected void initVariables() {
        SMSSDK.initSDK(this, "e227277ecd1c", "164a520f6067c9522c528128652fd898");
        timer = new CheckCodeCountDownTimer(60000, 1000);
        evenHandler = new EventHandler() {
            @Override
            public void afterEvent(int arg0, int arg1, Object arg2) {
                super.afterEvent(arg0, arg1, arg2);
                if (arg1 == SMSSDK.RESULT_COMPLETE) {
                    if (arg0 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        verify(SUBMIT_CODE_OK);
                    } else if (arg0 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        verify(GET_CODE_OK);
                    }
                } else {
                    showToast("验证码发送失败");
                }
            }
        };

    }

    @Click({R.id.tv_top_title, R.id.tv_get_check_code, R.id.btn_phone_login_to_dianping})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_top_title:
                finish();
                break;
            case R.id.tv_get_check_code:
                timer.start();
                getCheckCode();
                break;
            case R.id.btn_phone_login_to_dianping:
               // sendCheckCode();
                loginSuccess("张韶勇","南寨");
        }
    }

    private void getCheckCode() {

        SMSSDK.registerEventHandler(evenHandler);
        Pattern p = Pattern.compile(phoneMatcher);

        phonenumber = et_phone_number.getText().toString();
        password = et_acount_password.getText().toString();
        if (phonenumber != null && phonenumber != "" && password != null && password != "") {
            if (p.matcher(phonenumber).matches()) {
                SMSSDK.getVerificationCode("86", phonenumber);
            }
        } else {
            showToast("请填写手机号码，密码两项");
        }

    }

    private void sendCheckCode() {
        SMSSDK.submitVerificationCode("86", et_phone_number.getText().toString(), et_phone_check_code.getText().toString());
    }


    @UiThread
    void verify(int msg) {
        if (msg == GET_CODE_OK) {
            //获取验证码成功
            showToast("我们已将验证码发送到您的手机，请注意查收！");
        }
        if (msg == SUBMIT_CODE_OK) {
            //提交验证码成功 将用户信息 保存到数据库
            SMSSDK.unregisterAllEventHandler();

            phonenumber = this.et_phone_number.getText().toString();
            password = this.et_acount_password.getText().toString();

            UTF_8StringRequest utf_8StringRequest = new UTF_8StringRequest(Request.Method.GET,
                    Data.LOGIN_URI + "register&phonenumber=" + phonenumber + "&password=" + password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            responsed(s);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            showToast("链接出错！");

                        }
                    });
            VolleySingleTone.getInstance(getApplicationContext()).addToRequestQueue(utf_8StringRequest);
        }
    }

    @UiThread
    void responsed(String json) {
        Gson gson = new GsonBuilder().create();

        ResponseWrapper<User> result = gson.fromJson(json, new TypeToken<ResponseWrapper<User>>() {
        }.getType());
        if (result.getResult_State() == 0) {
            showToast(result.getMsg());
        } else {
            loginSuccess(
                    et_phone_number.getText().toString(),
                    et_acount_password.getText().toString()
            );
        }

    }

    class CheckCodeCountDownTimer extends CountDownTimer {

        public CheckCodeCountDownTimer(long millisInFuture,
                                       long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_check_code.setText(millisUntilFinished / 1000 + "后重新发送");
            tv_get_check_code.setClickable(false);

        }

        @Override
        public void onFinish() {
            tv_get_check_code.setText("获取验证码");
            tv_get_check_code.setClickable(true);
        }

    }

    @UiThread
    void loginSuccess(String phone, String password) {
        //如果登录成功 我们就将密码和用户id存储到本地
        SharedUtils.putUserNameTopreference(getApplicationContext(), phone, password);
        ShareSDK.stopSDK(this);
        EventBus.getDefault().post(new UerLoginedEvent(phone, password));
        AssistApplication.getActivityStack().finishActivityByClass(LoginActivity_.class);
        AssistApplication.getActivityStack().finishActivity(this);
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}
