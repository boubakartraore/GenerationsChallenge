package com.example.bacar.generationschallenge.Model.Network;

import com.example.bacar.generationschallenge.Model.ImageResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Bacar on 14/09/2017.
 */

public interface UploadImageInterface {

    @Multipart
    @POST("upload.php")
    Call<ImageResult> uploadImage(@Part MultipartBody.Part file);

}
