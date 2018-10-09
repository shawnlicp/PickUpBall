package com.example.shawnli.pickupball.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawnli on 10/2/2018.
 */

public class Court {
    //Location
    List<Game> games = null;
    String name;
    String address;
    float latitude;
    float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Court(){
        games = new ArrayList<>();
    }

    public List<Game> getGames(){
        return games;
    }

    public int getPlayerSize(){
        return games.size();
    }
}
