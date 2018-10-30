package com.example.shawnli.pickupball.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawnli on 10/2/2018.
 */

public class Game {
    //Location
    List<User> players = null;
    String name;
    private int startTime;
    private int duration;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(){
        players = new ArrayList<>();
    }

    public Boolean addPlayer(User player){
        for(User p : players){
            if(p.getUsername()==player.getUsername()){
                return false;
            }
        }
        players.add(player);
        return true;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public List<User> getPlayers(){
        return players;
    }

    public int getPlayerSize(){
        return players.size();
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayersOnWay() {
        List<User> playersList = getPlayers();
        for (User player:playersList) {

        }
        return 0;
    }
}
