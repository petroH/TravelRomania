package com.example.petro.travelromania.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.example.petro.travelromania.utils.JSONParser;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private String region;
    private int success;//to determine JSON signal insert success/fail

    JSONParser jsonParser = new JSONParser();

    // Progress Dialog
    private ProgressDialog pDialog;

    // url to insert new revie (change accordingly)
    private static String url_insert_new = "http://192.168.1.4/appjson/insertnew.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";


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
        region=regionName;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //call the InsertNewIdiom thread
                new InsertNewVot().execute(editName.getText().toString(), editMail.getText().toString(), region, String.valueOf(rate.getRating()));

                Toast.makeText(RegionsDetailActivity.this, "Votul a fost salvat...", Toast.LENGTH_LONG).show();

                Toast.makeText(RegionsDetailActivity.this, "Va multumesc!", Toast.LENGTH_SHORT).show();

                Log.e( "A editat userul " ,editName.getText().toString());
                editMail.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Background Async Task to Create new Idioms
     * */
    class InsertNewVot extends AsyncTask<String, Void, String> {
        int stele=rate.getNumStars();

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegionsDetailActivity.this);
            pDialog.setMessage("Ati oferit regiunii "+region+" " + stele+ " stele.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        /**
         * Inserting the new vot
         * */
        protected String doInBackground(String... args) {
        String nume, mail, regiune, vot;

        nume=args[0];
        mail=args[1];
        regiune=args[2];
        vot=args[3];

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nume", nume));
            params.add(new BasicNameValuePair("mail", mail));
            params.add(new BasicNameValuePair("regiune", regiune));
            params.add(new BasicNameValuePair("vot", vot));

            // getting JSON Object
            // Note that create product url accepts GET method
            JSONObject json = jsonParser.makeHttpRequest(url_insert_new,
                    "GET", params);

            // check log cat from response
            Log.d("Insert ", json.toString());

            // check for success tag
            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully save new
                } else {
                    // failed to add new
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //return null;
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            final Timer time= new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (pDialog!=null && pDialog.isShowing()) {
                        // dismiss the dialog once done
                        pDialog.dismiss();
                    }
                    time.cancel();
                }
            }, 3000);

        }
    }
}
