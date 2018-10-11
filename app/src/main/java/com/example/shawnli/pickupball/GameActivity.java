package com.example.shawnli.pickupball;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;

public class GameActivity extends AppCompatActivity {

    private RecyclerView playerRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button takeMebutton;
    private TextView courtName;
    private TextView courtAddress;
    private TextView gameName;
    Court testCourt = null;
    Court currentCourt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    public void init(){
        currentCourt = Single.getInstance().getCurrentCourt();
        playerRecyclerView = (RecyclerView) findViewById(R.id.playerRecyclerView);
        testCourt = Single.getInstance().getCourts().get(0);

        Game currentGame = Single.getInstance().getCurrentGame();

        //potentially
        //playerRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        playerRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PlayerAdapter(currentGame);
        playerRecyclerView.setAdapter(mAdapter);

        takeMebutton = (Button) findViewById(R.id.takeMeButton);
        takeMebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q="+currentCourt.getAddress());

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });
        courtName = (TextView) findViewById(R.id.courtName);
        courtName.setText(currentCourt.getName());
        courtAddress = (TextView) findViewById(R.id.courtAddress);
        courtAddress.setText(currentCourt.getAddress());
        gameName = (TextView) findViewById(R.id.gameName);
        gameName.setText(currentGame.getName());
    }
}
