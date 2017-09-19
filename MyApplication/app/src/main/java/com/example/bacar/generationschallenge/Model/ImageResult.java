package com.example.bacar.generationschallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bacar on 14/09/2017.
 */

public class ImageResult {

    @SerializedName("result")
    @Expose
    private String Result;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
