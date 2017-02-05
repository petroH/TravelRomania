package com.example.petro.travelromania.fragments;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Petro on 04-Feb-17.
 */

public class MapFragment extends SupportMapFragment {

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }
}
