package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by Bacar on 28/08/2017.
 **/

public interface PlayersInterface {

    @POST("/teamPlayers.php")
    Call<ArrayList<User>> getPlayer(@Body String nom);

}
