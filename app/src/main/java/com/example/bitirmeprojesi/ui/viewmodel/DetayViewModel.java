package com.example.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.data.repo.UrunlerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public UrunlerDaoRepository udao;

    @Inject
    public DetayViewModel(UrunlerDaoRepository udao){
        this.udao = udao;
    }



    public void yemekKaydet(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        udao.yemekleriKaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
    }


}
