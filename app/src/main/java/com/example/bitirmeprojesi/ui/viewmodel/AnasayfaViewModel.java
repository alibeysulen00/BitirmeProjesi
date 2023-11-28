package com.example.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bitirmeprojesi.data.entity.Sepet;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.data.repo.UrunlerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    private final UrunlerDaoRepository udao;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;
    public MutableLiveData<List<Sepet>> sepetlerListesi;

    @Inject
    public AnasayfaViewModel(UrunlerDaoRepository udao) {
        this.udao = udao;
        yemeklerListesi = udao.yemeklerListesi;
        sepetlerListesi = udao.sepetlerListesi;
        yemekleriYukle();
    }

    public void yemekleriYukle() {
        udao.yemekleriYukle();
    }

    
}
