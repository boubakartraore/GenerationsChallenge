package com.example.bacar.generationschallenge.Controller.Season;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.CalendarAdapter;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bacar on 27/06/2017.
 */

public class TeamCalendarActivity extends AppCompatActivity {

    private Spinner calendarDay;
    private List<String> spin = new ArrayList<String>();
    private List<Match> matchList = new ArrayList<Match>();
    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Match thisMatch;
    private Equipe equipe;
    private RecyclerView calendarMatchs;
    private SharedPreferences sharedPreferences;
    private int is_team_button;
    private String rcv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        sharedPreferences = this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        is_team_button = sharedPreferences.getInt("Calendar Team", 0);

        rcv = getIntent().getStringExtra("team");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarDay = (Spinner) findViewById(R.id.calendarDay);
        calendarMatchs = (RecyclerView) findViewById(R.id.calendarMatchs);

        CalendarMatch();
        TeamMatch();

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        calendarMatchs.setLayoutManager(myLayout);


        final ArrayAdapter adapter = new ArrayAdapter(
                TeamCalendarActivity.this,
                android.R.layout.simple_spinner_item,
                spin
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendarDay.setAdapter(adapter);


        if (is_team_button == 1) {
            calendarDay.setSelection(getIndex(calendarDay, rcv));
        }

        calendarDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<Match> curr = new ArrayList<Match>();
                Match thisMatch = new Match();
                String thisTeam = maListe.get(position).getName();
                for (int i = 0 ; i < matchList.size() ; i++) {
                    thisMatch = matchList.get(i);
                    Toast.makeText(TeamCalendarActivity.this, maListe.get(i).getName(), Toast.LENGTH_LONG).show();
                    if ( thisMatch.getEquipeExterieur().equalsIgnoreCase(thisTeam) ||
                            thisMatch.getEquipeDomicile().equalsIgnoreCase(thisTeam)) {
                        curr.add(thisMatch);
                    }
                }

                CalendarAdapter adapter1 = new CalendarAdapter(curr);
                calendarMatchs.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void TeamMatch() {
        equipe = new Equipe("Equipe 1", "Test", "Vert", 2,0,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 2", "Test", "Vert", 4,0,4,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 3", "Test", "Vert", 2,0,3,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 4", "Test", "Vert", 2,3,11,0,0);
        maListe.add(equipe);

        for (int i = 0 ; i < maListe.size() ; i++) {
            spin.add(maListe.get(i).getName());
        }
    }

    public void CalendarMatch() {
        thisMatch = new Match(4, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(2, new Date(), "Equipe 2", "Equipe 3", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(3, new Date(), "Equipe 3", "Equipe 4", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 4", "Equipe 1", 2, 0);
        matchList.add(thisMatch);
    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
