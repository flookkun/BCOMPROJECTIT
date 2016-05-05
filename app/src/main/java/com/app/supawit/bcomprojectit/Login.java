package com.app.supawit.bcomprojectit;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    ConnectionSQL connectionSQL;
    EditText etuserid, edtpass;
    Button btnlogin, btnreg;
    ProgressBar pbbar;
    LocationManager locationManager;
    String mprovider;
    TextView txtLat;
    double la;
    double lo;
    private GoogleApiClient googleApiClient;
    private RequestQueue requestQueue;
    public Login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        connectionSQL = new ConnectionSQL();
        etuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        txtLat = (TextView) findViewById(R.id.longtxt);
        pbbar.setVisibility(View.GONE);
        requestQueue = Volley.newRequestQueue(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();







        /// LOGIN ///
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin = new Dologin();
                dologin.execute("");
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

        // Connect to Google API Client
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            // Disconnect Google API Client if available and connected
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Do something when connected with Google API Client

        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(googleApiClient);
        if (locationAvailability.isLocationAvailable()) {
            // Call Location Services
            LocationRequest locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(5000);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } else {
            // Do something when Location Provider not available
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        // Do something when got new current location
        lo = location.getLongitude();
        la = location.getLatitude();

        JsonObjectRequest request = new JsonObjectRequest("https://maps.googleapis.com/" +
                "maps/api/geocode/json?&latlng="+la+","+lo+"&key=AIzaSyCgKYAAAA4818hI77NWeHnzL9Y-S0rppQk",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String address = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");

                            txtLat.setText(address);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

        //txtLat.setText("Latitude : " + la + "\n" +
                //"Longitude : " + lo);

        //txtLat.setText(location.getProvider());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



    public class Dologin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = etuserid.getText().toString();
        String password = edtpass.getText().toString();
        String stcode = "";

        @Override
        protected void onPreExecute(){
            pbbar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(Login.this,r, Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Intent i = new Intent(Login.this, Main.class);
                i.putExtra("STCODE",stcode);
                startActivity(i);
                finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionSQL.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "USE[CMD-BX]" +
                                       "select * from mas_st " +
                                       "where STCODE ='" + userid +
                                       "' and STPASSWORD ='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            stcode = rs.getString("stcode");
                            z = "Login successfull";
                            isSuccess = true;
                        }
                        else
                        {
                            z = "รหัสผ่านไม่ถูกต้องกรุณาลองอีกครั้ง";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.toString();
                }
            }
            return z;
        }
    }

}

