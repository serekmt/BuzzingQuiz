package com.quiz.buzzquiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.quiz.buzzquiz.Models.AppDataSingleton;
import com.quiz.buzzquiz.Models.Room;
import com.quiz.buzzquiz.Network.InternetActivity;
import com.quiz.buzzquiz.Network.Requests.AddRoomRequest;
import com.quiz.buzzquiz.Network.Requests.LeaveRoomRequest;


public class NewRoomActivity extends InternetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppDataSingleton.getInstance().getCurrentPlayer() == null)
            NavigateToLoginView();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        Configuration newConfig = getResources().getConfiguration();
        ManageViewOnConfigChange(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_room, menu);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        ManageViewOnConfigChange(newConfig);
    }

    private void ManageViewOnConfigChange(Configuration newConfig){
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ImageView imgView = (ImageView)findViewById(R.id.logoNewRoom);
            imgView.setVisibility(View.GONE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            ImageView imgView = (ImageView)findViewById(R.id.logoNewRoom);
            imgView.setVisibility(View.VISIBLE);
        }
    }

    public void AddRoomButtonClicked(View view){

        EditText newRoomName = (EditText)this.findViewById(R.id.newRoomName);
        AddRoomRequest addRoomRequest = new AddRoomRequest(newRoomName.getText().toString());

        spiceManager.execute(addRoomRequest, new RequestListener<Room>() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getBaseContext(), "Bład połączenia z Internetem!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Room room) {
                if(room == null)
                {
                    Toast.makeText(getBaseContext(),
                            "Istnieje już pokój o tej nazwie!",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    if(AppDataSingleton.getInstance().getCurrentRoom() != null)
//                        LeaveCurrentRoom();
                    AppDataSingleton.getInstance().setCurrentRoom(room);
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void LeaveCurrentRoom() {

        Room room = AppDataSingleton.getInstance().getCurrentRoom();

        LeaveRoomRequest roomRequest = new LeaveRoomRequest(room.Name);

        spiceManager.execute(roomRequest, new RequestListener<String>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getBaseContext(), "Jest błąd!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(String s) {
                AppDataSingleton.getInstance().setCurrentRoom(null);
            }
        });
    }

    private void NavigateToLoginView(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
