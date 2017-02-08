package com.example.petro.travelromania.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petro.travelromania.R;
import com.example.petro.travelromania.regiuni.Regions;
import com.squareup.picasso.Picasso;

/**
 * Created by Petro on 08-Feb-17.
 */

public class RegionsDetailActivity  extends AppCompatActivity {
    public static final String EXTRA_REGION = "extra_region";

    private RatingBar rate;
    private TextView txtRate;
    private Button submit;
    private EditText editName;
    private EditText editMail;

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

        addListenerOnRatingBar();
        addListenerButton(region.getName());
    }

    public void addListenerOnRatingBar(){
        rate =(RatingBar) findViewById(R.id.ratingBar);
        txtRate = (TextView) findViewById(R.id.txtRate);

        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtRate.setText(String.valueOf(rate.getRating()));
            }
        });
    }

    public void addListenerButton(final String regionName){
        rate =(RatingBar) findViewById(R.id.ratingBar);
        submit = (Button) findViewById(R.id.submit);
        editName=(EditText) findViewById(R.id.editNume);
        editMail= (EditText) findViewById(R.id.editMail);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegionsDetailActivity.this, "Va multumesc!", Toast.LENGTH_SHORT).show();

                Log.e( "A edit " ,editName.getText().toString());
                editMail.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
            }
        });
    }

}
