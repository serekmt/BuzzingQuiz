package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Models.Player;
import com.quiz.buzzquiz.Network.ApiInterface;

public class GetAllPlayerRequest extends RetrofitSpiceRequest<Player.List, ApiInterface> {

    public GetAllPlayerRequest() {
        super(Player.List.class, ApiInterface.class);
    }

    @Override
    public Player.List loadDataFromNetwork() throws Exception {
        return getService().players();
    }
}
