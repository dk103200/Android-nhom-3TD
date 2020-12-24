package com.shopee.home;

public class FlashSale {
    private int id;
    private String gia,sell, img;


    public FlashSale() {
    }

    public String getSell() {
        String selled = sell;
        return selled;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public FlashSale(String gia, String sell, String img, int id) {
        this.id = id;
        this.gia = gia;
        this.sell = sell;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
