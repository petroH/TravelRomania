package com.example.petro.travelromania.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petro.travelromania.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petro on 06-Feb-17.
 */

public class GalleryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "extra_image1";
    public static final String EXTRA_IMAGE2 = "extra_image2";
    public static final String EXTRA_IMAGE3 = "extra_image3";
    public static final String EXTRA_IMAGE4 = "extra_image4";
    public static final String EXTRA_CAPTION = "extra_caption";

    private TextView mCaptionTextView;
    private ImageView mImageView, mImageView2, mImageView3,mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        mCaptionTextView = (TextView) findViewById( R.id.caption );
        mImageView=(ImageView) findViewById( R.id.image1 );
       // mImageView2=(ImageView) findViewById( R.id.image2 );
       // mImageView3=(ImageView) findViewById( R.id.image3 );
       // mImageView4=(ImageView) findViewById( R.id.image4 );

        if( getIntent() != null && getIntent().getExtras() != null ) {
            if( getIntent().getExtras().containsKey( EXTRA_IMAGE ) ) {
                Picasso.with(this).load( getIntent().getExtras().getString( EXTRA_IMAGE) ).into( mImageView);
            }

            if( getIntent().getExtras().containsKey( EXTRA_CAPTION ) ) {
                mCaptionTextView.setText( getIntent().getExtras().getString( EXTRA_CAPTION ) );
            }
        }
    }
}
