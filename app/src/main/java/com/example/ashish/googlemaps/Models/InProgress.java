package com.example.ashish.googlemaps.Models;

/**
 * Created by Ashish on 01-09-2017.
 */

public class InProgress {
    public String deliveryTime, deliveryDate, id, contact, status, name;
    public double lat1, lang1, lat2, lang2;
    public InProgress(String id, String status, String deliveryDate, String deliveryTime, Double lat1, Double lang1, Double lat2, Double lang2, String name, String contact){
        this.id = id;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.deliveryDate = deliveryDate;
        this.lat1 = lat1;
        this.lang1 = lang1;
        this.lat2 = lat2;
        this.lang2 = lang2;
        this.name = name;
        this.contact = contact;
    }
    public String getId(){return id;}
    public String getStatus(){return status;}
    public String getDeliveryTime(){return deliveryTime;}
    public String getDeliveryDate(){return deliveryDate;}
    public String getContact(){return contact;}
    public String getName(){return name;}
    public double getLat1(){return lat1;}
    public double getLang1(){return lang1;}
    public double getLat2(){return lat2;}
    public double getLang2(){return lang2;}

    private void setId(String id){this.id = id;}
    private void setStatus(String status){this.status = status;}
    private void  setDeliveryTime(String deliveryTime){this.deliveryTime = deliveryTime;}
    private void setDeliveryDate(String deliveryDate){this.deliveryDate = deliveryDate;}
    private void setContact(String contact){this.contact = contact;}
    private void setName(String name){this.name = name;}
    private void setLat1(double lat1){this.lat1 = lat1;}
    private void setLang1(double lang1){this.lang1 = lang1;}
    private void setLat2(double lat2){this.lat1 = lat2;}
    private void setLang2(double lang2){this.lang1 = lang2;}
}
