package com.example.myapplication.bean;

public class Depart {
    private int departId;
    private String departName;

    public Depart() {}

    public Depart(String departName) {
        this.departName = departName;
    }

    public Depart(int departId, String departName) {
        this.departId = departId;
        this.departName = departName;
    }

    // Getters and Setters
    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}