package com.example.shawnli.pickupball;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.shawnli.pickupball.Model.Court;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CardView mCard;
    private TextView nameView;
    private TextView addressView;

    private Map<Marker, Court> markerCourtMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mCard = findViewById(R.id.card_view);
        nameView = findViewById(R.id.CardName);
        addressView = findViewById(R.id.CardAddress);

        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                startActivity(intent);
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
        mMap.getUiSettings().setMapToolbarEnabled(false);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        List<Court> courts = Single.getInstance().getCourts();

        for( Court court : courts){
            LatLng location = new LatLng(court.getLatitude(), court.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(court.getName())
                            .snippet("There are 0 players playing right now")
                            .icon(BitmapDescriptorFactory.fromAsset("icon.png")));
            markerCourtMap.put(marker,court);
            marker.showInfoWindow();
        }
        // Add a marker in Sydney and move the camera

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Court court = markerCourtMap.get(marker);
                nameView.setText(court.getName());
                addressView.setText(court.getAddress());
                return false;
            }
        });


        LatLng provo = new LatLng(40.233845, -111.658531);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((provo),12.0f));
    }
}
