package com.example.need;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.need.LoginActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener

{


    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest  locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code=99;
    double latitude,longitude;
    private int ProximityRadius = 10000;






    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M ){
            checkUserLocationPermession();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        Button logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.logout: FirebaseAuth.getInstance().signOut(); startActivity(new Intent(getApplicationContext(),LoginActivity.class));finish();
                        break;
                }
            }
        });
    }

 public void onClick(View v){
        String  hospital="Hoptial",school="Ecole",restaurant="Restaurant";
        Object transferData[]=new  Object[2];
        GetNearbyPlaces getNearbyPlaces=new GetNearbyPlaces();


        switch (v.getId()){
            case  R.id.recherche_entreprise:
                EditText addressfield=(EditText) findViewById(R.id.location_search);
                String address= addressfield.getText().toString();
                List<Address> addressList=null;
                MarkerOptions usermarkerOptions=new MarkerOptions() ;

                if (!TextUtils.isEmpty(address)){
                    Geocoder geocoder=new Geocoder(this);
                    try {
                        addressList=geocoder.getFromLocationName(address,6);
                        if (addressList!=null){
                            for (int i=0;i<addressList.size();i++){

                                Address userAddress=addressList.get(i);
                                LatLng latLng=new LatLng(userAddress.getLatitude(),userAddress.getLongitude());
                                usermarkerOptions.position(latLng);
                                usermarkerOptions.title(address);
                                usermarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                                mMap.addMarker(usermarkerOptions);

                                currentUserLocationMarker=mMap.addMarker(usermarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10 ));

                            }

                        }
                        else {
                            Toast.makeText(this,"Emplacement introuvable",Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                            e.printStackTrace();
                    }

                }else {
                    Toast.makeText(this,"Veuillez écrire n'importe quelle entreprise nom",Toast.LENGTH_SHORT).show();
                }
                break;


        }
 }

    /* *
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


   private String getUrl(double latitude, double longitude,String nearByPlace){


        StringBuilder googleUrl=new StringBuilder("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?");
        googleUrl.append("location="+latitude+","+longitude);
        googleUrl.append("&radius="+ProximityRadius);
        googleUrl.append("&type="+nearByPlace);
        googleUrl.append("&sensor=true");
        googleUrl.append("&key"+"AIzaSyBIukixEg9qdcJNXH61hA9AYUn4eqPbkQo");

       Log.d("MapsActivity","url = "+googleUrl.toString());

 return googleUrl.toString();
   }

        public boolean checkUserLocationPermession (){
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
                }
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode)
            {
                case Request_User_Location_Code:
                    if (grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                            if (googleApiClient==null){
                                buildGoogleApiClient();
                            }
                        }
                        mMap.setMyLocationEnabled(true);
                    }else {
                        Toast.makeText(this,"Permission refusée..",Toast.LENGTH_SHORT).show();
                    }
            }   return;
    }

    protected synchronized void buildGoogleApiClient(){
        googleApiClient=new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
            lastLocation=location;
            if (currentUserLocationMarker!=null){
                currentUserLocationMarker.remove();
            }
            LatLng latLng=  new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Votre emplacement actuel");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

            currentUserLocationMarker=mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            if(googleApiClient!=null){
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
            }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void Logout( ){

        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(MapsActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

        }
}