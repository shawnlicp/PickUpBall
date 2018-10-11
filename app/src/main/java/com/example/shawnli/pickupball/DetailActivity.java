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
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    protected List<Game> gamesInCurrentCourt;
    private Game clickedGame;
    private GamesDataAdapter mGameAdapter;
    private PlayersDataAdapter mPlayerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the current court object
        Court currentCourt = Single.getInstance().getCurrentCourt();

        // Get list of Games in current Court
        gamesInCurrentCourt = currentCourt.getGames();

        // TODO: Change the Label of the Activity w/ Court Name

        // TODO: Make Recycler View
//        RecyclerView gameRecyclerView = (RecyclerView) findViewById(R.id.gameRecyclerView);
//        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGameAdapter = new GamesDataAdapter(gamesInCurrentCourt);
        setupGameRecyclerView();


            // TODO: First Make Game
            // TODO: Then, make Game Items as Cards (Game Name, End Time,
            // TODO: List Players, & Join Game Button)

        // TODO: Make Create Game Button && Click Handler for Create Game Button.
        // TODO: Make Click Handler for Join Button (Go to Game Activity).
        clickedGame = Single.getInstance().getCurrentGame();


    }

    private void setupGameRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.gameRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mGameAdapter);
    }

    class GamesDataAdapter extends RecyclerView.Adapter<GamesDataAdapter.GameViewHolder> {
        private List<Game> games;

        public class GameViewHolder extends RecyclerView.ViewHolder {
            private TextView gameName, numberOfPlayers, gameTimeLeft;
            private RecyclerView playerRecyclerView;
            private Button joinGame;

            public GameViewHolder(View view) {
                super(view);
                gameName = (TextView) view.findViewById(R.id.gameName);
                numberOfPlayers = (TextView) view.findViewById(R.id.numberOfPlayers);
                gameTimeLeft = (TextView) view.findViewById(R.id.gameTimeLeft);
                playerRecyclerView = (RecyclerView) view.findViewById(R.id.playerRecyclerView);
                joinGame = (Button) view.findViewById(R.id.takeMeButton);
            }
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
            holder.numberOfPlayers.setText(String.format("Players: %s", String.valueOf(game.getPlayerSize())));
            holder.gameTimeLeft.setText(String.format("Time Left in game: %s", String.valueOf(game.getDuration())));

            mPlayerAdapter = new PlayersDataAdapter(game.getPlayers());
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
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.playerRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mPlayerAdapter);
    }

    class PlayersDataAdapter extends RecyclerView.Adapter<PlayersDataAdapter.PlayerViewHolder> {
        private List<User> players;

        public class PlayerViewHolder extends RecyclerView.ViewHolder {
            private TextView playerName;
            private ImageView playerImage;

            public PlayerViewHolder(View view) {
                super(view);
                playerName = (TextView) view.findViewById(R.id.playerName);
                playerImage = (ImageView) view.findViewById(R.id.playerImage);
            }
        }

        public PlayersDataAdapter(List<User> players) {
            this.players = players;
        }

        @Override
        public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_list_item, parent, false);

            return new PlayerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PlayerViewHolder holder, int position) {
            User player = players.get(position);
            holder.playerName.setText(player.getUsername());

        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}
