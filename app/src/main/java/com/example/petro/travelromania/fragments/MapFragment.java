package com.example.petro.travelromania.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.petro.travelromania.activities.RegionsDetailActivity;
import com.example.petro.travelromania.adaptors.RegionsAdaptor;
import com.example.petro.travelromania.regiuni.Regions;
import com.example.petro.travelromania.utils.RegionsApiInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Map;
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

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regAdaptor= new RegionsAdaptor(getActivity(),0);

        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( 44.416667, 26.1 ) )
                .zoom( 16f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        getMap().animateCamera(CameraUpdateFactory.newCameraPosition( position ), null );
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

        pinsApiInterface.getStreams( new Callback<List<Map>>() {
            @Override
            public void success(List<Map> pins, Response response) {
                for( Map pin : pins ) {
                   MarkerOptions options= new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                    getMap().addMarker(options);
                }
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
}
