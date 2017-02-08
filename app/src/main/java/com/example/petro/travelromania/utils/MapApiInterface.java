package com.example.petro.travelromania.utils;

import com.example.petro.travelromania.regiuni.MapModel;
import com.example.petro.travelromania.regiuni.Regions;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Petro on 07-Feb-17.
 */

public interface MapApiInterface {

    @GET("/map.json")
    void getStreams(Callback<List<MapModel>> callback);
}
