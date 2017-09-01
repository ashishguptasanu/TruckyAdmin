package com.example.ashish.googlemaps.Models;

/**
 * Created by Ashish on 31-08-2017.
 */

public class DeliveryBoyModel {
    String name;
    String status;
    double lat;
    double lang;
    public DeliveryBoyModel(String name, String status, double lat, double lang){
        this.name = name;
        this.status = status;
        this.lat = lat;
        this.lang = lang;
    }
    public String getName(){return name;}
    public String getStatus(){return status;}
    public double getLat(){ return lat;}
    public double getLang(){return lang;}
}
