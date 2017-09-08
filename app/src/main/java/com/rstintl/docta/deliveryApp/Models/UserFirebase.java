package com.rstintl.docta.deliveryApp.Models;

/**
 * Created by Ashish on 07-09-2017.
 */

public class UserFirebase {
    String name;
    String status;
    double latitude;
    double longitude;
    public UserFirebase(double latitute, double longitude, String name, String status){
        this.latitude = latitute;
        this.longitude = longitude;
        this.name = name;
        this.status = status;
    }
    public UserFirebase(){

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public double getLatitute(){
        return latitude;
    }
    public void setLatitute(double latitute){
        this.latitude = latitute;
    }
    public double getLongitude(){
        return longitude;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
}
