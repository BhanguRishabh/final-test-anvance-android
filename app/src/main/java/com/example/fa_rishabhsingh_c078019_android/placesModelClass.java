package com.example.fa_rishabhsingh_c078019_android;

public class placesModelClass {
    private String place;
    private  Boolean visit;
    private  Double latitude;
    private  Double  longitude;
    private  String  date;
    private  int id;


    public placesModelClass(int id,String place, Boolean visit, Double latitude, Double longitude, String date) {
        this.place = place;
        this.visit = visit;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getVisit() {
        return visit;
    }

    public void setVisit(Boolean visit) {
        this.visit = visit;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
