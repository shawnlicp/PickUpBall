package com.example.shawnli.pickupball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shawnli.pickupball.Model.Game;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public String loadJsonFile(){
        String json = null;
        try {
            InputStream is = getBaseContext().getAssets().open("games.json");
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

    public List<Game> loadGamesfromJson() {

        List<Game> games = new ArrayList<>();
        try {

            JSONArray array = new JSONArray(loadJsonFile());
            for(int i = 0; i < array.length(); i++ ){
                Game game = new Game();
                JSONObject temp = array.getJSONObject(i);
                game.setName(temp.getString("name"));
                game.setStartTime(Integer.parseInt(temp.getString("startTime")));
                game.setDuration(Integer.parseInt(temp.getString("duration")));
                //todo: take care of the list of users
                games.add(game);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return games;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
