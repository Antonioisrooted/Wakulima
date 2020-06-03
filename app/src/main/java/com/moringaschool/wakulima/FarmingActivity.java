package com.moringaschool.wakulima;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

    public class FarmingActivity extends AppCompatActivity {
        public static final String TAG = FarmingActivity.class.getSimpleName();
        //TextView mLocationTextView;
        //ListView mListView;
        @BindView(R.id.locationTextView) TextView mLocationTextView;
        @BindView(R.id.listView) ListView mListView;

        private String[] restaurants = new String[] {"Poultry Farming", "Cow Farming", "Goat Farming", "Pig Farming", "Sheep Farming"};
        private String[] cuisines = new String[] {"Layers", "Dairy Cattle", "Dairy Goats", "Breeding Pigs", "Sheep Breeding"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_farming);
            ButterKnife.bind(this);

            LearnFarmingArrayAdapter adapter = new LearnFarmingArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines); // must match constructor!

            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String restaurant = ((TextView)view).getText().toString();

                    Toast.makeText(FarmingActivity.this, cuisines[i], Toast.LENGTH_LONG).show();
                        getRestaurants(location);
                    }

            });

            Intent intent = getIntent();
            String location = intent.getStringExtra("location");

            mLocationTextView.setText("Lets take a deep dive: " + location);
        }

    private void getRestaurants(String location) {
        final GovServices govServices = new GovServices();
        govServices.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}