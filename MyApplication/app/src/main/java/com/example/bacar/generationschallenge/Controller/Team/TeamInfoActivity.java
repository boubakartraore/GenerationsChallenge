package com.example.bacar.generationschallenge.Controller.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;

import java.util.Date;


/**
 * Created by Bacar on 05/06/2017.
 */

public class TeamInfoActivity extends AppCompatActivity {

    /* ********************** */

    //private Date curDate;

    /* ********************** */

    private String rcv;

    private Equipe current;
    private Match currentMAtch;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcv = getIntent().getStringExtra("team");

        teamImage = (ImageView) findViewById(R.id.team_image);
        teamNAme = (TextView) findViewById(R.id.team_name);
        teamCaptain = (TextView) findViewById(R.id.team_captain);
        nextMatchTeam1 = (TextView) findViewById(R.id.nextMatchTeam1);
        nextMatchTeam2 = (TextView) findViewById(R.id.nextMatchTeam2);
        nextMatchScore1 = (TextView) findViewById(R.id.nextMatchScore1);
        nextMatchScore2 = (TextView) findViewById(R.id.nextMatchScore2);
        teamCalButton = (Button) findViewById(R.id.teamCalButton);
        playersButton = (Button) findViewById(R.id.playersButton);
        nextMatch = (TextView) findViewById(R.id.nextMatch);
        journee = (TextView) findViewById(R.id.journee);
        teamNextDay = (TextView) findViewById(R.id.teamNextDay);
        teamNextMatchDate = (TextView) findViewById(R.id.teamNextMatchDate);

        //curDate = new Date();

        current = new Equipe("Equipe 1", "Player 1", "Blue", 0,0,0,0,0);
        currentMAtch = new Match(0, new Date(), "Equipe 1", "Equipe 2", 2, 0);

        String dateMatch = new myDate().myDate(currentMAtch.getDate());
        String heureMatch = new myDate().myHeure(currentMAtch.getDate());

        teamImage.setImageResource(R.drawable.team);
        teamNAme.setText(rcv);
        teamCaptain.setText(current.getCaptain());
        nextMatchTeam1.setText(currentMAtch.getEquipeDomicile());
        nextMatchTeam2.setText(currentMAtch.getEquipeExterieur());
        nextMatchScore1.setText(currentMAtch.getScoreEquipeDomicile().toString());
        nextMatchScore2.setText(currentMAtch.getScoreEquipeExterieur().toString());
        teamNextDay.setText(currentMAtch.getJournée().toString());
        teamNextMatchDate.setText("Le " + dateMatch + " à " + heureMatch);


        playersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TeamInfoActivity.this, PlayersActivity.class);
                i.putExtra("team", rcv);
                startActivity(i);
            }
        });

        teamCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
