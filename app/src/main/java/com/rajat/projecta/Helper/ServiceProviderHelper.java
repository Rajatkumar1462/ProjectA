package com.rajat.projecta.Helper;

public class ServiceProviderHelper {

    private int image;
    private String title;
    private String rating;
    private String info;

    public ServiceProviderHelper(int image, String title, String rating, String info){
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.info = info;
    }
    public int getImage() {
        return image;
    }

    public String getTitle(){ return title; }

    public String getRating(){ return rating; }

    public String getInfo(){ return  info; }
}
