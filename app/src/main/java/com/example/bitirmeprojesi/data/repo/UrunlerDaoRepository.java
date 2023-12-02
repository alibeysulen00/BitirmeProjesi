package com.example.bitirmeprojesi.data.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bitirmeprojesi.data.entity.Sepet;
import com.example.bitirmeprojesi.data.entity.SepetCevap;
import com.example.bitirmeprojesi.data.entity.TumSepetler;
import com.example.bitirmeprojesi.data.entity.TumYemekler;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.retrofit.UrunlerDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunlerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public MutableLiveData<List<Sepet>> sepetlerListesi = new MutableLiveData<>();


    private UrunlerDao udao;

    @Inject
    public UrunlerDaoRepository(UrunlerDao udao) {
        this.udao = udao;
    }

    public void yemekleriYukle() {
        udao.yemekleriYukle().enqueue(new Callback<TumYemekler>() {
            @Override
            public void onResponse(Call<TumYemekler> call, Response<TumYemekler> response) {
                if (response.isSuccessful()) {
                    List<Yemekler> liste = response.body().getYemekler();
                    yemeklerListesi.setValue(liste);
                }
            }

            @Override
            public void onFailure(Call<TumYemekler> call, Throwable t) {
                // Hata durumunda gerekli işlemleri yapabilirsiniz.
            }
        });
    }


    public void yemekleriKaydet(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        udao.yemekKaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(new Callback<SepetCevap>() {
            @Override
            public void onResponse(Call<SepetCevap> call, Response<SepetCevap> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SepetCevap sepet = response.body();

                    // Gson kütüphanesini kullanarak JSON formatına çevirme
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(sepet);

                    // JSON formatındaki cevabı logla
                    Log.e("Kişiler Başarıyla Kaydedildi", jsonResponse);

                } else {
                    Log.e("Kişiler Kaydetme Başarısız", "HTTP hatası: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SepetCevap> call, Throwable t) {

            }
        });

    }

    public void sepetGetir(String kullanici_adi){
        udao.sepetGetir(kullanici_adi).enqueue(new Callback<TumSepetler>() {
            @Override
            public void onResponse(Call<TumSepetler> call, Response<TumSepetler> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sepet> sepetList = response.body().getSepet_yemekler();
                    sepetlerListesi.setValue(sepetList);

                    // Gson kütüphanesini kullanarak JSON formatına çevirme
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(sepetList);

                    // JSON formatındaki cevabı logla
                    Log.e("Sepet Başarıyla Getirildi", jsonResponse);
                } else {
                    Log.e("Sepet Getirme Başarısız", "HTTP hatası: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TumSepetler> call, Throwable t) {

            }
        });

    }
   


    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi){
        udao.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(new Callback<SepetCevap>() {
            @Override
            public void onResponse(Call<SepetCevap> call, Response<SepetCevap> response) {
                sepetGetir("alibeysulen");
            }

            @Override
            public void onFailure(Call<SepetCevap> call, Throwable t) {

            }
        });
    }




}
