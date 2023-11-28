package com.example.bitirmeprojesi.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/yemekler/";

    public static UrunlerDao getUrunlerDao(){
        return RetrofitClient.getClient(BASE_URL).create(UrunlerDao.class);
    }
}
