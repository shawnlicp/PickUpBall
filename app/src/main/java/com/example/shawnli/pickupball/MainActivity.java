package com.example.shawnli.pickupball;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public String loadJsonFile(){
        String json = null;
        try {
            InputStream is = getBaseContext().getAssets().open("courts.json");
            int size = is.available();
            byte [] buffer = new byte [size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return json;
    }

    public List<Court> loadCourtsfromJson() {

        List<Court> courts = new ArrayList<>();

        try {

            JSONArray array = new JSONArray(loadJsonFile());
            for(int i = 0; i < array.length(); i++ ){
                Court court = new Court();
                List<Game> games = new ArrayList<>();
                JSONObject temp = array.getJSONObject(i);
                court.setName(temp.getString("name"));
                court.setAddress(temp.getString("address"));
                court.setLatitude(Float.parseFloat(temp.getString("latitude")));
                court.setLongitude(Float.parseFloat(temp.getString("longitude")));
                // take care of the list of games from Json
                JSONArray gamesList = temp.getJSONArray("games");
                // TODO: campare the games by names and make sure they are valid games already stored in singleton.
                for(int k = 0; k < gamesList.length(); k++ ){
                    Game game = new Game();
                    JSONObject gameTemp = gamesList.getJSONObject(k);
                    game.setName(gameTemp.getString("name"));
                    game.setStartTime(gameTemp.getInt("startTime"));
                    game.setDuration(gameTemp.getInt("duration"));

                    // take care of the list of users from Json
                    JSONArray playersList = gameTemp.getJSONArray("players");
                    for (int j = 0; j < playersList.length(); ++j) {
                        User user = new User();
                        JSONObject player = playersList.getJSONObject(j);
                        user.setUsername(player.getString("username"));
                        user.setPassword(player.getString("password"));
                        game.addPlayer(user);
                    }
                    games.add(game);
                }
                court.setGames(games);
                courts.add(court);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courts;
    }

    EditText nameEntry;
    String name = "Shawn";
    LinearLayout mainBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //todo: read the json file and get the objects
        List<Court> courts = loadCourtsfromJson();
        Single.getInstance().setCourts(courts);
        mainBackground = findViewById(R.id.mainbackground);
        mainBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert mgr != null;
                mgr.hideSoftInputFromWindow(mainBackground.getWindowToken(), 0);
            }

        });
        nameEntry = (EditText) findViewById(R.id.EnterName);
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
        Button button = this.findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name != "") {
                    Toast.makeText(getBaseContext(),"Welcome "+name,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(),"Please enter a name",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onClick(View v){
        nameEntry.getText().clear();
    }
}
