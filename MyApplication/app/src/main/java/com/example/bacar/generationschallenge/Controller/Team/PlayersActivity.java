package com.example.bacar.generationschallenge.Controller.Team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Controller.Adapter.PlayersListAdapter;
import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bacar on 06/06/2017.
 */

public class PlayersActivity extends AppCompatActivity {

    private TextView name;
    private RecyclerView playersList;
    private String recp;
    private List<Joueurs> play = new ArrayList<Joueurs>();
    private PlayersListAdapter playersListAdapter;
    private Joueurs j1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recp = getIntent().getStringExtra("team");

        name = (TextView) findViewById(R.id.playersTeam);
        name.setText(recp);


        playersList = (RecyclerView) findViewById(R.id.playersList);

        playersListAdapter = new PlayersListAdapter(play);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        playersList.setLayoutManager(mLayoutManager);
        playersList.setAdapter(playersListAdapter);

        j1 = new Joueurs(1, "Player 1", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(2, "Player 2", "Nom", "mail@mail.com", "tester", "Chef du projet", 1, "0123445566", 10);
        play.add(j1);
        j1 = new Joueurs(3, "Player 3", "Nom", "mail@mail.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(4, "Player 4", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 34);
        play.add(j1);
        j1 = new Joueurs(5, "Player 5", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);

        playersListAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
