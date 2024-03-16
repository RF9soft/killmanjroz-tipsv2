package com.kilimanjaroz.tips.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsNotifcationDatumn {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("notification_title")
    @Expose
    private String notificationTitle;
    @SerializedName("notification_description")
    @Expose
    private String notificationDescription;
    @SerializedName("notification_image")
    @Expose
    private String notificationImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }
}
