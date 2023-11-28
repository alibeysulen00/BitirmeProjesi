package com.example.bitirmeprojesi.ui;

import android.util.Log;

import com.example.bitirmeprojesi.data.entity.Yemekler;

import java.util.ArrayList;
import java.util.List;

public class FavoriManager {
    private static FavoriManager instance;
    private List<Yemekler> favoriListesi;

    public FavoriManager() {
        favoriListesi = new ArrayList<>();
    }

   public static FavoriManager getInstance(){
        if(instance == null){
            instance = new FavoriManager();
        }
        return instance;
   }
   public List<Yemekler> getFavoriListesi() {
        return favoriListesi;
    }

    public void toggleFavori(Yemekler yemek) {
        if (favoriListesi.contains(yemek)) {
            favoriListesi.remove(yemek);
        } else {
            favoriListesi.add(yemek);
        }
    }

    public boolean isFavori(Yemekler yemek) {
        for(Yemekler y : favoriListesi){
            Log.e("favoriListesi",y.getYemek_adi());
        }

        return favoriListesi.contains(yemek);
    }
}