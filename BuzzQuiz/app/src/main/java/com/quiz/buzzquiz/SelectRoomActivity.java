package com.quiz.buzzquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.quiz.buzzquiz.Models.AppDataSingleton;
import com.quiz.buzzquiz.Models.Room;
import com.quiz.buzzquiz.Network.InternetActivity;
import com.quiz.buzzquiz.Network.Requests.GetAllRoomsRequest;

import java.util.ArrayList;


public class SelectRoomActivity extends InternetActivity {

    private LinearLayout avalableRooms;
    private TextView OrSelectTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppDataSingleton.getInstance().getCurrentPlayer() == null)
            NavigateToLoginView();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        avalableRooms = (LinearLayout)this.findViewById(R.id.avalableRooms);
        avalableRooms.setVisibility(View.INVISIBLE);
        this.OrSelectTextView = new TextView(getApplicationContext());
        OrSelectTextView.setText("Albo Wybierz");
        OrSelectTextView.setGravity(Gravity.CENTER);
        OrSelectTextView.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_Large);
        OrSelectTextView.setLayoutParams(new android.view.ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        avalableRooms.addView(OrSelectTextView);
    }
    @Override
    public void onStart() {
        super.onStart();


        GetAllRoomsRequest getAllRoomsRequest = new GetAllRoomsRequest();

        spiceManager.execute(getAllRoomsRequest, new RequestListener<Room.List>() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getBaseContext(), "Jest błąd!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Room.List rooms) {

                if(rooms != null)
                {
                    if((avalableRooms).getChildCount() > 0)
                        (avalableRooms).removeAllViews();

                    avalableRooms.addView(OrSelectTextView);

                    for (Room room : rooms) {
                        Button b = GetNewButton(room.Name);
                        avalableRooms.addView(b);
                    }

                    avalableRooms.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private Button GetNewButton(String name) {

        Button b = new Button(getApplicationContext());
        b.setLayoutParams(new android.view.ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        b.setText(name);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                AppDataSingleton s = AppDataSingleton.getInstance();
                AppDataSingleton.getInstance().setCurrentRoom(new Room(0, b.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        return b;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_room, menu);
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

    public void NewRoomButtonClicked(View view){
        Intent intent = new Intent(this, NewRoomActivity.class);
        startActivity(intent);
    }


    private void NavigateToLoginView(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
