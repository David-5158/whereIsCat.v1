package com.example.whereiscat;

import com.google.android.gms.maps.model.LatLng;

public class LocationHelper
{
    private String IdToken;  //Firebase Uid (고유 토큰정보 )
    private double Longitude;   //위도
    private double Latitude;    //경도

    public LocationHelper( double longitude, double latitude, String idToken ) {
        IdToken = idToken;
        Longitude = longitude;
        Latitude = latitude;
    }
    public LocationHelper(LatLng latLng) { }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getIdToken() { return IdToken; }

    public void setIdToken(String idToken) { IdToken = idToken; }
}
