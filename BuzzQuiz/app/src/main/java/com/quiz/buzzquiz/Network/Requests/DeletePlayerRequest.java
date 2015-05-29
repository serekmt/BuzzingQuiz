package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Network.ApiInterface;

public class DeletePlayerRequest extends RetrofitSpiceRequest<String, ApiInterface> {

    private String credentials;

    public DeletePlayerRequest(String credentials) {
        super(String.class, ApiInterface.class);
        this.credentials = credentials;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        getService().deletePlayer(credentials);
        return "";
    }
}
