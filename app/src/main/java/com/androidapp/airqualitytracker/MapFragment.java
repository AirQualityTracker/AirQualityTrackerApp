package com.androidapp.airqualitytracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * This class is used to show the map of the app
 */
//return current location of the user

public class MapFragment extends Fragment{
    //textView1 for lat
    //textView2 for Long
    TextView textView1, textView2, textView3, textView4, textView5; //textviews will deleted, they are used just to confirm is working
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //get the view from the xml
        View mView = inflater.inflate(R.layout.map_fragment, container, false);
        textView1 = mView.findViewById(R.id.textview1_location);
        textView2 = mView.findViewById(R.id.textview2_location);
        textView3 = mView.findViewById(R.id.textview3_location);
        textView4 = mView.findViewById(R.id.textview4_location);
        textView5 = mView.findViewById(R.id.textview5_location);
        button = mView.findViewById(R.id.button_location);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
    //add listener to the button that asks for the user location
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext() , android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getlocation();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        return mView;
    }

    /*
    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.AQT_MAP);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247, -74.044502)).title("Statue of Liberty").snippet("Hello everyone"));

        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }

     */
   //This method is used to get the locations of the user
    private void getlocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize location
                Location location = task.getResult();
                if (location != null) {

                    try {
                        //Initialize Geocoder
                        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
                        //Initialize Address List
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLatitude(), 1);

                        textView1.setText(Html.fromHtml("<font color = '#6200EE'><b><Longitude : </b><br></font>" + addresses.get(0).getLongitude()));
                        textView2.setText(Html.fromHtml("<font color = '#6200EE'><b><Latitude : </b><br></font>" + addresses.get(0).getLatitude()));
                        textView3.setText(addresses.get(0).getCountryName());
                        textView4.setText(addresses.get(0).getLocality());
                        textView5.setText(addresses.get(0).getAdminArea());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else
                    textView1.setText("Null");

            }
        });
    }
}

