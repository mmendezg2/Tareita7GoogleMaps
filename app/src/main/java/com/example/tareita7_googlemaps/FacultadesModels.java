package com.example.tareita7_googlemaps;

public class FacultadesModels {
    String facultad;
    String decano;
    String urlLogo;
    String latitud;
    String longuitud;
    String ubicaciones;

    public FacultadesModels(String facultad, String decano, String urlLogo, String latitud, String longuitud, String ubicaciones) {
        this.facultad = facultad;
        this.decano = decano;
        this.urlLogo = urlLogo;
        this.latitud = latitud;
        this.longuitud = longuitud;
        this.ubicaciones = ubicaciones;
    }


    public FacultadesModels() {
    }


    public String getFacultad() {
        return facultad;
    }

    public String getDecano() {
        return decano;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLonguitud() {
        return longuitud;
    }

    public String getUbicaciones() {
        return ubicaciones;
    }


    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLonguitud(String longuitud) {
        this.longuitud = longuitud;
    }

    public void setUbicaciones(String ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
}
