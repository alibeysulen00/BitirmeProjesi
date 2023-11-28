package com.example.bitirmeprojesi.data.entity;

import java.util.List;

public class TumSepetler {

    private List<Sepet> sepet_yemekler;
    private int success;

    public TumSepetler() {
    }

    public TumSepetler(List<Sepet> sepet_yemekler, int success) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
    }

    public List<Sepet> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<Sepet> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
