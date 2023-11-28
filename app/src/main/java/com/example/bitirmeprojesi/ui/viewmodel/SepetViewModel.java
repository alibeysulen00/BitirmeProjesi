package com.example.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bitirmeprojesi.data.entity.Sepet;
import com.example.bitirmeprojesi.data.repo.UrunlerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    public UrunlerDaoRepository udao;
    public MutableLiveData<List<Sepet>> sepetlerListesi;
    private final MutableLiveData<Integer> genelToplam = new MutableLiveData<>(0);

    @Inject
    public SepetViewModel(UrunlerDaoRepository udao){
        this.udao = udao;
        sepetlerListesi = udao.sepetlerListesi;
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi){
        udao.sepettenYemekSil(sepet_yemek_id, kullanici_adi);
    }

    public void sepetiYukle(String kullanici_adi){
        udao.sepetGetir(kullanici_adi);
    }

    public void eklemeYapildi(int eklenecekTutar) {
        Integer currentValue = genelToplam.getValue();
        if (currentValue != null) {
            genelToplam.setValue(currentValue + eklenecekTutar);
        }
    }

    public int toplamFiyatiHesapla(List<Sepet> sepetYemeklerListesi) {
        int toplamFiyat = 0;
        if (sepetYemeklerListesi != null) {
            for (Sepet yemek : sepetYemeklerListesi) {
                toplamFiyat += yemek.getYemek_fiyat() * yemek.getYemek_siparis_adet();
            }
        }
        return toplamFiyat;
    }

    public MutableLiveData<Integer> getGenelToplam() {
        return genelToplam;
    }

    public void sifirla() {
        genelToplam.setValue(0);
    }
}
