package com.example.need;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object,String,String> {

    private   String googleplacedata, url;
    private GoogleMap mMap;
    @Override
    protected String doInBackground(Object... objects)
    {
            mMap=(GoogleMap) objects[0];
            url= (String) objects[1];
            DownloadUrl downloadUrl=new DownloadUrl();
        try {
            googleplacedata=downloadUrl.ReadTheUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplacedata;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearByPlacesList=null;
        DataPasser dataPasser=new DataPasser();
        nearByPlacesList=dataPasser.parse(s);
        DisplayNearByPlaces(nearByPlacesList);
    }

    private void DisplayNearByPlaces(List<HashMap<String,String>> nearByPlacesList){

        for(int i=0;i<nearByPlacesList.size();i++)
        {
            MarkerOptions markerOptions=new MarkerOptions();
            HashMap<String,String> googleNearByPlace=nearByPlacesList.get(i);
            String nameOfPlace=googleNearByPlace.get("place_name");
            String vicinity=googleNearByPlace.get("vicinity");
            double lat=Double.parseDouble(googleNearByPlace.get("lat"));
            double lng=Double.parseDouble(googleNearByPlace.get("lng"));

            LatLng latLng=new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace +" : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10 ));

        }
    }
}
