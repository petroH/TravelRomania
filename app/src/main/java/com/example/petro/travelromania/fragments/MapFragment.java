package com.example.petro.travelromania.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.example.petro.travelromania.activities.RegionsDetailActivity;
import com.example.petro.travelromania.adaptors.RegionsAdaptor;
import com.example.petro.travelromania.regiuni.Regions;
import com.example.petro.travelromania.utils.RegionsApiInterface;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.MapModel;
import com.example.petro.travelromania.utils.MapApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Petro on 04-Feb-17.
 */

public class MapFragment extends SupportMapFragment {

   private RegionsAdaptor regAdaptor;
    private HashMap markerHashMap;
    private Iterator<Entry> iter;
    private CameraUpdate cu;

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regAdaptor= new RegionsAdaptor(getActivity(),0);

        getMap().getUiSettings().setCompassEnabled(true);
        getMap().getUiSettings().setRotateGesturesEnabled(false);
        getMap().getUiSettings().setTiltGesturesEnabled(true);
        getMap().getUiSettings().setZoomControlsEnabled(true);
        getMap().getUiSettings().setMyLocationButtonEnabled(true);

        getMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
        getMap().setTrafficEnabled( true );

        getMap().getUiSettings().setZoomControlsEnabled( true );

        getMap().setOnMarkerClickListener( new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                String name = marker.getTitle();

                if (name.equals("Bucovina")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Bucovina", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 0 ) );

                    startActivity( intent );
                }

                if (name.equals("Maramures")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Maramures", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 3 ) );

                    startActivity( intent );

                }
                if (name.equals("Moldova")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Moldova", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 1 ) );

                    startActivity( intent );
                }
                if (name.equals("Crisana")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Crisana", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 8 ) );

                    startActivity( intent );
                }
                if (name.equals("Transilvania")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Transilvania", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 7 ) );

                    startActivity( intent );
                }
                if (name.equals("Muntenia")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Muntenia", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 5 ) );

                    startActivity( intent );

                }
                if (name.equals("Oltenia")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Oltenia", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 4 ) );

                    startActivity( intent );
                }
                if (name.equals("Banat")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Banat", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 2 ) );

                    startActivity( intent );

                }
                if (name.equals("Dobrogea")) {
                    Toast.makeText(getActivity(), "Ati ales sejur in Dobrogea", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent( getActivity(), RegionsDetailActivity.class );
                    intent.putExtra( RegionsDetailActivity.EXTRA_REGION, regAdaptor.getItem( 6 ) );

                    startActivity( intent );

                }
                return true;
            }

        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.map_feed ) )
                .build();

        MapApiInterface pinsApiInterface = adapter.create( MapApiInterface.class );

        pinsApiInterface.getStreams( new Callback<List<MapModel>>() {
            @Override
            public void success(List<MapModel> pins, Response response) {
                for( MapModel pin : pins ) {
                   MarkerOptions options= new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    Marker marker= getMap().addMarker(options);
                    //adding markers to hashmap
                    addMarkedToHashMap(pin, marker);
                }
                zoomAnimateLevelToFitMarkers(120);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.regiuni_feed ) )
                .build();

        RegionsApiInterface regApiInterface = restAdapter.create( RegionsApiInterface.class );

        regApiInterface.getStreams( new Callback<List<Regions>>() {
            @Override
            public void success(List<Regions> regiuni, Response response) {
                if( regiuni == null || regiuni.isEmpty() )
                    return;

                for( Regions reg : regiuni ) {
                    regAdaptor.add(reg);
                }

//updates automaticaly the data
                regAdaptor.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e( "Regiuni", "Retrofit error " + error.getMessage() );
            }
        });
    }

    //sets up the hashmap
    public void setMarkerHashMap(){
        if(markerHashMap== null){
            markerHashMap= new HashMap();
        }
    }

    //adds all the markers on the map
    public void addMarkedToHashMap(MapModel pin, Marker marker){
        setMarkerHashMap();
        markerHashMap.put(pin,marker);
    }

    //this is method to help us fit the Markers into specific bounds for camera position
    public void zoomAnimateLevelToFitMarkers(int padding) {
        LatLngBounds.Builder b = new LatLngBounds.Builder();
        iter = markerHashMap.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry mEntry = (Map.Entry) iter.next();
            MapModel key = (MapModel) mEntry.getKey();
            LatLng ll = new LatLng(key.getLatitude(), key.getLongitude());
            b.include(ll);
        }
        LatLngBounds bounds = b.build();

        // Change the padding as per needed
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        getMap().animateCamera(cu);
    }


}
