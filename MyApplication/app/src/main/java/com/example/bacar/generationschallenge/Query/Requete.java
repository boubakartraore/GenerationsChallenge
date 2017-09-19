package com.example.bacar.generationschallenge.Query;

import android.util.Log;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.ListTeamAdapter;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.Model.Network.RequestTeamInterface;
import com.example.bacar.generationschallenge.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 28/08/2017.
 */

public class Requete {

    private Match match;
    private Equipe equipe;
    private ArrayList<Equipe> allTeams = new ArrayList<Equipe>();
    private User user;
    private ListTeamAdapter teamAdapter;


    public User getUserByMail(String mail) {

        return user;

    }

    public ListTeamAdapter getAllTeams () {

        Retrofit retrofit = new Retrofit.Builder()
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

                        teamAdapter.addTeams(eq);

                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Equipe>> call, Throwable t) {

                Log.d("FAILURE", "failed");

                //Toast.makeText(, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return teamAdapter;
    }

}
