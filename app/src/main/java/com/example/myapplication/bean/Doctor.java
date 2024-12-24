package com.example.myapplication.bean;


public class Doctor {
    private int did;
    private String dname;
    private String dlevel;
    private String dinfo;
    private int departid;
    private int sex;
    private String detail;


    public Doctor(){}

    public Doctor(int did, String dname, String dlevel, String dinfo, int departid, int sex, String detail) {
        this.did = did;
        this.dname = dname;
        this.dlevel = dlevel;
        this.dinfo = dinfo;
        this.departid = departid;
        this.sex = sex;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", dlevel='" + dlevel + '\'' +
                ", dinfo='" + dinfo + '\'' +
                ", departid=" + departid +
                ", sex=" + sex +
                ", detail='" + detail + '\'' +
                '}';
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDlevel() {
        return dlevel;
    }

    public void setDlevel(String dlevel) {
        this.dlevel = dlevel;
    }

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}