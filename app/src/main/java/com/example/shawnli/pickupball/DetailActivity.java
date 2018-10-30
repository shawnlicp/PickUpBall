package com.example.shawnli.pickupball;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
import com.example.shawnli.pickupball.Model.User;


import java.util.List;

public class DetailActivity extends AppCompatActivity {

    protected List<Game> gamesInCurrentCourt;
    private Game clickedGame;
    private GamesDataAdapter mGameAdapter;
    private PlayerAdapter mPlayerAdapter;
    private Button mStartGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the current court object
        Court currentCourt = Single.getInstance().getCurrentCourt();
        TextView courtName, courtAddress;
        courtName = (TextView) findViewById(R.id.courtName);
        courtAddress = (TextView) findViewById(R.id.courtAddress);
        mStartGameButton = (Button) findViewById(R.id.createGame);

        courtName.setText(currentCourt.getName());
        courtAddress.setText(currentCourt.getAddress());

        // Get list of Games in current Court
        gamesInCurrentCourt = currentCourt.getGames();

        mGameAdapter = new GamesDataAdapter(gamesInCurrentCourt);
        setupGameRecyclerView();

        clickedGame = Single.getInstance().getCurrentGame();

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //make a toast
                Toast.makeText(getBaseContext(),"Switch to Start Game Activiry",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), StartGameActivity.class);
                startActivity(intent);
            }
        });


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
                playerRecyclerView = (RecyclerView) view.findViewById(R.id.playerRecyclerView1);
                joinGame = (Button) view.findViewById(R.id.takeMeButton);

                playerRecyclerView.setVisibility(View.GONE);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (playerRecyclerView.getVisibility() == View.GONE){
                            playerRecyclerView.setVisibility(View.VISIBLE);
                        }
                        else if(playerRecyclerView.getVisibility() == View.VISIBLE){
                            playerRecyclerView.setVisibility(View.GONE);
                        }
                    }
                });
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

            mPlayerAdapter = new PlayerAdapter(game);
            setupPlayerRecyclerView(holder.playerRecyclerView);

            String peoplePlaying = Integer.toString(mPlayerAdapter.getPlayersPlaying());
            String peopleEnRoute = Integer.toString(mPlayerAdapter.getPlayersEnRoute());
            holder.playersPlaying.setText(String.format("People Playing: %s", peoplePlaying));
            holder.playersOnWay.setText(String.format("People On The Way: %s", peopleEnRoute));

            holder.joinGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Single.getInstance().setCurrentGame(game);
                    game.addPlayer(Single.getInstance().getCurrentUser());
                    mPlayerAdapter.setPlayersPlaying(mPlayerAdapter.getPlayersEnRoute() + 1);

                    show_dialog();  // This creates the pop-up Dialog.

                }
            });



        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }

    public void show_dialog() {
        FragmentManager fm = getFragmentManager();
        DialogFragment newFragment = new popUpDialog();
        newFragment.show(fm, "abc");
    }

    private void setupPlayerRecyclerView(RecyclerView recyclerView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mPlayerAdapter);
    }

}
