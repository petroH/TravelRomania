package com.example.petro.travelromania.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Regions;
import com.example.petro.travelromania.utils.RegionsApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Petro on 04-Feb-17.
 */
public class RegiuniFragment extends ListFragment {
    public static RegiuniFragment getInstance(){
        RegiuniFragment fragment= new RegiuniFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.regiuni_feed ) )
                .build();

        RegionsApiInterface regApiInterface = restAdapter.create( RegionsApiInterface.class );

        regApiInterface.getStreams( new Callback<List<Regions>>() {
            @Override
            public void success(List<Regions> regiuni, Response response) {
                if( regiuni == null || regiuni.isEmpty() )
                    return;

                for( Regions reg : regiuni ) {
                    Log.e( "Regiuni",reg.getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e( "Regiuni", "Retrofit error " + error.getMessage() );
            }
        });
    }
}
