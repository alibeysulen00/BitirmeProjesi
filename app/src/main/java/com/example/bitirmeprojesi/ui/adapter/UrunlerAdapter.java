package com.example.bitirmeprojesi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitirmeprojesi.R;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.databinding.AnasayfaYemeklerCardBinding;
import com.example.bitirmeprojesi.ui.FavoriManager;
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragment;
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections;
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;

import java.util.List;

public class UrunlerAdapter extends RecyclerView.Adapter<UrunlerAdapter.CardTasarimTutucu> {
    private List<Yemekler> yemeklerListesi;
    private Context mContext;
    private AnasayfaViewModel viewModel;
    private FavoriManager favoriManager;

    public UrunlerAdapter(List<Yemekler> yemeklerListesi, Context mContext, AnasayfaViewModel viewModel) {
        this.yemeklerListesi = yemeklerListesi;
        this.mContext = mContext;
        this.viewModel = viewModel;
        this.favoriManager = FavoriManager.getInstance();
    }



    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private AnasayfaYemeklerCardBinding tasarim;

        public CardTasarimTutucu(AnasayfaYemeklerCardBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }
    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //attachToParrent !!!
        AnasayfaYemeklerCardBinding binding = AnasayfaYemeklerCardBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yemekler yemek = yemeklerListesi.get(position);
        AnasayfaYemeklerCardBinding t = holder.tasarim;

        String yemekUrlAdi = yemek.getYemek_resim_adi();
        resimGoster(yemekUrlAdi,t.yemekResim);
        t.yemekAdi.setText(yemek.getYemek_adi());
        t.yemekFiyat.setText(String.valueOf("₺ "+yemek.getYemek_fiyat()));

        t.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite(yemek,t.likeImageView);
            }
        });



        t.cardViewKategori.setOnClickListener(v1 -> {
            // Ürün detaya git

            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(v1).navigate(gecis);
        });








    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }

    public void resimGoster(String resimAdi, ImageView imageView) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url).override(300, 300).into(imageView);
    }

    private void toggleFavorite(Yemekler yemek, ImageView likeImageView) {
        favoriManager.toggleFavori(yemek);
        updateFavoriteIcon(yemek, likeImageView);
    }

    private void updateFavoriteIcon(Yemekler yemek, ImageView likeImageView) {
        if (favoriManager.isFavori(yemek)) {
            likeImageView.setImageResource(R.drawable.favoriteok);
        } else {
            likeImageView.setImageResource(R.drawable.nonefavorite);
        }
    }




}
