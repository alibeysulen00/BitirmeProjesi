package com.example.bitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = navHostFragment.getNavController();
        bottomNavigationView.setOnNavigationItemSelectedListener(item ->{
            if(item.getItemId() == R.id.anasayfaFragment){
                Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.anasayfaFragment);
                return true;
            }
            else if(item.getItemId() == R.id.sepetFragment){
                Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.sepetFragment);
                return true;
            } else if (item.getItemId() == R.id.favorilerFragment) {
                Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.favorilerFragment);
                return true;

            } else{
                return false;
            }
        });






    }



}