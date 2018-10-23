package com.example.shawnli.pickupball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    protected List<Game> gamesInCurrentCourt;
    private Game clickedGame;
    private GamesDataAdapter mGameAdapter;
    private PlayerAdapter mPlayerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the current court object
        Court currentCourt = Single.getInstance().getCurrentCourt();
        TextView courtName, courtAddress;
        courtName = (TextView) findViewById(R.id.courtName);
        courtAddress = (TextView) findViewById(R.id.courtAddress);

        courtName.setText(currentCourt.getName());
        courtAddress.setText(currentCourt.getAddress());

        // Get list of Games in current Court
        gamesInCurrentCourt = currentCourt.getGames();

        mGameAdapter = new GamesDataAdapter(gamesInCurrentCourt);
        setupGameRecyclerView();

        clickedGame = Single.getInstance().getCurrentGame();


    }

    private void setupGameRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.gameRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mGameAdapter);
    }

    class GamesDataAdapter extends RecyclerView.Adapter<GamesDataAdapter.GameViewHolder> {

        private List<Game> games;


        public class GameViewHolder extends RecyclerView.ViewHolder {
            private TextView gameName, playersPlaying, playersOnWay;
            private RecyclerView playerRecyclerView;
            private Button joinGame;

            public GameViewHolder(View view) {
                super(view);
                gameName = (TextView) view.findViewById(R.id.gameName);
                playersPlaying = (TextView) view.findViewById(R.id.numberOfPlayersPlaying);
                playersOnWay = (TextView) view.findViewById(R.id.numberOfPlayersOnWay);
                playerRecyclerView = (RecyclerView) view.findViewById(R.id.playerRecyclerView);
                joinGame = (Button) view.findViewById(R.id.takeMeButton);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Expand Card View", Toast.LENGTH_SHORT).show();
                        playerRecyclerView.setVisibility(View.VISIBLE);
                        playerRecyclerView.setMinimumHeight(20 * mPlayerAdapter.getItemCount());
                    }
                });
            }

//            @Override
//            public void onClick(View view) {
////                Toast.makeText(getApplicationContext(), "Expand Card View", Toast.LENGTH_SHORT).show();
//                // TODO: display player list and expand view
//                Toast.makeText(getApplicationContext(), "PRV is visible: " + Integer.toString(playerRecyclerView.getVisibility()), Toast.LENGTH_SHORT).show();
//                playerRecyclerView.setVisibility(View.VISIBLE);
//                playerRecyclerView.setMinimumHeight(20 * mPlayerAdapter.getItemCount());
//
////                Toast.makeText(getApplicationContext(), "PRV is NOW visible: " + Integer.toString(playerRecyclerView.getVisibility()), Toast.LENGTH_SHORT).show();
//                // Expandable Recycler View example.
//            }
        }

        public GamesDataAdapter(List<Game> games) {
            this.games = games;
        }

        @Override
        public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.game_list_item, parent, false);

            return new GameViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GameViewHolder holder, int position) {
            final Game game = games.get(position);
            holder.gameName.setText(game.getName());
            holder.playersPlaying.setText(String.format("People Playing: %s", "IDK")); // TODO: Get value of people playing.
            holder.playersOnWay.setText(String.format("People On The Way: %s", "IDK")); // TODO: Get value of people on way.

            mPlayerAdapter = new PlayerAdapter(game);
            setupPlayerRecyclerView(holder.playerRecyclerView);

            holder.joinGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Single.getInstance().setCurrentGame(game);
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    startActivity(intent);
                }
            });



        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }

    private void setupPlayerRecyclerView(RecyclerView recyclerView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mPlayerAdapter);
    }

}
