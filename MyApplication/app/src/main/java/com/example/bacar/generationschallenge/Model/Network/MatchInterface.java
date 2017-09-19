package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Bacar on 29/08/2017.
 */

public interface MatchInterface {

    @POST("/allMatch.php")
    Call<AllMatch> getMatch();
}
