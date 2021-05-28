package com.example.whereiscat;

public class UserLocation {

    public String idToken;  //Firebase Uid (고유 토큰정보 )
    public Double latitude;
    public Double longitude;

    public UserLocation(){}

    public String getIdToken() { return idToken; }

    public void setIdToken(String idToken) { this.idToken = idToken; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
