package com.quiz.buzzquiz.Network.Requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.quiz.buzzquiz.Models.Player;
import com.quiz.buzzquiz.Network.ApiInterface;

public class GetPlayerRequest extends RetrofitSpiceRequest<Player, ApiInterface> {

    private String name;

    public GetPlayerRequest(String name) {
        super(Player.class, ApiInterface.class);
        this.name = name;
    }

    @Override
    public Player loadDataFromNetwork() throws Exception {
        return getService().getPlayer(name);
    }
}


