package com.example.need;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataPasser {

    private HashMap<String,String>  getSingleNearByPlace(JSONObject googlePlaceJSON){
        HashMap<String,String> googlePlaceMap=new HashMap<>();
        String  NameOfPlace="-NA-";
        String  vicinity="-NA-";
        String  latitude="";
        String  longitude="";
        String  reference="";

        try {
            if(!googlePlaceJSON.isNull("nom")){
                NameOfPlace=googlePlaceJSON.getString("nom");

            }
            if(!googlePlaceJSON.isNull("vicinity")){
                vicinity=googlePlaceJSON.getString("nom");

            }
            latitude=googlePlaceJSON.getJSONObject("Geometrie").getJSONObject("location").getString("lat");
            longitude=googlePlaceJSON.getJSONObject("Geometrie").getJSONObject("location").getString("lng");
            reference=googlePlaceJSON.getString("reference");

            googlePlaceMap.put("Nom d'entreprise",NameOfPlace);
            googlePlaceMap.put("Proximité",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("long",vicinity);
            googlePlaceMap.put("reference",reference);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return googlePlaceMap;

    }

    private List<HashMap<String,String>> getAllnearbyPlaces(JSONArray jsonArray)
    {
            int compteur=jsonArray.length();
            List<HashMap<String,String>> NearByPlacesList=new ArrayList<>();
            HashMap<String,String>  NearByPlaceMap=null;

            for (int i=0;i<compteur;i++)
            {
                try {
                    NearByPlaceMap=getSingleNearByPlace((JSONObject) jsonArray.get(i));
                    NearByPlacesList.add(NearByPlaceMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                    return NearByPlacesList;
    }
    public  List<HashMap<String,String>> parse (String jSONdata){

        JSONArray jsonArray=null;

        try {
            JSONObject jsonObject=new JSONObject(jSONdata);
            jsonArray=jsonObject.getJSONArray("Résultats");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getAllnearbyPlaces(jsonArray);

    }
}
