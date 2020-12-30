package com.example.shoping.Fragments;

import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoping.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = view.findViewById(R.id.mapsView);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }


        return view;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 15.5 ,23.0 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 17.5 ,25.5 )).title("Marka").snippet("Eygpt"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 15.12 ,24.5 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 12.8   ,23.0 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 15.5 ,23.0 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 17.5 ,25.5 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 15.12 ,24.5 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 12.8   ,23.0 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 15.5 ,66.0 )).title("Marka").snippet("UN"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 50.5 ,25.5 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 1.12 ,24.5 )).title("Marka").snippet("Gaza"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng( 120.8   ,68.0 )).title("Marka").snippet("US"));


    }


}
