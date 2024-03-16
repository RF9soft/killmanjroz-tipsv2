package com.kilimanjaroz.tips.model;

public class DashboardItemModel {
    private int dashImage;
    private String title;
    private String subtitle;
    private int id;

    public DashboardItemModel(int dashImage, String title, String subtitle, int id) {
        this.dashImage = dashImage;
        this.title = title;
        this.id = id;
        this.subtitle=subtitle;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
