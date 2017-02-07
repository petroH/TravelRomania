package com.example.petro.travelromania.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Map;
import com.example.petro.travelromania.utils.MapApiInterface;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Petro on 04-Feb-17.
 */

public class MapFragment extends SupportMapFragment {

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.map_feed ) )
                .build();

        MapApiInterface pinsApiInterface = adapter.create( MapApiInterface.class );

        pinsApiInterface.getStreams( new Callback<List<Map>>() {
            @Override
            public void success(List<Map> pins, Response response) {
                for( Map pin : pins ) {
                    Log.e("Zoo", pin.getName() );
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
