package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Models.Room;
import com.quiz.buzzquiz.Network.ApiInterface;

public class AddRoomRequest extends RetrofitSpiceRequest<Room, ApiInterface> {

    private String name;

    public AddRoomRequest(String name) {
        super(Room.class, ApiInterface.class);
        this.name = name;
    }

    @Override
    public Room loadDataFromNetwork() throws Exception {
        return getService().postRoom(name);
    }
}
