package com.quiz.buzzquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.quiz.buzzquiz.Models.AppDataSingleton;
import com.quiz.buzzquiz.Models.Player;
import com.quiz.buzzquiz.Models.Room;
import com.quiz.buzzquiz.Network.InternetActivity;
import com.quiz.buzzquiz.Network.Requests.AddPlayerRequest;
import com.quiz.buzzquiz.Network.Requests.DeletePlayerRequest;
import com.quiz.buzzquiz.Network.Requests.GetAllPlayerRequest;
import com.quiz.buzzquiz.Network.Requests.GetPlayerRequest;

import java.util.ArrayList;


public class StartActivity extends InternetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    @Override
    public void onStart() {
        super.onStart();
//
//        CommentsRequest commentsRequest = new CommentsRequest();
//
//        spiceManager.execute(commentsRequest, new RequestListener<Room.List>() {
//            @Override
//            public void onRequestFailure(SpiceException spiceException) {
//                Toast.makeText(getActivity(), "Jest błąd!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRequestSuccess(Room.List comments) {
//                commentAdapter.addNewComments(comments);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

        if (id == R.id.action_newRoom) {
            Intent intent = new Intent(this, NewRoomActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_game) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_selectRoom) {
            Intent intent = new Intent(this, SelectRoomActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LogInButtonClicked(View view){

        String name = ((EditText)this.findViewById(R.id.LoginEditText)).getText().toString();

        AddPlayerRequest addPlayerRequest = new AddPlayerRequest(name);

        spiceManager.execute(addPlayerRequest, new RequestListener<Player>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getBaseContext(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Player player) {
                if (player != null) {
                    if(AppDataSingleton.getInstance().getCurrentPlayer() != null)
                        DeleteCurrentPlayer();
                    AppDataSingleton.getInstance().setCurrentPlayer(player);
                    Intent intent = new Intent(getBaseContext(), SelectRoomActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Gracz o tej nazwie już istnieje! Wybierz inną!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        GetAllPlayerRequest playersRequest = new GetAllPlayerRequest();
//
//        spiceManager.execute(playersRequest, new RequestListener<Player.List>() {
//            @Override
//            public void onRequestFailure(SpiceException spiceException) {
//                Toast.makeText(getBaseContext(), "Jest błąd!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRequestSuccess(Player.List comments) {
//                ArrayList<Player> result = comments;
//            }
//        });
    }

    private void DeleteCurrentPlayer() {

        Player player = AppDataSingleton.getInstance().getCurrentPlayer();

        DeletePlayerRequest playersRequest = new DeletePlayerRequest(player.Credentials);

        spiceManager.execute(playersRequest, new RequestListener<String>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getBaseContext(), "Jest błąd!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(String s) {
                AppDataSingleton.getInstance().setCurrentPlayer(null);
            }
        });
    }
}
