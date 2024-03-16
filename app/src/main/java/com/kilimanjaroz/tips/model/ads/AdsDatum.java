package com.kilimanjaroz.tips.model.ads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsDatum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("select_ads")
    @Expose
    private String selectAds;
    @SerializedName("admob_app_id")
    @Expose
    private String admobAppId;
    @SerializedName("admob_banner_id")
    @Expose
    private String admobBannerId;
    @SerializedName("admob_native_id")
    @Expose
    private String admobNativeId;
    @SerializedName("admob_interstial_id")
    @Expose
    private String admobInterstialId;
    @SerializedName("startio_app_id")
    @Expose
    private String startioAppId;
    @SerializedName("applovin_app_id")
    @Expose
    private String applovinAppId;
    @SerializedName("applovin_banner_id")
    @Expose
    private String applovinBannerId;
    @SerializedName("applovin_native_id")
    @Expose
    private String applovinNativeId;
    @SerializedName("applovin_interstial_id")
    @Expose
    private String applovinInterstialId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelectAds() {
        return selectAds;
    }

    public void setSelectAds(String selectAds) {
        this.selectAds = selectAds;
    }

    public String getAdmobAppId() {
        return admobAppId;
    }

    public void setAdmobAppId(String admobAppId) {
        this.admobAppId = admobAppId;
    }

    public String getAdmobBannerId() {
        return admobBannerId;
    }

    public void setAdmobBannerId(String admobBannerId) {
        this.admobBannerId = admobBannerId;
    }

    public String getAdmobNativeId() {
        return admobNativeId;
    }

    public void setAdmobNativeId(String admobNativeId) {
        this.admobNativeId = admobNativeId;
    }

    public String getAdmobInterstialId() {
        return admobInterstialId;
    }

    public void setAdmobInterstialId(String admobInterstialId) {
        this.admobInterstialId = admobInterstialId;
    }

    public String getStartioAppId() {
        return startioAppId;
    }

    public void setStartioAppId(String startioAppId) {
        this.startioAppId = startioAppId;
    }

    public String getApplovinAppId() {
        return applovinAppId;
    }

    public void setApplovinAppId(String applovinAppId) {
        this.applovinAppId = applovinAppId;
    }

    public String getApplovinBannerId() {
        return applovinBannerId;
    }

    public void setApplovinBannerId(String applovinBannerId) {
        this.applovinBannerId = applovinBannerId;
    }

    public String getApplovinNativeId() {
        return applovinNativeId;
    }

    public void setApplovinNativeId(String applovinNativeId) {
        this.applovinNativeId = applovinNativeId;
    }

    public String getApplovinInterstialId() {
        return applovinInterstialId;
    }

    public void setApplovinInterstialId(String applovinInterstialId) {
        this.applovinInterstialId = applovinInterstialId;
    }
}
