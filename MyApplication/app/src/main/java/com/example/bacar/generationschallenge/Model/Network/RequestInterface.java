package com.example.bacar.generationschallenge.Model.Network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Bacar on 12/07/2017.
 */

public interface RequestInterface {

    @POST("/")
    Call<ServerPlayerResponse> operation(@Body ServerPlayerRequest request);

}