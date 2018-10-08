package com.example.shawnli.pickupball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shawnli.pickupball.Model.Court;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                JSONObject temp = array.getJSONObject(i);
                court.setName(temp.getString("name"));
                court.setAddress(temp.getString("address"));
                court.setLatitude(Float.parseFloat(temp.getString("latitude")));
                court.setLongtitude(Float.parseFloat(temp.getString("longtitude")));
                //todo: take care of the list of users
                courts.add(court);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is for the data base
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Single.getInstance().SetDB(db);

        //todo: read the json file and get the objects
        List<Court> courts = loadCourtsfromJson();
        Single.getInstance().setCourts(courts);


        Button button = this.findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
