package com.example.shawnli.pickupball;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;

import java.util.Random;

/**
 * Created by shawnli on 10/10/2018.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    final String [] status = {"on the way","playing"};

    public Random rand = new Random();
    private Game game;
    public static class PlayerViewHolder extends RecyclerView.ViewHolder{
        public TextView userNameText;
        public TextView userStatusText;
//        public TextView userTimeText;
        public PlayerViewHolder(View v){
            super(v);
            userNameText = (TextView) v.findViewById(R.id.PlayerName);
            userStatusText = (TextView) v.findViewById(R.id.PlayerStatus);
//            userTimeText = (TextView) v.findViewById(R.id.PlayerTime);
        }
    }

    public PlayerAdapter(Game game){
        this.game = game;
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row,parent,false);
        PlayerAdapter.PlayerViewHolder viewHolder =new PlayerViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.userNameText.setText(this.game.getPlayers().get(position).getUsername());

        int index = rand.nextInt(2);
        holder.userStatusText.setText(this.status[index]);

        int hour = rand.nextInt(10)+5;
        int minute = rand.nextInt(60) + 10;
        String time = String.valueOf(hour) + ":" + String.valueOf(minute) + "PM";
//        holder.userTimeText.setText(time);
    }

    @Override
    public int getItemCount() {
        return game.getPlayers().size();
    }
}
