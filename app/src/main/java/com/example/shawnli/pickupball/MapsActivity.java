package com.example.shawnli.pickupball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnli.pickupball.Model.Court;
import com.example.shawnli.pickupball.Model.Game;
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

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private CardView mCard;
    private TextView nameView;
    private TextView addressView;
    private ImageView imageView;

    private Map<Marker, Court> markerCourtMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        TextView listView = toolbar.findViewById(R.id.listText);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        mCard = findViewById(R.id.card_view);
        nameView = findViewById(R.id.CardName);
        addressView = findViewById(R.id.CardAddress);
        imageView = findViewById(R.id.right_icon);
        imageView.setVisibility(View.INVISIBLE);

        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Single.getInstance().getCurrentCourt() == null){
                    //make a toast
                    Toast.makeText(getBaseContext(),"Please select a marker",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                    startActivity(intent);
                }
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
    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        List<Court> courts = Single.getInstance().getCourts();

        for( Court court : courts){
            LatLng location = new LatLng(court.getLatitude(), court.getLongitude());
            int countGame = court.getGames().size();
            int countPlayer = 0;
            for(Game game: court.getGames()){
                countPlayer += game.getPlayerSize();
            }
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon);
            BitmapDescriptor test = BitmapDescriptorFactory.fromBitmap(resizeMapIcons("icon",150,150));
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(court.getName())
                            .snippet("There are "+countGame+" games with" + countPlayer + " players playing right now")
                            .icon(test)
                            .flat(true));
            markerCourtMap.put(marker,court);
            marker.showInfoWindow();
            mMap.setOnInfoWindowClickListener(this);
        }
        // Add a marker in Sydney and move the camera

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Court court = markerCourtMap.get(marker);
                Single.getInstance().setCurrentCourt(court);
                nameView.setText(court.getName());
                addressView.setText(court.getAddress());

                Single.getInstance().setCurrentCourt(court);
                imageView.setVisibility(View.VISIBLE);
                return false;
            }
        });


        LatLng provo = new LatLng(40.233845, -111.658531);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((provo),12.0f));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(Single.getInstance().getCurrentCourt() == null){
            //make a toast
            Toast.makeText(getBaseContext(),"Please select a marker",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getBaseContext(), DetailActivity.class);
            startActivity(intent);
        }
    }
}
