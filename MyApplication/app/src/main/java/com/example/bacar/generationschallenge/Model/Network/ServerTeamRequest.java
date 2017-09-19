package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.User;

import java.util.List;

/**
 * Created by Bacar on 23/07/2017.
 */

public class ServerTeamRequest {

    private String team_operation;

    public void setOperation (String operation) {
        this.team_operation = operation;
    }

}