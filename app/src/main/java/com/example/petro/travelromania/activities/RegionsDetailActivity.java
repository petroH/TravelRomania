package com.example.petro.travelromania.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Regions;
import com.squareup.picasso.Picasso;

/**
 * Created by Petro on 08-Feb-17.
 */

public class RegionsDetailActivity  extends AppCompatActivity {
    public static final String EXTRA_REGION = "extra_region";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions_details);

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        Regions region = getIntent().getExtras().getParcelable( EXTRA_REGION );

        TextView description = (TextView) findViewById( R.id.description );
        ImageView image = (ImageView) findViewById( R.id.image );

        description.setText( region.getDescription() );

        Picasso.with( this ).load( region.getImage() ).into( image );
    }
}
