package com.rstintl.docta.deliveryApp.Activities;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;

import com.cs.googlemaproute.DrawRoute;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rstintl.docta.deliveryApp.Models.DeliveryBoyModel;
import com.rstintl.docta.deliveryApp.Models.UserFirebase;
import com.rstintl.docta.deliveryApp.R;
import com.google.android.gms.location.LocationListener;

import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.rstintl.docta.deliveryApp.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    SharedPreferences sharedPreferences;
    MarkerOptions markerOptions, markerOptions2;
    List<DeliveryBoyModel> deliveryBoys = new ArrayList<>();
    String date;
    double lat1, lang1, lat2, lang2;
    TextView tvTimeDistance;
    String finalDuration, finalDistance, driverId;
    DatabaseReference myRef;
    FirebaseDatabase database;
    FloatingActionButton fab;
    List<UserFirebase>users = new ArrayList<>();
    Marker currentLocationMarker;
    boolean firstPass = true, zoomed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("location");
        fab = (FloatingActionButton) findViewById(R.id.fab);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (getIntent().getExtras() != null) {
            lat1 = getIntent().getDoubleExtra("lat1", 0.0);
            lat2 = getIntent().getDoubleExtra("lat2", 0.0);
            lang1 = getIntent().getDoubleExtra("lang1", 0.0);
            lang2 = getIntent().getDoubleExtra("lang2", 0.0);
            driverId = getIntent().getStringExtra("driver_contact");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawRoute.getInstance(new DrawRoute.onDrawRoute() {
                    @Override
                    public void afterDraw(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray routes = jsonObject.getJSONArray("routes");
                            JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
                            JSONObject duration = legs.getJSONObject(0).getJSONObject("duration");
                            finalDuration = duration.getString("text");
                            JSONObject distance = legs.getJSONObject(0).getJSONObject("distance");
                            finalDistance = distance.getString("text");
            /*JSONObject duration = legs.getJSONObject(2);
            String mDuration = duration.getString("text");*/
                            Log.d("Duration", finalDuration);
                            tvTimeDistance.setText("("+ finalDistance + ") " + finalDuration);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, MapsActivity.this).setFromLatLong(lat1,lang1)
                        .setToLatLong(lat2,lang2).setGmapAndKey("AIzaSyAXJL08SLtzX1hWhi_hTeBVsUQT2f49F1s",mMap).run();
            }
        });
        Log.d("Lat_lang", lat1 + "&" + lang1 + "&" + lat2 + "&" + lang2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        tvTimeDistance = (TextView) findViewById(R.id.tv_time_distance);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_map));
        mMap.setOnMarkerClickListener(this);
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.delivery_truck);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 84, 84, false);
        currentLocationMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat1, lang1)).title("Current Location").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        /*mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat1, lang1), 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);*/
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.child(driverId).exists()){
                    UserFirebase userFirebase = dataSnapshot.child(driverId).getValue(UserFirebase.class);
                    if(mMap != null){
                        Log.d("user",userFirebase.getName() + userFirebase.getStatus() + "&" + userFirebase.getLongitude() + " " + userFirebase.getLatitute());
                        LatLng latLngMarker = new LatLng(userFirebase.getLatitute(), userFirebase.getLongitude());
                        lat1 = userFirebase.getLatitute();
                        lang1 = userFirebase.getLongitude();
                        animateMarker(currentLocationMarker, latLngMarker, false);
                        // Move the camera instantly to hamburg with a zoom of 15.
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngMarker, 15));
                        zoomed = true;

                        if (!zoomed) {

                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            zoomed = true;
                        }
                        // Zoom in, animating the camera.
                    }
                    else{
                        Log.d("Size List", "Some`thing Went wrong");
                    }
                    //users.add(userFirebase);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("resp", s + dataSnapshot.child(driverId));
                if(dataSnapshot.child(driverId).exists()){
                    UserFirebase userFirebase = dataSnapshot.child(driverId).getValue(UserFirebase.class);
                    if(mMap != null){
                        Log.d("user",userFirebase.getName() + userFirebase.getStatus() + "&" + userFirebase.getLongitude() + " " + userFirebase.getLatitute());
                        LatLng latLngMarker = new LatLng(userFirebase.getLatitute(), userFirebase.getLongitude());
                        lat1 = userFirebase.getLatitute();
                        lang1 = userFirebase.getLongitude();
                        animateMarker(currentLocationMarker, latLngMarker, false);
                        // Move the camera instantly to hamburg with a zoom of 15.
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngMarker, 15));
                        zoomed = true;

                        if (!zoomed) {

                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            zoomed = true;
                        }
                        // Zoom in, animating the camera.
                    }
                    else{
                        Log.d("Size List", "Some`thing Went wrong");
                    }
                    //users.add(userFirebase);
                }

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

        /*DrawRoute.getInstance(this,MapsActivity.this).setFromLatLong(lat1,lang2)
                .setToLatLong(lat2,lang2).setGmapAndKey("AIzaSyAXJL08SLtzX1hWhi_hTeBVsUQT2f49F1s",mMap).setZoomLevel((float) 15).run();*/
        /*DeliveryBoyModel deliveryBoyModel = new DeliveryBoyModel("Ashish","Assigned",28.4540583,77.0937073);
        DeliveryBoyModel deliveryBoyModel1 = new DeliveryBoyModel("Vinay","Not Assigned",28.4941690,77.6427130);
        DeliveryBoyModel deliveryBoyModel2 = new DeliveryBoyModel("Deepak","Not Assigned",28.4342510,77.3237180);
        DeliveryBoyModel deliveryBoyModel3 = new DeliveryBoyModel("Akash","Out for delivery",28.4043182,77.9747654);
        DeliveryBoyModel deliveryBoyModel4 = new DeliveryBoyModel("Amit","Assigned",28.4744784,77.3057055);
        deliveryBoys.add(deliveryBoyModel);
        deliveryBoys.add(deliveryBoyModel1);
        deliveryBoys.add(deliveryBoyModel2);
        deliveryBoys.add(deliveryBoyModel3);
        deliveryBoys.add(deliveryBoyModel4);
        for(int i=0; i<deliveryBoys.size();i++){
            LatLng latLngMarker = new LatLng(deliveryBoys.get(i).getLat(), deliveryBoys.get(i).getLang());
            markerOptions = new MarkerOptions();
            markerOptions.position(latLngMarker);
            markerOptions.title(deliveryBoys.get(i).getName()+"(Status:"+ deliveryBoys.get(i).getStatus()+ ")");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.delivery_boy_final));
            Marker marker = mMap.addMarker(markerOptions);
            marker.setTag(i);
            marker.setDraggable(true);
        }*/

        //LatLng latLngMarker = new LatLng(lat1, lat1);

        LatLng latLngMarker2 = new LatLng(lat2, lang2);
        Marker currentLocationMarker2 = mMap.addMarker(new MarkerOptions().position(latLngMarker2).title("DropOff Location"));



    }






    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("assigned_to",deliveryBoys.get(tag).getName());
        startActivity(intent);*/

        return false;
    }

    /*@Override
    public void afterDraw(String result) {
            Log.d("Result", result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray routes = jsonObject.getJSONArray("routes");
            JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
            JSONObject duration = legs.getJSONObject(0).getJSONObject("duration");
            finalDuration = duration.getString("text");
            JSONObject distance = legs.getJSONObject(0).getJSONObject("distance");
            finalDistance = distance.getString("text");
            *//*JSONObject duration = legs.getJSONObject(2);
            String mDuration = duration.getString("text");*//*
            Log.d("Duration", finalDuration);
            tvTimeDistance.setText("("+ finalDistance + ") " + finalDuration);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        /*DrawRoute.getInstance(this,MapsActivity.this).setFromLatLong(toPosition.latitude,toPosition.longitude)
                .setToLatLong(lat2,lang2).setGmapAndKey("AIzaSyAXJL08SLtzX1hWhi_hTeBVsUQT2f49F1s",mMap).run();*/
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 2000;
        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {

                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } /*else {
                    if (hideMarker) {
                        marker.setVisible(true);
                    } else {
                        marker.setVisible(true);
                    }
                }*/
            }
        });
    }



}
