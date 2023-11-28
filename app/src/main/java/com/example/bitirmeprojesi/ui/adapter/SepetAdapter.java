package com.example.bitirmeprojesi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitirmeprojesi.data.entity.Sepet;
import com.example.bitirmeprojesi.databinding.SepetYemeklerCardBinding;
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel;

import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu> {
    private List<Sepet> sepetYemeklerListesi;
    private Context mContext;
    private SepetViewModel viewModel;

    public SepetAdapter(List<Sepet> sepetYemeklerListesi, Context mContext, SepetViewModel viewModel) {
        this.sepetYemeklerListesi = sepetYemeklerListesi;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private SepetYemeklerCardBinding tasarim;

        public CardTasarimTutucu(SepetYemeklerCardBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetYemeklerCardBinding binding = SepetYemeklerCardBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Sepet sepet = sepetYemeklerListesi.get(position);
        SepetYemeklerCardBinding t = holder.tasarim;

        t.sepetYemekAd.setText(sepet.getYemek_adi());
        t.sepetYemekFiyat.setText(String.valueOf(sepet.getYemek_fiyat()));
        t.sepetAdet.setText(String.valueOf(sepet.getYemek_siparis_adet()));
        resimGoster(sepet.getYemek_resim_adi(), t.sepetResim);


        t.urunSilButton.setOnClickListener(v -> {


            viewModel.sepettenYemekSil(sepet.getSepet_yemek_id(), "alibeysulen");
            sepetYemeklerListesi.remove(sepet);
            notifyDataSetChanged(); // RecyclerView'yi güncelle
            viewModel.sifirla();
            /*viewModel.eklemeYapildi(-sepet.getYemek_fiyat() * sepet.getYemek_siparis_adet());
            int toplamFiyat = viewModel.toplamFiyatiHesapla(sepetYemeklerListesi);
            t.sepetToplam.setText(String.valueOf(sepet.getYemek_fiyat() * sepet.getYemek_siparis_adet()));
            viewModel.getGenelToplam().setValue(toplamFiyat);
           */
        });

        viewModel.eklemeYapildi(sepet.getYemek_fiyat() * sepet.getYemek_siparis_adet());
        int toplamFiyat = viewModel.toplamFiyatiHesapla(sepetYemeklerListesi);
        t.sepetToplam.setText(String.valueOf(sepet.getYemek_fiyat() * sepet.getYemek_siparis_adet()));
        viewModel.getGenelToplam().setValue(toplamFiyat);
    }

    @Override
    public int getItemCount() {
        return sepetYemeklerListesi.size();
    }

    public void resimGoster(String resimAdi, ImageView imageView) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url).override(300, 300).into(imageView);
    }
}
