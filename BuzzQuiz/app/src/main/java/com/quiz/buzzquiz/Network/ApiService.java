package com.quiz.buzzquiz.Network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;


public class ApiService extends RetrofitGsonSpiceService {

    //private final static String BASE_URL = "http://bookweb.ciolek.info/api/v1";
    private final static String BASE_URL = "http://buzzingquiztest.azurewebsites.net/api";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ApiInterface.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}