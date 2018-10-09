package com.example.shawnli.pickupball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
