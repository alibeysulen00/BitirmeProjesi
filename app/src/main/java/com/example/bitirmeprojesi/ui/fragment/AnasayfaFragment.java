package com.example.bitirmeprojesi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitirmeprojesi.R;

import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding;
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter;
import com.example.bitirmeprojesi.ui.adapter.UrunlerAdapter;
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {

 private FragmentAnasayfaBinding binding;
 private AnasayfaViewModel viewModel;
 private SepetViewModel sepetViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);


        binding.anasayfaRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklerListesi -> {

            UrunlerAdapter adapter = new UrunlerAdapter(yemeklerListesi, requireContext(), viewModel);
            binding.anasayfaRv.setAdapter(adapter);

        });


        




        binding.sepetButton.setOnClickListener(v -> {
            /*sepetViewModel.sepetGetir("alibeysulen");*/
            Navigation.findNavController(v).navigate(R.id.sepetGecis);
        });






        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }



}