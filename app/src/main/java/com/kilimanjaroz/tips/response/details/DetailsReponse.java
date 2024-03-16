package com.kilimanjaroz.tips.response.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsReponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private DetailsData data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DetailsData getData() {
        return data;
    }

    public void setData(DetailsData data) {
        this.data = data;
    }
}
