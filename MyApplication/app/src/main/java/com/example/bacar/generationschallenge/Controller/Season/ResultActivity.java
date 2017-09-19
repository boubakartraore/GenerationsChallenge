package com.example.bacar.generationschallenge.Controller.Season;

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
import com.example.bacar.generationschallenge.Controller.Adapter.ResultAdapter;
import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.Model.Network.MatchInterface;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 27/06/2017.
 */

public class ResultActivity extends AppCompatActivity {

    private Spinner calendarDay;
    private List<String> journée = new ArrayList<String>();
    private List<Match> matchList = new ArrayList<Match>();
    private Match thisMatch;
    private RecyclerView calendarMatchs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarDay = (Spinner) findViewById(R.id.calendarDay);
        calendarMatchs = (RecyclerView) findViewById(R.id.calendarMatchs);

        //CalendarMatch();

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        calendarMatchs.setLayoutManager(myLayout);

        for (Integer i = 1; i <= 5 ; i++) {
            journée.add("Journée " + i.toString());
        }

        final ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                journée
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendarDay.setAdapter(adapter);

        calendarDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {


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

                            matchList = test.dayMatch(position + 1);

                            ResultAdapter adapter1 = new ResultAdapter(matchList);
                            calendarMatchs.setAdapter(adapter1);

                        }
                    }

                    @Override
                    public void onFailure(Call<AllMatch> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /*public void CalendarMatch() {
        thisMatch = new Match(4, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(4, new Date(), "Equipe 1", "Equipe 3", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(2, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(3, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(3, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(3, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(4, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(4, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        thisMatch.setPlayed(Boolean.TRUE);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
        thisMatch = new Match(1, new Date(), "Equipe 1", "Equipe 2", 2, 0);
        matchList.add(thisMatch);
    }*/

    private Integer getJourneeMax(List<Match> matches) {
        Integer max = 0;
        for (Integer i = 0; i < matches.size(); i++) {
            /*if (max < matches.get(i).getJournée()) {
                max = matches.get(i).getJournée();
            }*/
        }
        return max;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}