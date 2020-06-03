package com.moringaschool.wakulima.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.wakulima.AnimalApi;
import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.R;
import com.moringaschool.wakulima.adapter.FarmingArrayAdapter;
import com.moringaschool.wakulima.service.AnimalClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<List<Farming>> call=client.getAnimals();

        call.enqueue(new Callback<List<Farming>>() {
            @Override
            public void onResponse(Call<List<Farming>> call, Response<List<Farming>> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    farming = response.body();
                    mAdapter = new FarmingArrayAdapter(FarmingActivity.this, farming);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FarmingActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    showAnimals();

                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Farming>> call, Throwable t) {
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

    private void showAnimals() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}