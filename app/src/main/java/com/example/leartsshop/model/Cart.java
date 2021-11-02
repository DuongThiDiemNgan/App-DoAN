package com.example.leartsshop.model;

public class Cart {
    public int productId;
    public long price;
    public int quantity;
    public int amount;
    public String img;
    public String name;

    public Cart(int productId, long price, int quantity, int amount, String img, String name) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.img = img;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
