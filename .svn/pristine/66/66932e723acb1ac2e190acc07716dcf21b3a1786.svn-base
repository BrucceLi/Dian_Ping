package com.dianping.frameworkutils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/12/30.
 */
public class UTF_8StringRequest extends StringRequest {



        public UTF_8StringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data,"utf-8");
            } catch (UnsupportedEncodingException var4) {
                parsed = new String(response.data);
            }

            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }

}
