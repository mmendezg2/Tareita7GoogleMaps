package com.example.tareita7_googlemaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFacul implements GoogleMap.InfoWindowAdapter{

    Context contextito;
    List<FacultadesModels> infoFacul;

    public AdapterFacul(Context contextito, List<FacultadesModels> infoFacul) {
        this.contextito = contextito;
        this.infoFacul = infoFacul;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {

        return null;



    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {

        View InfoView= LayoutInflater.from(contextito).inflate(R.layout.layout_facultadesinfo,null);
        TextView txtFacultad= InfoView.findViewById(R.id.txtFacultad);
        TextView txtDecano=InfoView.findViewById(R.id.txtDecano);
        TextView txtUbicación= InfoView.findViewById(R.id.txtUbicacion);
        TextView txtlat=InfoView.findViewById(R.id.lbllat);
        TextView txtlong=InfoView.findViewById(R.id.lbllon);
        ImageView icon=InfoView.findViewById(R.id.imageView);

        try {
            for (FacultadesModels facultades : infoFacul) {
                if (facultades.getFacultad().equals(marker.getTitle())) {
                txtFacultad.setText(facultades.getFacultad());
                txtDecano.setText(facultades.getDecano());
                txtUbicación.setText(facultades.getUbicaciones());
                txtlat.setText(facultades.getLatitud());
                txtlong.setText(facultades.getLonguitud());

                Picasso.get().load(facultades.getUrlLogo().toString()).resize(200, 200).into(icon);
                }

            }

        }
        catch (Exception e){

        }

        return InfoView;
    }
}
