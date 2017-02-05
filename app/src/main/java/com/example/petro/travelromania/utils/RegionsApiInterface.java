package com.example.petro.travelromania.utils;

import com.example.petro.travelromania.regiuni.Regions;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Petro on 04-Feb-17.
 */

public interface  RegionsApiInterface{

    @GET("/regiuni.json")
    void getStreams(Callback<List<Regions>> callback);
}