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

    public static final String EXTRA_IMAGE1 = "extra_image1";
    public static final String EXTRA_IMAGE2 = "extra_image2";
    public static final String EXTRA_IMAGE3 = "extra_image3";
    public static final String EXTRA_IMAGE4 = "extra_image4";
    public static final String EXTRA_CAPTION = "extra_caption";

    private TextView mCaptionTextView;
    private ImageView mImageView1, mImageView2, mImageView3,mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        mCaptionTextView = (TextView) findViewById( R.id.caption );
        mImageView1=(ImageView) findViewById( R.id.image1 );
        mImageView2=(ImageView) findViewById( R.id.image2 );
        mImageView3=(ImageView) findViewById( R.id.image3 );
        mImageView4=(ImageView) findViewById( R.id.image4 );

        if( getIntent() != null && getIntent().getExtras() != null ) {
            if( getIntent().getExtras().containsKey( EXTRA_IMAGE1 ) ) {
                Picasso.with(this).load( getIntent().getExtras().getString( EXTRA_IMAGE1) ).into( mImageView1);
            }
            if( getIntent().getExtras().containsKey( EXTRA_IMAGE2 ) ) {
                Picasso.with(this).load( getIntent().getExtras().getString( EXTRA_IMAGE2) ).into( mImageView2);
            }
            if( getIntent().getExtras().containsKey( EXTRA_IMAGE3 ) ) {
                Picasso.with(this).load( getIntent().getExtras().getString( EXTRA_IMAGE3) ).into( mImageView3);
            }
            if( getIntent().getExtras().containsKey( EXTRA_IMAGE4 ) ) {
                Picasso.with(this).load( getIntent().getExtras().getString( EXTRA_IMAGE4) ).into( mImageView4);
            }

            if( getIntent().getExtras().containsKey( EXTRA_CAPTION ) ) {
                mCaptionTextView.setText( getIntent().getExtras().getString( EXTRA_CAPTION ) );
            }
        }
    }
}
