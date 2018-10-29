package com.example.shawnli.pickupball;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;

import java.util.List;
import java.util.Random;

/**
 * Created by shawnli on 10/10/2018.
 */

public class CourtAdapter extends RecyclerView.Adapter<CourtAdapter.CourtViewHolder> {

    public static class CourtViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView courtName;
        public TextView courtAddress;
        public CourtViewHolder(View v){
            super(v);
            courtName = v.findViewById(R.id.court_card_name);
            courtAddress = v.findViewById(R.id.court_card_address);
            cardView = v.findViewById(R.id.court_card_view);
        }
    }
    List<Court> courts;
    public CourtAdapter(List<Court> courts){
        this.courts = courts;

    }


    @NonNull
    @Override
    public CourtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.court_row,parent,false);
        CourtViewHolder viewHolder = new CourtViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourtViewHolder holder, int position) {
        final Court court = courts.get(position);
        holder.courtName.setText(court.getName());
        holder.courtAddress.setText(court.getAddress());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Single.getInstance().setCurrentCourt(court);
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courts.size();
    }
}
