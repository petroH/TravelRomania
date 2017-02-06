package com.example.petro.travelromania.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.ViewGroup;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.activities.GalleryDetailActivity;
import com.example.petro.travelromania.activities.MainActivity;
import com.example.petro.travelromania.adaptors.GalleryImageAdapter;
import com.example.petro.travelromania.regiuni.GalleryImage;
import com.example.petro.travelromania.utils.GalleryApiInterface;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.Callback;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Petro on 04-Feb-17.
 */

public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private GridView mGridView;
    private GalleryImageAdapter mAdapter;

    public static GalleryFragment getInstance() {
        GalleryFragment fragment = new GalleryFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_gallery_grid, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (GridView) view.findViewById( R.id.grid );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new GalleryImageAdapter( getActivity(), 0 );
        mGridView.setAdapter( mAdapter );

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.gallery_feed ) )
                .build();

        GalleryApiInterface galleryApiInterface = restAdapter.create( GalleryApiInterface.class );

        galleryApiInterface.getStreams( new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if( galleryImages == null || galleryImages.isEmpty() )
                    return;

                for( GalleryImage image : galleryImages ) {
                    Log.e("Images", image.getThumbnail() );
                    mAdapter.add( image );
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GalleryImage image = (GalleryImage) parent.getItemAtPosition( position );
        Intent intent = new Intent( getActivity(), GalleryDetailActivity.class );
        intent.putExtra( GalleryDetailActivity.EXTRA_IMAGE, image.getImage1() );
        intent.putExtra( GalleryDetailActivity.EXTRA_CAPTION, image.getCaption() );
        startActivity( intent );
    }
}
