package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Models.Room;
import com.quiz.buzzquiz.Network.ApiInterface;

public class GetAllRoomsRequest extends RetrofitSpiceRequest<Room.List, ApiInterface> {


    public GetAllRoomsRequest() {
        super(Room.List.class, ApiInterface.class);
    }

    @Override
    public Room.List loadDataFromNetwork() throws Exception {
        return getService().rooms();
    }
}

