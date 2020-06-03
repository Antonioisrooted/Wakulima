package com.moringaschool.wakulima;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimalApi {

    @GET("animals")
    Call<List<Farming>> getAnimals();
}