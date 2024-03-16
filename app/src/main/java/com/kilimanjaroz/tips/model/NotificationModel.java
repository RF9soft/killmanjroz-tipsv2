package com.kilimanjaroz.tips.model;

public class NotificationModel {

    private String date;
    private String title;



    public NotificationModel() {

    }

    public NotificationModel(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
}
