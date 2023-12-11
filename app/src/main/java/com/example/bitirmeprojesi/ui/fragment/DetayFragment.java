package com.example.bitirmeprojesi.ui.fragment;

import static android.graphics.Color.rgb;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bitirmeprojesi.R;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.databinding.FragmentDetayBinding;
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter;
import com.example.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.atomic.AtomicInteger;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {

   private FragmentDetayBinding binding;

   private DetayViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetayBinding.inflate(inflater, container, false);




        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemekler();

        AtomicInteger urunlerToplami = new AtomicInteger();
        int yemekFiyat = gelenYemek.getYemek_fiyat();


        binding.textViewYemekAdi.setText(gelenYemek.getYemek_adi());
        binding.textViewYemekFiyat.setText(String.valueOf(yemekFiyat));
        String resimAdi = gelenYemek.getYemek_resim_adi();
        resimGoster(resimAdi,binding.imageViewYemekResim);
        binding.textToplam.setText(yemekFiyat * Integer.parseInt(binding.textAdet.getText().toString()) + " ₺");

        binding.imageButtonEksilt.setOnClickListener(v -> {



                if (Integer.parseInt(binding.textAdet.getText().toString()) >= 2) {
                    binding.textAdet.setText(String.valueOf(Integer.parseInt(binding.textAdet.getText().toString()) - 1));
                    binding.textToplam.setText(yemekFiyat * Integer.parseInt(binding.textAdet.getText().toString()) + " ₺");
                    urunlerToplami.set(yemekFiyat * Integer.parseInt(binding.textAdet.getText().toString()));
                } else {
                    binding.textAdet.setText("1");
                }


        });

        binding.imageButtonArttir.setOnClickListener(v -> {
        if (Integer.parseInt(binding.textAdet.getText().toString()) <= 4) {
            binding.textAdet.setText(String.valueOf(Integer.parseInt(binding.textAdet.getText().toString())+1));
            binding.textToplam.setText(yemekFiyat*Integer.parseInt(binding.textAdet.getText().toString())+" ₺");
            urunlerToplami.set(yemekFiyat*Integer.parseInt(binding.textAdet.getText().toString()));
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Uyarı");
            builder.setMessage("En fazla 5 adet ürün seçebilirsiniz.");
            builder.setNegativeButton("Tamam", (dialog, which) -> dialog.dismiss());
            builder.show();

        }
        });



        binding.detaySepeteEkleButton.setOnClickListener(v -> {
            //yemekadi, yemekresimadi, yemekfiyat, sipariadet,kulaniciadi
            String yemekAdi = gelenYemek.getYemek_adi();
            String yemekResimAdi = gelenYemek.getYemek_resim_adi();
            int yemekFiyati = gelenYemek.getYemek_fiyat();
            int siparisAdeti = Integer.parseInt(binding.textAdet.getText().toString());
            String kullaniciAdi = "alibeysulen";
            viewModel.yemekKaydet(yemekAdi,yemekResimAdi,yemekFiyati,siparisAdeti,kullaniciAdi);
            Snackbar.make(v,yemekAdi+" sepete eklendi!", 500)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(rgb(245, 124, 0))
                    .setActionTextColor(rgb(245, 124, 0))
                    .show();




        });









        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);
    }

    public void resimGoster(String resimAdi, ImageView imageView) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(this).load(url).override(300, 300).into(imageView);
    }
}