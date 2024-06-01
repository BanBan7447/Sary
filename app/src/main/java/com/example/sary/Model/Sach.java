package com.example.sary.Model;

import java.util.SplittableRandom;

public class Sach {
    private String maSach;
    private String tensach;
    private String tacgia;
    private String nhaxuatban;
    private String theloai;
    private int giathue;

    public Sach(String maSach, String tensach, String tacgia, String nhaxuatban, String theloai, int giathue) {
        this.maSach = maSach;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nhaxuatban = nhaxuatban;
        this.theloai = theloai;
        this.giathue = giathue;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNhaxuatban() {
        return nhaxuatban;
    }

    public void setNhaxuatban(String nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }
}