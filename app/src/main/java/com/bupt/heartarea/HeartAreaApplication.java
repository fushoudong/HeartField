package com.bupt.heartarea;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bupt.heartarea.bean.Result;
import com.bupt.heartarea.utils.Constants;
import com.bupt.heartarea.utils.GlobalData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuqing on 2017/8/14.
 */

public class HeartAreaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initAsync();
    }

    public void initAsync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            public Void doInBackground(Void... voids) {
                RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_NEWS, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONObject resultObject = jsonObject.getJSONObject("result");
                            Gson gson = new Gson();
                            Result result = gson.fromJson(resultObject.toString(), Result.class);
                            GlobalData.result = result;
                            for (Result.DataBean dataBean : result.getData()) {
                                System.out.println(dataBean.toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("请求失败", volleyError.getMessage(), volleyError);
                    }
                });
                mQueue.add(jsonObjectRequest);
                return null;
            }
        }.execute();
    }
}
