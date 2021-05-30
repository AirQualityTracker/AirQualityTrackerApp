package com.androidapp.airqualitytracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * This class represents the airvisual api  interface
 * */
public interface AirVisualApi {

    @GET("countries")
    Call<List<String>> getCountries();


    @GET("cards")
    Call<List<Card>> getCards();
}
