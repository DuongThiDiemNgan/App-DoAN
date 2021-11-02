package com.example.leartsshop.model;

import java.io.Serializable;

public class Products implements Serializable {
    public int ID;
    public int Id_user;
    public int Id_cate;
    public int Id_port;
    public String Name_product;
    public String Image_product;
    public String Image_product_hover;
    public Integer Quantity_product;
    public Integer Price_product;
    public String Detail_product;
    public String Keyword_product;
    public int Status;
    public String View;
    public String Create_at;
    public String Update_at;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int id_user) {
        Id_user = id_user;
    }

    public int getId_cate() {
        return Id_cate;
    }

    public void setId_cate(int id_cate) {
        Id_cate = id_cate;
    }

    public int getId_port() {
        return Id_port;
    }

    public void setId_port(int id_port) {
        Id_port = id_port;
    }

    public String getName_product() {
        return Name_product;
    }

    public void setName_product(String name_product) {
        Name_product = name_product;
    }

    public String getImage_product() {
        return Image_product;
    }

    public void setImage_product(String image_product) {
        Image_product = image_product;
    }

    public String getImage_product_hover() {
        return Image_product_hover;
    }

    public void setImage_product_hover(String image_product_hover) {
        Image_product_hover = image_product_hover;
    }

    public Integer getQuantity_product() {
        return Quantity_product;
    }

    public void setQuantity_product(Integer quantity_product) {
        Quantity_product = quantity_product;
    }

    public Integer getPrice_product() {
        return Price_product;
    }

    public void setPrice_product(Integer price_product) {
        Price_product = price_product;
    }


    public String getDetail_product() {
        return Detail_product;
    }

    public void setDetail_product(String detail_product) {
        Detail_product = detail_product;
    }

    public String getKeyword_product() {
        return Keyword_product;
    }

    public void setKeyword_product(String keyword_product) {
        Keyword_product = keyword_product;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getView() {
        return View;
    }

    public void setView(String view) {
        View = view;
    }

    public String getCreate_at() {
        return Create_at;
    }

    public void setCreate_at(String create_at) {
        Create_at = create_at;
    }

    public String getUpdate_at() {
        return Update_at;
    }

    public void setUpdate_at(String update_at) {
        Update_at = update_at;
    }

    public Products(int ID, int id_user, int id_cate, int id_port, String name_product, String image_product, String image_product_hover, Integer quantity_product, Integer price_product, String detail_product, String keyword_product, int status, String view, String create_at, String update_at) {
        this.ID = ID;
        Id_user = id_user;
        Id_cate = id_cate;
        Id_port = id_port;
        Name_product = name_product;
        Image_product = image_product;
        Image_product_hover = image_product_hover;
        Quantity_product = quantity_product;
        Price_product = price_product;
        Detail_product = detail_product;
        Keyword_product = keyword_product;
        Status = status;
        View = view;
        Create_at = create_at;
        Update_at = update_at;
    }
}
