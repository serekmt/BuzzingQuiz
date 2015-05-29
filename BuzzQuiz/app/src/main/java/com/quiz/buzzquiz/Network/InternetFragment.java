package com.quiz.buzzquiz.Network;


import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;

public abstract class InternetFragment extends Fragment {
    protected SpiceManager spiceManager = new SpiceManager(ApiService.class);

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();

    }
}