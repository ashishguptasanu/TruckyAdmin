package com.rstintl.docta.deliveryApp.Models;

/**
 * Created by Ashish on 01-09-2017.
 */

public class Task {
    public String deliveryTime, deliveryDate, id, pickUpLocation, dropOffLocation, contactPickup, contactDropoff, status;
    public Task(String id, String status, String deliveryDate, String deliveryTime, String pickUpLocation, String dropOffLocation, String contactPickup, String contactDropoff){
        this.id = id;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.deliveryDate = deliveryDate;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.contactPickup = contactPickup;
        this.contactDropoff = contactDropoff;
    }
    public String getId(){return id;}
    public String getStatus(){return status;}
    public String getDeliveryTime(){return deliveryTime;}
    public String getDeliveryDate(){return deliveryDate;}
    public String getPickUpLocation(){return pickUpLocation;}
    public String getDropOffLocation(){return dropOffLocation;}
    public String getContactPickup(){return contactPickup;}
    public String getContactDropoff(){return contactDropoff;}

    private void setId(String id){this.id = id;}
    private void setStatus(String status){this.status = status;}
    private void  setDeliveryTime(String deliveryTime){this.deliveryTime = deliveryTime;}
    private void setDeliveryDate(String deliveryDate){this.deliveryDate = deliveryDate;}
    private void setPickUpLocation(String pickUpLocation){this.pickUpLocation = pickUpLocation;}
    private void setDropOffLocation(String dropOffLocation){this.dropOffLocation = dropOffLocation;}
    private void setContactPickup(String contactPickup){this.contactPickup = contactPickup;}
    private void setContactDropoff(String contactDropoff){this.contactDropoff = contactDropoff;}
}
