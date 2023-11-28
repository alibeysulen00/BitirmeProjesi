package com.example.bitirmeprojesi.data.entity;

import java.util.List;

public class TumYemekler {
    private List<Yemekler> yemekler;
    private int success;

    public TumYemekler() {
    }

    public TumYemekler(List<Yemekler> yemekler, int success) {
        this.yemekler = yemekler;
        this.success = success;
    }

    public List<Yemekler> getYemekler() {
        return yemekler;
    }

    public void setYemekler(List<Yemekler> yemekler) {
        this.yemekler = yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
