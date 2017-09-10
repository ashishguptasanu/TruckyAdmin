package com.rstintl.docta.deliveryApp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rstintl.docta.deliveryApp.Models.UserFirebase;
import com.rstintl.docta.deliveryApp.R;

import java.util.ArrayList;
import java.util.List;

public class AllDriverActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference myRef;
    FirebaseDatabase database;
    List<UserFirebase> users = new ArrayList<>();
    Marker currentLocationMarker;
    List<String> usersList = new ArrayList<>();
    TextView tvOnlineDrivers;
    FloatingActionButton fab;
    boolean markerDraw = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_driver);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvOnlineDrivers = (TextView)findViewById(R.id.tv_online_driver);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllDriverActivity.class);
                startActivity(intent);
            }
        });
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("location");

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ManageActivity.class);
        startActivity(intent);
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
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_map));
//        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void getUpdates(DataSnapshot dataSnapshot) {
        users.clear();
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            Log.d("Chenged", String.valueOf(dataSnapshot1));
            UserFirebase userFirebase = new UserFirebase(Double.parseDouble(dataSnapshot1.child("latitude").getValue().toString()),Double.parseDouble(dataSnapshot1.child("longitude").getValue().toString()),dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("status").getValue().toString());
            users.add(userFirebase);
        }
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.delivery_truck);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 84, 84, false);
        for(int k=0; k<users.size(); k++){
            currentLocationMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(users.get(k).getLatitute(), users.get(k).getLongitude())).title(users.get(k).getName() + "(" + users.get(k).getStatus()  + ")").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(users.get(0).getLatitute(), users.get(0).getLongitude()), 9));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(9), 2000, null);
        tvOnlineDrivers.setText(users.size() + " Drivers Nearby");
    }

    /*@Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }*/
}
