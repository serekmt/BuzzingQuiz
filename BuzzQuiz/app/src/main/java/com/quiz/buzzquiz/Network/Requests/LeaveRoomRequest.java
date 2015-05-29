package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Network.ApiInterface;

public class LeaveRoomRequest extends RetrofitSpiceRequest<String, ApiInterface> {

    private String name;
    private String credentials;
    private boolean remove;


    public LeaveRoomRequest(String name, String credentials, boolean remove) {
        super(String.class, ApiInterface.class);
        this.name = name;
        this.credentials = credentials;
        this.remove = remove;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        getService().leaveRoom(name, credentials, remove);
        return "";
    }
}
