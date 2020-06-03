package com.moringaschool.wakulima;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GovServices {

        public static void findRestaurants(String location, Callback callback) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOV_BASE_URL).newBuilder();
            urlBuilder.addQueryParameter(Constants.GOV_LOCATION_QUERY_PARAMETER, location);
            String url = urlBuilder.build().toString();

            Request request= new Request.Builder()
                    .url(url)
                    .header("Authorization", Constants.GOV_TOKEN)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);
        }
    }