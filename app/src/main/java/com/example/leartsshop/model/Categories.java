package com.example.leartsshop.model;

import java.io.Serializable;

public class Categories implements Serializable {
    public int Id;
    public int Id_user;
    public String Name_cate;
    public String Img_cate;
    public String Detail;
    public String Create;
    public String Update;
    public int Status;
    public String View;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int id_user) {
        Id_user = id_user;
    }

    public String getName_cate() {
        return Name_cate;
    }

    public void setName_cate(String name_cate) {
        Name_cate = name_cate;
    }

    public String getImg_cate() {
        return Img_cate;
    }

    public void setImg_cate(String img_cate) {
        Img_cate = img_cate;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getCreate() {
        return Create;
    }

    public void setCreate(String create) {
        Create = create;
    }

    public String getUpdate() {
        return Update;
    }

    public void setUpdate(String update) {
        Update = update;
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

    public Categories(int id, int id_user, String name_cate, String img_cate, String detail, String create, String update, int status, String view) {
        Id = id;
        Id_user = id_user;
        Name_cate = name_cate;
        Img_cate = img_cate;
        Detail = detail;
        Create = create;
        Update = update;
        Status = status;
        View = view;
    }
}
