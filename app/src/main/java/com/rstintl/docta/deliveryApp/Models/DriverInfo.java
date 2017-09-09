package com.rstintl.docta.deliveryApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 06-09-2017.
 */

public class DriverInfo {
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("driver_contact")
    @Expose
    private String driverContact;
    @SerializedName("driver_vehicle_no")
    @Expose
    private String driverVehicleNo;
    @SerializedName("driver_vehicle_type")
    @Expose
    private String driverVehicleType;
    @SerializedName("driver_area_code")
    @Expose
    private String driverAreaCode;
    @SerializedName("driver_duty_status")
    @Expose
    private String driverDutyStatus;
    @SerializedName("driver_rating")
    @Expose
    private String driverRating;
    @SerializedName("driver_added")
    @Expose
    private String driverAdded;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public String getDriverVehicleNo() {
        return driverVehicleNo;
    }

    public void setDriverVehicleNo(String driverVehicleNo) {
        this.driverVehicleNo = driverVehicleNo;
    }

    public String getDriverVehicleType() {
        return driverVehicleType;
    }

    public void setDriverVehicleType(String driverVehicleType) {
        this.driverVehicleType = driverVehicleType;
    }

    public String getDriverAreaCode() {
        return driverAreaCode;
    }

    public void setDriverAreaCode(String driverAreaCode) {
        this.driverAreaCode = driverAreaCode;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public String getDriverAdded() {
        return driverAdded;
    }

    public void setDriverAdded(String driverAdded) {
        this.driverAdded = driverAdded;
    }
    public String getDriverDutyStatus() {
        return driverDutyStatus;
    }

    public void setDriverDutyStatus(String driverDutyStatus) {
        this.driverDutyStatus = driverDutyStatus;
    }
    public DriverInfo(String driverId, String driverName, String vehicleType, String driverDutyStatus){
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverVehicleType = vehicleType;
        this.driverDutyStatus = driverDutyStatus;
    }

}
