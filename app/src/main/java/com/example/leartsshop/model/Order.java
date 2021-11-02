package com.example.leartsshop.model;

public class Order {
    private int Id;
    private int User_id;
    private String Address;
    private String Phone;
    private String Note;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Order(int id, int user_id, String address, String phone, String note) {
        Id = id;
        User_id = user_id;
        Address = address;
        Phone = phone;
        Note = note;
    }
}
