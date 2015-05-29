package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Network.ApiInterface;

/**
 * Created by m on 2015-05-29.
 */

public class AnswerRequest extends RetrofitSpiceRequest<String, ApiInterface> {

    private String credentials;
    private String roomName;
    private int keyId;

    public AnswerRequest(String credentials, String roomName, int kayId) {
        super(null, ApiInterface.class);

        this.credentials = credentials;
        this.roomName = roomName;
        this.keyId = kayId;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        return getService().getAnswer(credentials, roomName, keyId);
    }
}