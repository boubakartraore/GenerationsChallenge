package com.example.bacar.generationschallenge.Controller.Team;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bacar.generationschallenge.Controller.Season.TeamCalendarActivity;
import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.Model.Network.MatchInterface;
import com.example.bacar.generationschallenge.R;
import com.squareup.picasso.Picasso;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Bacar on 05/06/2017.
 */

public class TeamInfoActivity extends AppCompatActivity {

    /* ********************** */

    //private Date curDate;

    /* ********************** */

    private Equipe rcv;

    private Equipe current;
    private Match currentMAtch;

    private Match nextGame;

    private TextView teamNAme;
    private TextView teamCaptain;
    private TextView nextMatchScore1;
    private TextView nextMatchTeam1;
    private TextView nextMatchTeam2;
    private TextView nextMatchScore2;
    private Button teamCalButton;
    private Button playersButton;
    private TextView nextMatch;
    private TextView journee;
    private TextView teamNextDay;
    private TextView teamNextMatchDate;
    private ImageView teamImage;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcv = (Equipe) getIntent().getSerializableExtra("team");

        teamImage = (ImageView) findViewById(R.id.team_image);
        teamNAme = (TextView) findViewById(R.id.team_name);
        teamCaptain = (TextView) findViewById(R.id.team_captain);
        nextMatchTeam1 = (TextView) findViewById(R.id.nextMatchTeam1);
        nextMatchTeam2 = (TextView) findViewById(R.id.nextMatchTeam2);
        teamCalButton = (Button) findViewById(R.id.teamCalButton);
        playersButton = (Button) findViewById(R.id.playersButton);
        nextMatch = (TextView) findViewById(R.id.nextMatch);
        journee = (TextView) findViewById(R.id.journee);
        teamNextDay = (TextView) findViewById(R.id.teamNextDay);
        teamNextMatchDate = (TextView) findViewById(R.id.teamNextMatchDate);

        //curDate = new Date();

        //current = new Equipe("Equipe 1", "Player 1", "Blue", 0,0,0,0,0);
        //currentMAtch = new Match(0, new Date(), "Equipe 1", "Equipe 2", 2, 0);

        //String dateMatch = new myDate().myDate(currentMAtch.getDate());
        //String heureMatch = new myDate().myHeure(currentMAtch.getDate());

        //teamImage.setImageResource(R.drawable.team);

        Picasso.with(getApplicationContext())
                .load(rcv.getPhoto())
                .placeholder(R.drawable.team)
                .into(teamImage);

        teamNAme.setText(rcv.getName());
        teamCaptain.setText(rcv.getCaptain());
        //nextMatchTeam1.setText(currentMAtch.getEquipeDomicile());
        //nextMatchTeam2.setText(currentMAtch.getEquipeExterieur());
        //nextMatchScore1.setText(currentMAtch.getScoreEquipeDomicile());
        //nextMatchScore2.setText(currentMAtch.getScoreEquipeExterieur());
        //teamNextDay.setText(currentMAtch.getJournée());
        //teamNextMatchDate.setText("Le " + dateMatch + " à " + heureMatch);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MatchInterface requestTeamInterface = retrofit.create(MatchInterface.class);

        final Call<AllMatch> requete = requestTeamInterface.getMatch();

        requete.enqueue(new Callback<AllMatch>() {

            @Override
            public void onResponse(Call<AllMatch> call, retrofit2.Response<AllMatch> response) {

                if (response.isSuccessful()) {

                    AllMatch test = response.body();

                    nextGame = test.getNextMatch(rcv.getName());

                    teamNextDay.setText(nextGame.getJournée());
                    teamNextMatchDate.setText("Le " + nextGame.getDate());
                    nextMatchTeam1.setText(nextGame.getEquipeDomicile());
                    nextMatchTeam2.setText(nextGame.getEquipeExterieur());
                }
            }

            @Override
            public void onFailure(Call<AllMatch> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


        playersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeamInfoActivity.this, PlayersActivity.class);
                i.putExtra("team", rcv.getName());
                startActivity(i);
            }
        });

        teamCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeamInfoActivity.this, TeamCalendarActivity.class);
                sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Calendar Team", 1);
                editor.apply();
                i.putExtra("team", rcv.getName());
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
