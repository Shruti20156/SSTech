package com.shruti.school;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.shruti.school.databinding.ActivityGoogleMapBinding;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGoogleMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Mountreach Solution Pvt Ltd and move the camera
        LatLng mountreachoffice = new LatLng(20.899250999236973, 77.76378468710735);
        mMap.addMarker(new MarkerOptions().position(mountreachoffice)
                .title("Marker in Mountreach Solution Pvt Ltd")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.com)));

        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(mountreachoffice, 16), 5000, null);
        mMap.addCircle(new CircleOptions().strokeColor(Color.BLUE)
                .fillColor(Color.argb(70,50,150,50))
                .radius(150).strokeWidth(5).center(mountreachoffice));
        // Add a marker in Government Polytechnic Jalan
        LatLng gpj = new LatLng(19.869003464062448, 75.82571997456091);
        mMap.addMarker(new MarkerOptions().position(gpj)
                .title("Marker in Government Polytechnic Jalana")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gpjc)));
        mMap.addPolyline(new PolylineOptions()
                .add(mountreachoffice, gpj)
                .color(Color.RED)
                .width(5));
    }
}




