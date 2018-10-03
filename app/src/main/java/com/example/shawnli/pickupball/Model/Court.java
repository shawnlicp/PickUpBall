package com.example.shawnli.pickupball.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawnli on 10/2/2018.
 */

public class Court {
    //Location
    List<User> players = null;
    String name;
    String address;

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

    Court(){
        players = new ArrayList<>();
    }

    public List<User> getPlayers(){
        return players;
    }

    public int getPlayerSize(){
        return players.size();
    }
}
