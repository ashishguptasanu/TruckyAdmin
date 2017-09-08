package com.rstintl.docta.deliveryApp.Models;

import android.icu.text.StringPrepParseException;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 08-09-2017.
 */

public class VehicleList {

    @SerializedName("vehicle_id")
    @Expose
    private String vehicleId;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("vehicle_type")
    @Expose
    private String vehicleType;
    @SerializedName("vehicle_maximum_load")
    @Expose
    private String vehicleMaximumLoad;
    @SerializedName("vehicle_last_service_date")
    @Expose
    private String vehicleLastServiceDate;
    @SerializedName("vehicle_next_service_date")
    @Expose
    private String vehicleNextServiceDate;
    @SerializedName("vehicle_insurance_end_date")
    @Expose
    private String vehicleInsuranceEndDate;
    @SerializedName("vehicle_refrigerated")
    @Expose
    private String vehicleRefrigerated;
    @SerializedName("vehicle_pest_control")
    @Expose
    private String vehiclePestControl;
    @SerializedName("vehicle_water_proof")
    @Expose
    private String vehicleWaterProof;
    @SerializedName("vehicle_created")
    @Expose
    private String vehicleCreated;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleMaximumLoad() {
        return vehicleMaximumLoad;
    }

    public void setVehicleMaximumLoad(String vehicleMaximumLoad) {
        this.vehicleMaximumLoad = vehicleMaximumLoad;
    }

    public String getVehicleLastServiceDate() {
        return vehicleLastServiceDate;
    }

    public void setVehicleLastServiceDate(String vehicleLastServiceDate) {
        this.vehicleLastServiceDate = vehicleLastServiceDate;
    }

    public String getVehicleNextServiceDate() {
        return vehicleNextServiceDate;
    }

    public void setVehicleNextServiceDate(String vehicleNextServiceDate) {
        this.vehicleNextServiceDate = vehicleNextServiceDate;
    }

    public String getVehicleInsuranceEndDate() {
        return vehicleInsuranceEndDate;
    }

    public void setVehicleInsuranceEndDate(String vehicleInsuranceEndDate) {
        this.vehicleInsuranceEndDate = vehicleInsuranceEndDate;
    }

    public String getVehicleRefrigerated() {
        return vehicleRefrigerated;
    }

    public void setVehicleRefrigerated(String vehicleRefrigerated) {
        this.vehicleRefrigerated = vehicleRefrigerated;
    }

    public String getVehiclePestControl() {
        return vehiclePestControl;
    }

    public void setVehiclePestControl(String vehiclePestControl) {
        this.vehiclePestControl = vehiclePestControl;
    }

    public String getVehicleWaterProof() {
        return vehicleWaterProof;
    }

    public void setVehicleWaterProof(String vehicleWaterProof) {
        this.vehicleWaterProof = vehicleWaterProof;
    }

    public String getVehicleCreated() {
        return vehicleCreated;
    }

    public void setVehicleCreated(String vehicleCreated) {
        this.vehicleCreated = vehicleCreated;
    }
    public VehicleList(String vehicleId, String vehicleName,String vehicleType){
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;

    }
}
