package com.moringaschool.wakulima.service;

import com.moringaschool.wakulima.Constants;
import com.moringaschool.wakulima.Farming;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class AnimalServices {

    public static void findAnimals(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ANIMALS_BASE_URL).newBuilder();


    }

    public ArrayList<Farming> processResults(Response response) {
        ArrayList<Farming> animals = new ArrayList<>();
        try {

            String jsonData = response.body().string();
            JSONObject animalJSON = new JSONObject(jsonData);
            JSONArray animalsJSON = animalJSON.getJSONArray("animals");

            if (response.isSuccessful()) {
                for (int i = 0; i < animalsJSON.length(); i++) {
                    JSONObject farmingJSON = animalsJSON.getJSONObject(i);
                    String type = farmingJSON.getString("type");
                    String breed = farmingJSON.getString("breed");
                    String image = farmingJSON.getString("image");
                    String description = farmingJSON.getString("description");

                    Farming farming = new Farming(type, breed, image, description);

                    animals.add(farming);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return animals;
    }
}