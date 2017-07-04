package com.example.bacar.generationschallenge.Controller.Classement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Controller.Adapter.PlayersListAdapter;
import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bacar on 15/06/2017.
 */

public class AllButeursClassementActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView rv;
    private Joueurs j1;
    private List<Joueurs> play = new ArrayList<Joueurs>();

    private PlayersListAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        title = (TextView) findViewById(R.id.playersTeam);
        rv = (RecyclerView) findViewById(R.id.playersList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /* **************** */

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
        j1 = new Joueurs(6, "Player 6", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 1);
        play.add(j1);
        j1 = new Joueurs(7, "Player 7", "Nom", "mail@mail.com", "tester", "Chef du projet", 1, "0123445566", 12);
        play.add(j1);
        j1 = new Joueurs(8, "Player 8", "Nom", "mail@mail.com", "tester", "Milieu", 1, "0123445566", 200);
        play.add(j1);
        j1 = new Joueurs(9, "Player 9", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 348);
        play.add(j1);
        j1 = new Joueurs(10, "Player 10", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 8);
        play.add(j1);
        j1 = new Joueurs(11, "Player 11", "Nom", "mail@mail.com", "tester", "Attaquant", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(12, "Player 12", "Nom", "mail@mail.com", "tester", "Chef du projet", 1, "0123445566", 3);
        play.add(j1);
        j1 = new Joueurs(13, "Player 13", "Nom", "mail@mail.com", "tester", "Milieu", 1, "0123445566", 2);
        play.add(j1);
        j1 = new Joueurs(14, "Player 14", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 32);
        play.add(j1);
        j1 = new Joueurs(15, "Player 15", "Nom", "mail@mail.com", "tester", "Defenseur", 1, "0123445566", 0);
        play.add(j1);

        Collections.sort(play, new Joueurs.JoueursComparator());

        /* *************** */



        title.setText(R.string.allScorers);

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(myLayout);

        myAdapter = new PlayersListAdapter(play);
        rv.setAdapter(myAdapter);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
