package com.example.leartsshop.model;

public class Article {
    public int Id;
    public int Id_user;
    public String Name_article;
    public String Img_article;
    public String Desc_article;
    public String Detail_article;
    public String Create_article;
    public String Update_article;
    public String Keyword_article;
    public int Status_article;
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

    public String getName_article() {
        return Name_article;
    }

    public void setName_article(String name_article) {
        Name_article = name_article;
    }

    public String getImg_article() {
        return Img_article;
    }

    public void setImg_article(String img_article) {
        Img_article = img_article;
    }

    public String getDesc_article() {
        return Desc_article;
    }

    public void setDesc_article(String desc_article) {
        Desc_article = desc_article;
    }

    public String getDetail_article() {
        return Detail_article;
    }

    public void setDetail_article(String detail_article) {
        Detail_article = detail_article;
    }

    public String getCreate_article() {
        return Create_article;
    }

    public void setCreate_article(String create_article) {
        Create_article = create_article;
    }

    public String getUpdate_article() {
        return Update_article;
    }

    public void setUpdate_article(String update_article) {
        Update_article = update_article;
    }

    public String getKeyword_article() {
        return Keyword_article;
    }

    public void setKeyword_article(String keyword_article) {
        Keyword_article = keyword_article;
    }

    public int getStatus_article() {
        return Status_article;
    }

    public void setStatus_article(int status_article) {
        Status_article = status_article;
    }

    public String getView() {
        return View;
    }

    public void setView(String view) {
        View = view;
    }

    public Article(int id, int id_user, String name_article, String img_article, String desc_article, String detail_article, String create_article, String update_article, String keyword_article, int status_article, String view) {
        Id = id;
        Id_user = id_user;
        Name_article = name_article;
        Img_article = img_article;
        Desc_article = desc_article;
        Detail_article = detail_article;
        Create_article = create_article;
        Update_article = update_article;
        Keyword_article = keyword_article;
        Status_article = status_article;
        View = view;
    }
}
