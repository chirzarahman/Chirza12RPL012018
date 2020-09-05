package com.example.chiza12rpl012018.Model;

public class DashboardModel {

    private int image;
    private String title;
    private String description;

    public DashboardModel(int Image, String Title, String Description) {
        image = Image;
        title = Title;
        description = Description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
