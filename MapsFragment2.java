package com.example.monogenicdiseases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment2 extends Fragment {
    private Button isl,lhr,reset,browse;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng Islamabad = new LatLng(33.69833934017662, 73.03322693227781);
//            googleMap.addMarker(new MarkerOptions().position(Islamabad).title("Marker in Sydney"));
            LatLng Shifa = new LatLng(33.67577289138797, 73.06716614015772);
            LatLng Marham = new LatLng(31.502104672865503, 74.32543388064718);
            LatLng instacare = new LatLng(31.50125956310959, 74.4351871266115);
            LatLng healthwire = new LatLng(31.51149342472463, 74.35091776501876);

            googleMap.addMarker(new MarkerOptions().position(Shifa).title("Shifa International Hospital"));
            googleMap.addMarker(new MarkerOptions().position(Marham).title("Marham"));
            googleMap.addMarker(new MarkerOptions().position(instacare).title("instacare"));
            googleMap.addMarker(new MarkerOptions().position(healthwire).title("healthwire"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Islamabad));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Islamabad,5));

//            isl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Islamabad,15));
//                }
//            });
//
//            lhr.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    LatLng Lahore = new LatLng(31.54351470275946, 74.3618147798444);
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lahore));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lahore,5));
//                }
//            });
//
//            reset.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(Islamabad));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Islamabad,5));
//                }
//            });
//
//            browse.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri CFuri= Uri.parse("https://www.google.com/maps");
//                    startActivity(new Intent(Intent.ACTION_VIEW,CFuri));
//                }
//            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        isl=(Button) view.findViewById(R.id.isl);
//        lhr=(Button) view.findViewById(R.id.lhr);
//        reset=(Button) view.findViewById(R.id.reset);
//        browse=(Button) view.findViewById(R.id.browsem);
        return inflater.inflate(R.layout.fragment_maps2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}