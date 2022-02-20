package com.example.fa_rishabhsingh_c078019_android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.fa_rishabhsingh_c078019_android.databinding.ActivitySelectPlaceBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class SELECT_PLACE extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySelectPlaceBinding binding;
    List<Address> listGeoCoder;
    private  String currentLoaction;
    private  static  final  int LOCATION_PERMISSION_CODE = 101;
    LocationManager locationManager;
    LocationListener locationListener;
    protected  Double latitude , longitude;
    EditText searchText;
    ImageButton searchButton;
    Button slctLocButton;
    databaseHelperClass dbHelper = new databaseHelperClass(SELECT_PLACE.this);
    List<placesModelClass> recievedAll = new ArrayList<>();

    String Address1;
    int editId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySelectPlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        editId = getIntent().getIntExtra("sendingId",0);

        searchText = findViewById(R.id.etxtSearch);
        searchButton = findViewById(R.id.ibtnSearch);
        slctLocButton = findViewById(R.id.btSelectLoc);

        requestLocationPermission();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchText != null){
                geocoding(searchText.getText().toString());
                Toast.makeText(SELECT_PLACE.this,"All fields Manadtory",Toast.LENGTH_SHORT).show(); }
            }
        });
        slctLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(SELECT_PLACE.this,"location "+longitude.toString(), Toast.LENGTH_SHORT).show();

                if(editId != 0){

                    recievedAll = dbHelper.allDataReturn();

                    placesModelClass updateClassInput = new placesModelClass(recievedAll.get(editId).getId(),reverseGeocoding(latitude,longitude),false,latitude,longitude,recievedAll.get(editId).getDate());
                    dbHelper.updateRow(updateClassInput);

                }
                else{
                    dbHelper.addData(new placesModelClass(1,reverseGeocoding(latitude,longitude),false,latitude,longitude,"jkskd"));
                }



                Intent detailSCreen = new Intent(SELECT_PLACE.this,MainActivity.class);


                startActivity(detailSCreen);

            }
        });


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED)
        {   mMap.setMyLocationEnabled(true);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
            latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();


            reverseGeocoding(latitude,longitude);

        }



    }

    private  boolean isLocationPermissionGranted() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED)
        { return true;}
        else { return false; }

    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);

    }
    // USING FUNCTION FOR GETTING ADDRESS
    private  String reverseGeocoding(Double latitude,Double longitude){
        List<Address> geocodeMatches = null;


        try {
            geocodeMatches =
                    new Geocoder(this).getFromLocation(latitude, longitude, 1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!geocodeMatches.isEmpty())
        {
            Address1 = geocodeMatches.get(0).getAddressLine(0);


        }
return  Address1;
    }

    private void geocoding( String address){



        List<Address> geocodeMatches = null;
        try {
            geocodeMatches =
                    new Geocoder(this).getFromLocationName(
                            address, 1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!geocodeMatches.isEmpty())
        {

            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
            LatLng sydney = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        }


    }

}