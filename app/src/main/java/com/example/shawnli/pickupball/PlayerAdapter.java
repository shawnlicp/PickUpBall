package com.example.shawnli.pickupball;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import java.util.Random;

/**
 * Created by shawnli on 10/10/2018.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    final String [] status = {"En route","Playing"};

    public Random rand = new Random();
    private Game game;
    private int playersPlaying;
    private int playersEnRoute;

    public int getPlayersPlaying() {
        return playersPlaying;
    }

    public void setPlayersPlaying(int playersPlaying) {
        this.playersPlaying = playersPlaying;
    }

    public int getPlayersEnRoute() {
        return playersEnRoute;
    }

    public void setPlayersEnRoute(int playerEnRoute) {
        this.playersEnRoute = playerEnRoute;
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{
        public TextView userNameText;
        public TextView userStatusText;
        public PlayerViewHolder(View v){
            super(v);
            userNameText = (TextView) v.findViewById(R.id.PlayerName);
            userStatusText = (TextView) v.findViewById(R.id.PlayerStatus);
        }
    }

    public PlayerAdapter(Game game){
        this.game = game;
        for (User player: game.getPlayers()) {
            if(player.getStatus()== -1){
                int index = rand.nextInt(2);
                player.setStatus(index);


            }
            if(player.getStatus() == 0){
                playersEnRoute++;
            }
            else if(player.getStatus() == 1){
                playersPlaying++;
            }
        };
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row,parent,false);

        return new PlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.userNameText.setText(this.game.getPlayers().get(position).getUsername());
        holder.userStatusText.setText(this.status[this.game.getPlayers().get(position).getStatus()]);
    }

    @Override
    public int getItemCount() {
        return game.getPlayers().size();
    }
}
