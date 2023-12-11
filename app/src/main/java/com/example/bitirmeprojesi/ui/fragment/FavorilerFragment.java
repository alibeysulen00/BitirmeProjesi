package com.example.bitirmeprojesi.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitirmeprojesi.R;
import com.example.bitirmeprojesi.data.entity.Yemekler;
import com.example.bitirmeprojesi.databinding.FragmentFavorilerBinding;
import com.example.bitirmeprojesi.ui.adapter.FavoriAdapter;
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.example.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.example.bitirmeprojesi.ui.viewmodel.FavorilerViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FavorilerFragment extends Fragment {

   private FragmentFavorilerBinding binding;

   private DetayViewModel detayViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavorilerBinding.inflate(inflater, container, false);


        binding.favorilerRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));






        FavorilerViewModel.favorilerListesi.observe(getViewLifecycleOwner(), favorilerListesi -> {
            FavoriAdapter adapter = new FavoriAdapter(favorilerListesi, requireContext(), detayViewModel);
            binding.favorilerRv.setAdapter(adapter);

        });









        return binding.getRoot();
    }

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FavorilerViewModel.class);

    }*/


}