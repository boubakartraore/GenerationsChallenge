package com.example.bacar.generationschallenge.Controller.Season;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.CalendarAdapter;
import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.Model.Network.MatchInterface;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 10/06/2017.
 */


public class TeamCalendarActivity extends AppCompatActivity {

    private Spinner calendarDay;
    private List<String> journée = new ArrayList<String>();
    private List<Match> matchList = new ArrayList<Match>();
    private Match thisMatch;
    private RecyclerView calendarMatchs;
    private ListTeamAdapter teamAdapter;
    private SharedPreferences sharedPreferences;
    private int countTeam = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarDay = (Spinner) findViewById(R.id.calendarDay);
        calendarMatchs = (RecyclerView) findViewById(R.id.calendarMatchs);


        remplirSpiner();
       // CalendarMatch();

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        calendarMatchs.setLayoutManager(myLayout);

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
                /*List<Match> curr = new ArrayList<Match>();
                Match thisMatch = new Match();
                for (int i = 0 ; i < matchList.size() ; i++) {
                    thisMatch = matchList.get(i);
                    *//*if (thisMatch.getJournée() == position + 1){
                        curr.add(thisMatch);
                    }*//*
                }*/


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

                            sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            String pos = sharedPreferences.getString("equipe " + position, "");

                            matchList = test.teamMatch(journée.get(position));

                            CalendarAdapter adapter1 = new CalendarAdapter(matchList);
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

    private Integer getJourneeMax(List<Match> matches) {
        Integer max = 0;
        for (Integer i = 0; i < matches.size(); i++) {
            /*if (max < matches.get(i).getJournée()) {
                max = matches.get(i).getJournée();
            }*/
        }
        return max;
    }

    public void remplirSpiner () {

        journée.add("AFC");
        journée.add("Dortmund");
        journée.add("New Team");
        journée.add("All Black");
        journée.add("Les Anciens");

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestTeamInterface requestTeamInterface = retrofit.create(RequestTeamInterface.class);

        //Toast.makeText(getActivity(), "Lancement de la recherche", Toast.LENGTH_LONG).show();

        final Call<ArrayList<Equipe>> requete = requestTeamInterface.team_operation();

        requete.enqueue(new Callback<ArrayList<Equipe>>() {

            @Override
            public void onResponse(Call<ArrayList<Equipe>> call, retrofit2.Response<ArrayList<Equipe>> response) {

                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().size(); i++) {

                        Equipe eq = response.body().get(i);

                        journée.add(eq.getName());
                        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("equipe " + i,eq.getName());
                        editor.apply();
                        countTeam++;



                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Equipe>> call, Throwable t) {

                Log.d("FAILURE","failed");

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

        for (int z = 0; z < countTeam; z++) {
            String ne = sharedPreferences.getString("equipe "+z, "");
            Log.d("Shared", ne);
            journée.add(ne);
        }*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
