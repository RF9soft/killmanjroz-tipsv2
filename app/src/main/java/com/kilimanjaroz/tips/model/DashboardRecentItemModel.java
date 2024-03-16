package com.kilimanjaroz.tips.model;

public class DashboardRecentItemModel {
    private int dashImage;
    private String title;

    private int id;

    public DashboardRecentItemModel(int dashImage, String title, int id) {
        this.dashImage = dashImage;
        this.title = title;
        this.id = id;

    }

    public int getDashImage() {
        return dashImage;
    }

    public void setDashImage(int dashImage) {
        this.dashImage = dashImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
