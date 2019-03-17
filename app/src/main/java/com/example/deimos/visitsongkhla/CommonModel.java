package com.example.deimos.visitsongkhla;

public  class CommonModel {
    String title,url,des,tel,type,location,faceline,time,lat,lng;

    public CommonModel(String title, String url, String des, String tel, String type, String location, String faceline, String time,String lat,String lng) {
        this.title = title;
        this.url = url;
        this.des = des;
        this.tel = tel;
        this.type = type;
        this.location = location;
        this.faceline = faceline;
        this.time = time;
        this.lat = lat;
        this.lng = lng;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getImage() {
        return url;
    }

    public void setImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getDes() {
        return des;
    }

    public String getDescription() {
        return des;
    }

    public void setDescription(String description) {
        this.des = description;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFaceline() {
        return faceline;
    }

    public void setFaceline(String faceline) {
        this.faceline = faceline;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public CommonModel(){



    }
}
