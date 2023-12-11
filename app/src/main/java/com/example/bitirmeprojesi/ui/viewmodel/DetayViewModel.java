package com.example.bitirmeprojesi.ui.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bitirmeprojesi.data.entity.Sepet;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.data.repo.UrunlerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public static UrunlerDaoRepository udao;


    @Inject
    public DetayViewModel(UrunlerDaoRepository udao){
        this.udao = udao;
    }

    public static int sepetKontrol(String kullanici_adi,String yemek_adi) {


        if (udao == null) {
            Log.e("UrunlerDaoRepository", "null");

        } else {

            udao.sepetGetir(kullanici_adi); //önce api çalıştırılır live data ya verilerin aktarımı sağlanır
            LiveData<List<Sepet>> sepetLiveData = udao.sepetlerListesi; // ardından aktarım sağlanan verilerin listesi alınır
            List<Sepet> sepetler = sepetLiveData.getValue(); // listeye aktarılan verilerin değerleri alınır
            int eskiAdet = 0;
            if (sepetler != null) {
                for (Sepet sepet : sepetler) {
                    Log.e("Sepet İçeriği", sepet.getYemek_adi());
                    if (sepet.getYemek_adi().equals(yemek_adi)) { // eğer sepet içeriğindeki yemek adı parametre olarak gelen yemek adına eşitse
                        eskiAdet = sepet.getYemek_siparis_adet(); // eski adet değeri sepet içeriğindeki yemek adet değeri olarak atanır
                        udao.sepettenYemekSil(sepet.getSepet_yemek_id(), kullanici_adi); // sepetten yemek silme fonksiyonu çalıştırılır
                    }
                }
                return eskiAdet; // eski adet değeri döndürülür
            }


            return eskiAdet; // eğer sepetler listesi boşsa eski adet değeri döndürülür


        }
        return 0;
    }
         // eğer sepetler listesi boşsa eski adet değeri döndürülür




    public static void yemekKaydet(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){

        //önce api çalıştırılır live data ya verilerin aktarımı sağlanır
       int eskiAdet =  sepetKontrol(kullanici_adi, yemek_adi); // sepet kontrol fonksiyonu çalıştırılır ve eski adet değeri döndürülür
         yemek_siparis_adet += eskiAdet; // eski adet değeri yeni adet değeri ile toplanır

        if(udao == null){
            Log.e("UrunlerDaoRepository","null");
        }else{
            udao.yemekleriKaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi); // yemek kaydet fonksiyonu çalıştırılır

        }


    }


}
