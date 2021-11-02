package com.example.leartsshop.model;

public class Portfolio {
    public int Id;
    public int Id_user;
    public String Name_port;
    public String Avatar_port;
    public String Img_Port;
    public String Origin_port;
    public String Detail_port;
    public String Create_port;
    public String Update_port;
    public int Status_port;

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

    public String getName_port() {
        return Name_port;
    }

    public void setName_port(String name_port) {
        Name_port = name_port;
    }

    public String getAvatar_port() {
        return Avatar_port;
    }

    public void setAvatar_port(String avatar_port) {
        Avatar_port = avatar_port;
    }

    public String getImg_Port() {
        return Img_Port;
    }

    public void setImg_Port(String img_Port) {
        Img_Port = img_Port;
    }

    public String getOrigin_port() {
        return Origin_port;
    }

    public void setOrigin_port(String origin_port) {
        Origin_port = origin_port;
    }

    public String getDetail_port() {
        return Detail_port;
    }

    public void setDetail_port(String detail_port) {
        Detail_port = detail_port;
    }

    public String getCreate_port() {
        return Create_port;
    }

    public void setCreate_port(String create_port) {
        Create_port = create_port;
    }

    public String getUpdate_port() {
        return Update_port;
    }

    public void setUpdate_port(String update_port) {
        Update_port = update_port;
    }


    public int getStatus_port() {
        return Status_port;
    }

    public void setStatus_port(int status_port) {
        Status_port = status_port;
    }

    public Portfolio(int id, int id_user, String name_port, String avatar_port, String img_Port, String origin_port, String detail_port, String create_port, String update_port, int status_port) {
        Id = id;
        Id_user = id_user;
        Name_port = name_port;
        Avatar_port = avatar_port;
        Img_Port = img_Port;
        Origin_port = origin_port;
        Detail_port = detail_port;
        Create_port = create_port;
        Update_port = update_port;
        Status_port = status_port;
    }
}
