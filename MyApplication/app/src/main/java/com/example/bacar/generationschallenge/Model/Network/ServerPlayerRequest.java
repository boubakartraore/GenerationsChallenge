package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.Joueurs;

/**
 * Created by Bacar on 12/07/2017.
 */

public class ServerPlayerRequest {

    private String operation;
    private Joueurs joueur;

    public void setOperation (String operation) {
        this.operation = operation;
    }

    public void setJoueur (Joueurs joueur) {
        this.joueur = joueur;
    }

}
