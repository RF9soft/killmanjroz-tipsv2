package com.kilimanjaroz.tips.model.datewise;

import com.kilimanjaroz.tips.response.Tip;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatewiseResponse {
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
    private ArrayList<Tip> data;

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

    public ArrayList<Tip> getData() {
        return data;
    }

    public void setData(ArrayList<Tip> data) {
        this.data = data;
    }
}
