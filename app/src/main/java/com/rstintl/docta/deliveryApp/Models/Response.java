package com.rstintl.docta.deliveryApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 06-09-2017.
 */

public class Response {
    @SerializedName("Response")
    @Expose
    private Response_ response;

    public Response_ getResponse() {
        return response;
    }

    public void setResponse(Response_ response) {
        this.response = response;
    }
}
