package com.example.bitirmeprojesi.retrofit;

import com.example.bitirmeprojesi.data.entity.SepetCevap;
import com.example.bitirmeprojesi.data.entity.TumSepetler;
import com.example.bitirmeprojesi.data.entity.TumYemekler;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UrunlerDao {

    @GET("tumYemekleriGetir.php")
    Call<TumYemekler> yemekleriYukle();



    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<SepetCevap> yemekKaydet(@Field("yemek_adi")String yemek_adi, @Field("yemek_resim_adi") String yemek_resim_adi, @Field("yemek_fiyat") int yemek_fiyat,
                                 @Field("yemek_siparis_adet") int yemek_siparis_adet, @Field("kullanici_adi") String kullanici_adi);




    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<TumSepetler> sepetGetir(@Field("kullanici_adi") String kullanici_adi);

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    Call<SepetCevap> sepettenYemekSil(@Field("sepet_yemek_id") int sepet_yemek_id, @Field("kullanici_adi") String kullanici_adi);







}
