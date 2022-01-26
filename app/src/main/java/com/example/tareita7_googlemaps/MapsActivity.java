package com.example.tareita7_googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tareita7_googlemaps.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<FacultadesModels> listaFacultades = new ArrayList<>();
    private GoogleMap mMap;
    private ActivityMapsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        APIvolley();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng uteq= new LatLng(-1.0118506915092784, -79.46892724061738);
        CameraUpdate Cupdate= CameraUpdateFactory.newLatLngZoom(uteq,17 );
        mMap.moveCamera(Cupdate);


    }


    public void APIvolley() {

        RequestQueue RequestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlJSON = "https://my-json-server.typicode.com/mmendezg2/FacultadesUniJsonsito/db";
        JsonObjectRequest JsonOR = new JsonObjectRequest(Request.Method.GET,
                urlJSON, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonFacul = response.getJSONArray("Facultades");
                    for (int i = 0; i < jsonFacul.length(); i++) {

                        JSONObject info= new JSONObject(jsonFacul.get(i).toString());
                        String facultad, decano, logo, lat, longi, ubicacion;
                        facultad=info.getString("Facultad");
                        decano=info.getString("Decano");
                        logo=info.getString("logo");
                        lat=info.getString("Latitud");
                        longi=info.getString("Longuitud");
                        ubicacion=info.getString("ubicacion");

                        FacultadesModels Facultades = new FacultadesModels(facultad,decano, logo, lat, longi,ubicacion);


                        listaFacultades.add(Facultades);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                cargarMarcadoresFacul();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        RequestQueue.add(JsonOR);


    }

    public void cargarMarcadoresFacul(){
        for(FacultadesModels facultades : listaFacultades) {

            LatLng LatyLong= new LatLng(Float.parseFloat(facultades.getLatitud())
                    ,Float.parseFloat(facultades.getLonguitud()));

            mMap.addMarker(new MarkerOptions()
                    .position(LatyLong)
                    .title(facultades.getFacultad()));
        }

        mMap.setInfoWindowAdapter(new AdapterFacul(MapsActivity.this,listaFacultades));


    }


}