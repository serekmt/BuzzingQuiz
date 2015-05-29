package com.quiz.buzzquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.quiz.buzzquiz.Models.AppDataSingleton;
import com.quiz.buzzquiz.Network.InternetActivity;
import com.quiz.buzzquiz.Network.Requests.AnswerRequest;


public class GameActivity extends InternetActivity {

    private boolean canBeClicked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDataSingleton dataSingleton = AppDataSingleton.getInstance();

        if(dataSingleton.getCurrentPlayer() == null)
            NavigateToLoginView();

        if(dataSingleton.getCurrentRoom() == null)
            NavigateToLoginView();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonAClicked(View view){
        SetButtonClickedMessage(1);
    }

    public void buttonBClicked(View view){
        SetButtonClickedMessage(2);
    }

    public void buttonCClicked(View view){
        SetButtonClickedMessage(3);
    }

    public void buttonDClicked(View view){
        SetButtonClickedMessage(4);
    }

    public void readyClicked(View view){
        SetButtonClickedMessage(5);
    }

    private void SetButtonClickedMessage(int keyId)
    {
        if(!canBeClicked)
            return;
        else
            canBeClicked = false;

        AppDataSingleton dataSingleton = AppDataSingleton.getInstance();

        if(dataSingleton == null || dataSingleton.getCurrentPlayer() == null)
            NavigateToLoginView();

        AnswerRequest answerRequest = new AnswerRequest(
                AppDataSingleton.getInstance().getCurrentPlayer().Credentials,
                AppDataSingleton.getInstance().getCurrentRoom().Name,
                keyId);


        spiceManager.execute(answerRequest, new RequestListener<String>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getApplicationContext(), "Jest błąd!", Toast.LENGTH_SHORT).show();
                canBeClicked = true;
            }

            @Override
            public void onRequestSuccess(String answer) {
                canBeClicked = true;
            }
        });
    }

    private void NavigateToLoginView(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
