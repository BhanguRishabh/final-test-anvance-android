package com.example.fa_rishabhsingh_c078019_android;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fa_rishabhsingh_c078019_android.databinding.ActivitySeePlaceBinding;

import java.util.ArrayList;
import java.util.List;

public class SEE_PLACE extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySeePlaceBinding binding;
    databaseHelperClass dbHelper = new databaseHelperClass(SEE_PLACE.this);
    List<placesModelClass> recivedList = new ArrayList<>();
    int recivedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySeePlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         recivedIndex = getIntent().getIntExtra("pos",0);


        recivedList = dbHelper.allDataReturn();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LatLng sydney = new LatLng(recivedList.get(recivedIndex).getLatitude(), recivedList.get(recivedIndex).getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title(recivedList.get(recivedIndex).getPlace().toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}