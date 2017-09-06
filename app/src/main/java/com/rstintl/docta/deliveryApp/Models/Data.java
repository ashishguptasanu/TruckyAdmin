package com.rstintl.docta.deliveryApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 06-09-2017.
 */

public class Data {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("assigned_task")
    @Expose
    private List<AssignedTask> assignedTask = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssignedTask> getAssignedTask() {
        return assignedTask;
    }

    public void setAssignedTask(List<AssignedTask> assignedTask) {
        this.assignedTask = assignedTask;
    }
}
