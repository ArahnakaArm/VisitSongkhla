package com.visitsongkhla.deimos.visitsongkhla;

public  class CommonModel {

    String title;
    String url;
    String url2;

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    String url3;
    String des;
    String tel;
    String type;
    String date;
    String location;
    String title2;


    String faceline;
    String time;
    String lat;
    String lng;
    String link;
    String MoreUrl1;
    String MoreUrl2;
    String MoreUrl3;
    String MoreUrl4;
    String MoreUrl5;
    int id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CommonModel(String title, String url,String url2,String url3, String des, String tel, String type, String location, String faceline, String time, String lat, String lng
    , String MoreUrl1, String MoreUrl2, String MoreUrl3, String MoreUrl4, String MoreUrl5, String date,String link,int id,String title2) {
        this.id = id;
        this.title2 = title2;
        this.url2 = url2;
        this.url3 = url3;
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
        this.date =date;

        this.MoreUrl1 = MoreUrl1;
        this.MoreUrl2 = MoreUrl2;
        this.MoreUrl3 = MoreUrl3;
        this.MoreUrl4 = MoreUrl4;
        this.MoreUrl5 = MoreUrl5;
        this.link = link;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getMoreUrl1() {
        return MoreUrl1;
    }

    public void setMoreUrl1(String moreUrl1) {
        MoreUrl1 = moreUrl1;
    }

    public String getMoreUrl2() {
        return MoreUrl2;
    }

    public void setMoreUrl2(String moreUrl2) {
        MoreUrl2 = moreUrl2;
    }

    public String getMoreUrl3() {
        return MoreUrl3;
    }

    public void setMoreUrl3(String moreUrl3) {
        MoreUrl3 = moreUrl3;
    }

    public String getMoreUrl4() {
        return MoreUrl4;
    }

    public void setMoreUrl4(String moreUrl4) {
        MoreUrl4 = moreUrl4;
    }

    public String getMoreUrl5() {
        return MoreUrl5;
    }

    public void setMoreUrl5(String moreUrl5) {
        MoreUrl5 = moreUrl5;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CommonModel(){



    }
}
