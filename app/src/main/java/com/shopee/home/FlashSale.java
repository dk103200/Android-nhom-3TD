package com.shopee.home;

public class FlashSale {
    private int img,sell;
    private String gia;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSell() {
        String selled = "ĐÃ BÁN " + sell;
        return selled;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public FlashSale(int img, String gia) {
        this.img = img;
        this.gia = gia;
    }

    public FlashSale(int img, String gia, int sell) {
        this.img = img;
        this.sell = sell;
        this.gia = gia;
    }
}
