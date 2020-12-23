package com.shopee.cart;

public class Cart {
    private int id;
    private String img;
    private String name;
    private String product;
    private int gia;
    private int solg;

    public Cart() {
    }

    public Cart(int id, String img, String name, String product, int gia, int solg) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.product = product;
        this.gia = gia;
        this.solg = solg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolg() {
        return solg;
    }

    public void setSolg(int solg) {
        this.solg = solg;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
