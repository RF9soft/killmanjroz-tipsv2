package com.kilimanjaroz.tips.response.free;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FreeCatResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("data")
    @Expose
    private ArrayList<FreeList> data;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<FreeList> getData() {
        return data;
    }

    public void setData(ArrayList<FreeList> data) {
        this.data = data;
    }
}
