package com.moringaschool.wakulima.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.wakulima.AnimalApi;
import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.R;
import com.moringaschool.wakulima.adapter.FarmingArrayAdapter;
import com.moringaschool.wakulima.service.AnimalClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FarmingActivity extends AppCompatActivity {
    private static final String TAG = FarmingActivity.class.getSimpleName();

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public List<Farming>farming;

    private FarmingArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming);
        ButterKnife.bind(this);


        AnimalApi client = AnimalClient.getClient();

        Call<List<Farming>> call = client.getAnimals();

        call.enqueue(new Callback<List<Farming>>() {
            @Override
            public void onResponse(Call<List<Farming>> call, Response<List<Farming>> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    farming = response.body();

                    for (int i = 0; i < restaurants.length; i++){
                        restaurants[i] = restaurantsList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = restaurantsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter
                            = new LearnFarmingArrayAdapter(FarmingActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
                    mListView.setAdapter(adapter);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<GovBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}