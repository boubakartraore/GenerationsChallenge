package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.Joueurs;

/**
 * Created by Bacar on 12/07/2017.
 */

public class ServerPlayerResponse {

    private String result;
    private String message;
    private Joueurs joueur;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Joueurs getUser() {
        return joueur;
    }
}
