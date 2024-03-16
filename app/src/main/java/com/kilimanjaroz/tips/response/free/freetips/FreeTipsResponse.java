package com.kilimanjaroz.tips.response.free.freetips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FreeTipsResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<FreeTipsList> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FreeTipsList> getData() {
        return data;
    }

    public void setData(List<FreeTipsList> data) {
        this.data = data;
    }
}
