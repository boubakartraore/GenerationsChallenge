package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.TouteEquipe;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Bacar on 23/07/2017.
 */

public interface RequestTeamInterface {

    @POST("/allTeams.php")
    Call<ArrayList<Equipe>> team_operation(/*@Body ServerTeamRequest request*/);

}