package com.example.bitirmeprojesi.ui.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.retrofit.UrunlerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FavorilerViewModel extends ViewModel {

    private UrunlerDao udao;
    public static MutableLiveData<List<Yemekler>> favorilerListesi = new MutableLiveData<>(null);
    public static MutableLiveData<List<Yemekler>> sharedPreferencesData = new MutableLiveData<>(null);
    @Inject
    public FavorilerViewModel(UrunlerDao udao) {
        this.udao = udao;
    }

    public FavorilerViewModel() {
    }

    public static void favoriyeEkle(int yemekFiyat, String yemekAdi, String yemekResimAdi) {
        List<Yemekler> favoriler = favorilerListesi.getValue();
        if (favoriler == null) {
            favoriler = new ArrayList<>();
            Yemekler yemek = new Yemekler();
            yemek.setYemek_adi(yemekAdi);
            yemek.setYemek_fiyat(yemekFiyat);
            yemek.setYemek_resim_adi(yemekResimAdi);
            favoriler.add(yemek);
            favorilerListesi.setValue(favoriler);

        }else{

            for(int i = 0; i<favoriler.size(); i++){
                if(favoriler.get(i).getYemek_adi().equals(yemekAdi)){
                    favoriler.remove(i);
                    favorilerListesi.setValue(favoriler);
                }


            }
            Yemekler yemek = new Yemekler();
            yemek.setYemek_adi(yemekAdi);
            yemek.setYemek_fiyat(yemekFiyat);
            yemek.setYemek_resim_adi(yemekResimAdi);
            favoriler.add(yemek);
            favorilerListesi.setValue(favoriler);

        }



    }



}
