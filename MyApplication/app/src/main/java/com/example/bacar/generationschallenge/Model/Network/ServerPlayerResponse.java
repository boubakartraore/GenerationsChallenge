package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.User;

/**
 * Created by Bacar on 12/07/2017.
 */

public class ServerPlayerResponse {

    private String result;
    private String message;
    private User user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
