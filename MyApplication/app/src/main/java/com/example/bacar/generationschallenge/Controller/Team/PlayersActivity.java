package com.example.bacar.generationschallenge.Controller.Team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.PlayersListAdapter;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.AllPlayerInterface;
import com.example.bacar.generationschallenge.Model.Network.PlayersInterface;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.Model.Network.listPlayersRequest;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 06/06/2017.
 */

public class PlayersActivity extends AppCompatActivity {



    private TextView title;
    private RecyclerView rv;
    private User j1;
    private String recp;
    private List<User> play = new ArrayList<User>();

    private PlayersListAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        recp = getIntent().getStringExtra("team");

        title = (TextView) findViewById(R.id.playersTeam);
        rv = (RecyclerView) findViewById(R.id.playersList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title.setText("Tout les joueurs");

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(myLayout);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllPlayerInterface allPlayerInterface = retrofit.create(AllPlayerInterface.class);

        Call<ArrayList<User>> requete = allPlayerInterface.getUse();

        requete.enqueue(new Callback<ArrayList<User>>() {


            @Override
            public void onResponse(Call<ArrayList<User>> call, retrofit2.Response<ArrayList<User>> response) {

                for (int i = 0 ; i < response.body().size() ; i++) {
                    if (response.body().get(i).getTeam_id().equals(recp)) {
                        play.add(response.body().get(i));
                    }
                }

                Collections.sort(play, new User.JoueursComparator());

                myAdapter = new PlayersListAdapter(play);
                rv.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Log.d("FAILURE","failed");

            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

















    /*private TextView name;
    private RecyclerView playersList;
    private String recp;
    private ArrayList<User> play = new ArrayList<User>();
    private PlayersListAdapter playersListAdapter;
    private User j1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recp = getIntent().getStringExtra("team");

        name = (TextView) findViewById(R.id.playersTeam);
        name.setText(recp);

        playersList = (RecyclerView) findViewById(R.id.playersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        playersList.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayersInterface playersInterface = retrofit.create(PlayersInterface.class);

        Call<ArrayList<User>> requete = playersInterface.getPlayer(recp);

        requete.enqueue(new Callback<ArrayList<User>>() {


            @Override
            public void onResponse(Call<ArrayList<User>> call, retrofit2.Response<ArrayList<User>> response) {

                play = response.body();

                Collections.sort(play, new User.JoueursComparator());

                playersListAdapter = new PlayersListAdapter(play);
                playersList.setAdapter(playersListAdapter);

                Log.d("AAAAA", "4");

            }


            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Log.d("FAILURE","failed");

                Toast.makeText(PlayersActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });



        playersListAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/

}
