package com.kilimanjaroz.tips.model.ads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AdsDatum data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdsDatum getData() {
        return data;
    }

    public void setData(AdsDatum data) {
        this.data = data;
    }
}
