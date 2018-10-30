package com.example.shawnli.pickupball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import java.util.ArrayList;
import java.util.List;

public class StartGameActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nameEntry;
    String name = "Game name...";
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mButton = (Button) findViewById(R.id.createGameButton);

        nameEntry = (EditText) findViewById(R.id.enterGameName);
        nameEntry.setText(name);
        nameEntry.setOnClickListener(this);
        nameEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = editable.toString();
            }
        });
        mButton = (Button) findViewById(R.id.createGameButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Created " + name,Toast.LENGTH_SHORT).show();
                // Create new game
                Game newGame = new Game();
                newGame.setName(name);

                // Create new user to add to list of players in game
                User firstPlayer = Single.getInstance().getCurrentUser();
                List<User> players = new ArrayList<>();
                players.add(firstPlayer);

                // Add list of Players to game.
                newGame.setPlayers(players);

                // Add game to current court
                Court currentCourt = Single.getInstance().getCurrentCourt();
                currentCourt.addGame(newGame);

                Single.getInstance().setCurrentCourt(currentCourt);
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onClick(View v){
        nameEntry.getText().clear();
    }
}
