package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.Equipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bacar on 10/08/2017.
 */

public class JSONTeamResponse {

    @SerializedName("teams")
    @Expose
    private Equipe[] teams;

    public Equipe[] getTeams() {
        return teams;
    }

}
