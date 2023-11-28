package com.example.bitirmeprojesi.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bitirmeprojesi.databinding.FragmentSepetBinding;
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter;
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {

    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);

        binding.sepetRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
//Burada sepete eklenen yemeklerin listelendiği RecyclerView'yi güncellemek için observe ediyoruz.
        viewModel.sepetlerListesi.observe(getViewLifecycleOwner(), sepetlerListesi -> {
            SepetAdapter adapter = new SepetAdapter(sepetlerListesi, requireContext(), viewModel);
            binding.sepetRv.setAdapter(adapter);
            int toplamFiyat = viewModel.toplamFiyatiHesapla(sepetlerListesi);
            binding.sepetGenelToplam.setText("₺ " + toplamFiyat);
        });

// Sepetin genel toplamını güncellemek için observe ediyoruz.
        viewModel.getGenelToplam().observe(getViewLifecycleOwner(), genelToplam -> {
            binding.sepetGenelToplam.setText("₺ " + genelToplam);
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
        viewModel.sepetiYukle("alibeysulen");
    }
}
