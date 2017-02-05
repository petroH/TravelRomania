package com.example.petro.travelromania.utils;

import com.example.petro.travelromania.regiuni.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Petro on 05-Feb-17.
 */

public interface GalleryApiInterface {

    @GET("/gallery.json")
    void getStreams(Callback<List<GalleryImage>> callback);
}
