package com.example.bitirmeprojesi.ui.adapter;

import static android.graphics.Color.rgb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.databinding.FavorilerCardBinding;
import com.example.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.example.bitirmeprojesi.ui.viewmodel.FavorilerViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

public class FavoriAdapter extends RecyclerView.Adapter<FavoriAdapter.CardTasarimTutucu>{
    private List<Yemekler> favoriYemeklerListesi;
    private Context mContext;
    private DetayViewModel detayViewModel;



    public FavoriAdapter(List<Yemekler> favoriYemeklerListesi, Context mContext, DetayViewModel detayViewModel) {
        this.favoriYemeklerListesi = favoriYemeklerListesi;
        this.mContext = mContext;
        this.detayViewModel = detayViewModel;

    }



    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private FavorilerCardBinding tasarim;

        public CardTasarimTutucu(FavorilerCardBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }



    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavorilerCardBinding binding = FavorilerCardBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

       Yemekler favoriYemek = favoriYemeklerListesi.get(position);


        FavorilerCardBinding t = holder.tasarim;

        t.favoriYemekAd.setText(favoriYemek.getYemek_adi());
        resimGoster(favoriYemek.getYemek_resim_adi(), t.favoriYemekResim);
        t.favoriYemekFiyat.setText(String.valueOf(favoriYemek.getYemek_fiyat()));

        t.favoriSepeteEkle.setOnClickListener(v -> {

            DetayViewModel.yemekKaydet(favoriYemek.getYemek_adi(), favoriYemek.getYemek_resim_adi(), favoriYemek.getYemek_fiyat(),
                    1, "alibeysulen");
        });

        t.favoriSil.setOnClickListener(v -> {
            favoriYemeklerListesi.remove(position); // listeden de siliyoruz
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favoriYemeklerListesi.size());
        });



    }

    @Override
    public int getItemCount() {
        if(favoriYemeklerListesi == null)
            return 0;
        else

        return favoriYemeklerListesi.size();
    }

    public void resimGoster(String resimAdi, ImageView imageView) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url).override(300, 300).into(imageView);
    }
}
