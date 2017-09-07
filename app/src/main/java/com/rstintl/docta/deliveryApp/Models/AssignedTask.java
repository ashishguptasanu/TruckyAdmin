package com.rstintl.docta.deliveryApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 06-09-2017.
 */

public class AssignedTask {
    @SerializedName("task_id")
    @Expose
    private String taskId;
    @SerializedName("task_pickup_address")
    @Expose
    private String taskPickupAddress;
    @SerializedName("task_pickup_latitude")
    @Expose
    private double taskPickupLatitude;
    @SerializedName("task_pickup_longitude")
    @Expose
    private double taskPickupLongitude;
    @SerializedName("task_dropoff_address")
    @Expose
    private String taskDropoffAddress;
    @SerializedName("task_dropoff_latitude")
    @Expose
    private double taskDropoffLatitude;
    @SerializedName("task_dropoff_longitude")
    @Expose
    private double taskDropoffLongitude;
    @SerializedName("task_start_datetime")
    @Expose
    private String taskStartDatetime;
    @SerializedName("task_start_timestamp")
    @Expose
    private String taskStartTimestamp;
    @SerializedName("task_end_datetime")
    @Expose
    private String taskEndDatetime;
    @SerializedName("task_end_timestamp")
    @Expose
    private String taskEndTimestamp;
    @SerializedName("task_delivery_person_name")
    @Expose
    private String taskDeliveryPersonName;
    @SerializedName("task_delivery_person_contact")
    @Expose
    private String taskDeliveryPersonContact;
    @SerializedName("task_delivery_person_address")
    @Expose
    private String taskDeliveryPersonAddress;
    @SerializedName("task_vehicle_type")
    @Expose
    private String taskVehicleType;
    @SerializedName("task_driver_id")
    @Expose
    private String taskDriverId;
    @SerializedName("task_status")
    @Expose
    private String taskStatus;
    @SerializedName("task_created")
    @Expose
    private String taskCreated;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_contact")
    @Expose
    private String driverContact;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskPickupAddress() {
        return taskPickupAddress;
    }

    public void setTaskPickupAddress(String taskPickupAddress) {
        this.taskPickupAddress = taskPickupAddress;
    }

    public double getTaskPickupLatitude() {
        return taskPickupLatitude;
    }

    public void setTaskPickupLatitude(double taskPickupLatitude) {
        this.taskPickupLatitude = taskPickupLatitude;
    }

    public double getTaskPickupLongitude() {
        return taskPickupLongitude;
    }

    public void setTaskPickupLongitude(double taskPickupLongitude) {
        this.taskPickupLongitude = taskPickupLongitude;
    }

    public String getTaskDropoffAddress() {
        return taskDropoffAddress;
    }

    public void setTaskDropoffAddress(String taskDropoffAddress) {
        this.taskDropoffAddress = taskDropoffAddress;
    }

    public double getTaskDropoffLatitude() {
        return taskDropoffLatitude;
    }

    public void setTaskDropoffLatitude(double taskDropoffLatitude) {
        this.taskDropoffLatitude = taskDropoffLatitude;
    }

    public double getTaskDropoffLongitude() {
        return taskDropoffLongitude;
    }

    public void setTaskDropoffLongitude(double taskDropoffLongitude) {
        this.taskDropoffLongitude = taskDropoffLongitude;
    }

    public String getTaskStartDatetime() {
        return taskStartDatetime;
    }

    public void setTaskStartDatetime(String taskStartDatetime) {
        this.taskStartDatetime = taskStartDatetime;
    }

    public String getTaskStartTimestamp() {
        return taskStartTimestamp;
    }

    public void setTaskStartTimestamp(String taskStartTimestamp) {
        this.taskStartTimestamp = taskStartTimestamp;
    }

    public String getTaskEndDatetime() {
        return taskEndDatetime;
    }

    public void setTaskEndDatetime(String taskEndDatetime) {
        this.taskEndDatetime = taskEndDatetime;
    }

    public String getTaskEndTimestamp() {
        return taskEndTimestamp;
    }

    public void setTaskEndTimestamp(String taskEndTimestamp) {
        this.taskEndTimestamp = taskEndTimestamp;
    }

    public String getTaskDeliveryPersonName() {
        return taskDeliveryPersonName;
    }

    public void setTaskDeliveryPersonName(String taskDeliveryPersonName) {
        this.taskDeliveryPersonName = taskDeliveryPersonName;
    }

    public String getTaskDeliveryPersonContact() {
        return taskDeliveryPersonContact;
    }

    public void setTaskDeliveryPersonContact(String taskDeliveryPersonContact) {
        this.taskDeliveryPersonContact = taskDeliveryPersonContact;
    }

    public String getTaskDeliveryPersonAddress() {
        return taskDeliveryPersonAddress;
    }

    public void setTaskDeliveryPersonAddress(String taskDeliveryPersonAddress) {
        this.taskDeliveryPersonAddress = taskDeliveryPersonAddress;
    }

    public String getTaskVehicleType() {
        return taskVehicleType;
    }

    public void setTaskVehicleType(String taskVehicleType) {
        this.taskVehicleType = taskVehicleType;
    }

    public String getTaskDriverId() {
        return taskDriverId;
    }

    public void setTaskDriverId(String taskDriverId) {
        this.taskDriverId = taskDriverId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskCreated() {
        return taskCreated;
    }

    public void setTaskCreated(String taskCreated) {
        this.taskCreated = taskCreated;
    }
    public String getDriverName() {
        return driverName;
    }
    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }


    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public AssignedTask(String taskId, String taskPickupAddress, double taskPickupLatitude, double taskPickupLongitude, String taskDropoffAddress, double taskDropoffLatitude, double taskDropoffLongitude, String taskStartDatetime, String taskEndDatetime, String taskStatus, String driverName, String driverContact, String taskDeliveryPersonName){
        this.taskId = taskId;
        this.taskPickupAddress = taskPickupAddress;
        this.taskPickupLatitude = taskPickupLatitude;
        this.taskPickupLongitude = taskPickupLongitude;
        this.taskDropoffAddress = taskDropoffAddress;
        this.taskDropoffLatitude = taskDropoffLatitude;
        this.taskDropoffLongitude = taskDropoffLongitude;
        this.taskStartDatetime = taskStartDatetime;
        this.taskEndDatetime = taskEndDatetime;
        this.taskStatus = taskStatus;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.taskDeliveryPersonName = taskDeliveryPersonName;
    }

}
