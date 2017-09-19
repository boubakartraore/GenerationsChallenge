package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Bacar on 10/09/2017.
 */

public interface AllPlayerInterface {

    @POST("/allPlayers.php")
    Call<ArrayList<User>> getUse();

}
