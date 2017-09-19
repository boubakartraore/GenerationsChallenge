package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.User;

/**
 * Created by Bacar on 12/07/2017.
 */

public class ServerPlayerRequest {

    private String operation;
    private User user;

    public void setOperation (String operation) {
        this.operation = operation;
    }

    public void setUser (User user) {
        this.user = user;
    }

}
