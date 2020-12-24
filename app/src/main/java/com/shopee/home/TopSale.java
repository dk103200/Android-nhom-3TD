package com.shopee.home;

public class TopSale {
    private String gia,img;
    private int id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public TopSale() {
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TopSale(String gia, String img, int id) {
        this.gia = gia;
        this.img = img;
        this.id = id;
    }
}
