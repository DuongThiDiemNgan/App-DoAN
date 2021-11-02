package com.example.leartsshop.model;

public class ItemMain {
    public int id;
    public String name;
    public String Img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public ItemMain(int id, String name, String img) {
        this.id = id;
        this.name = name;
        Img = img;
    }
}
