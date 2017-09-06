package com.rstintl.docta.deliveryApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 06-09-2017.
 */

public class Response_ {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<DriverInfo> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DriverInfo> getData() {
        return data;
    }

    public void setData(List<DriverInfo> data) {
        this.data = data;
    }
}
