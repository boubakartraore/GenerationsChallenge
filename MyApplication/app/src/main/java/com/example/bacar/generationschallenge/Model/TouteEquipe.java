package com.example.bacar.generationschallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bacar on 24/08/2017.
 */

public class TouteEquipe {

    @SerializedName("teams")
    @Expose
    private List<Equipe> teams;

    public List<Equipe> getEquipe () {

        return teams;

    }

    public void setTouteEquipe (List<Equipe> eq) {

        this.teams = eq;
    }

}
