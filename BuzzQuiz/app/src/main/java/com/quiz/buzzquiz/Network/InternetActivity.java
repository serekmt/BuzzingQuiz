package com.quiz.buzzquiz.Network;

import android.support.v7.app.ActionBarActivity;

import com.octo.android.robospice.SpiceManager;


public abstract class InternetActivity extends ActionBarActivity {
    protected SpiceManager spiceManager = new SpiceManager(ApiService.class);


    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}

