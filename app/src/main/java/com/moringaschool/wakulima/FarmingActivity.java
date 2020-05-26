package com.moringaschool.wakulima;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

    public class FarmingActivity extends AppCompatActivity {
        TextView mLocationTextView;
        ListView mListView;

        private String[] restaurants = new String[] {"Poultry", };
        private String[] cuisines = new String[] {"Layers", ""};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_farming);
            ButterKnife.bind(this);

            LearnFarmingArrayAdapter adapter = new LearnFarmingArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines); // must match constructor!

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, farming);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String restaurant = ((TextView)view).getText().toString();
                    Toast.makeText(FarmingActivity.this, restaurant, Toast.LENGTH_LONG).show();
                }
            });

            Intent intent = getIntent();
            String location = intent.getStringExtra("location");

            mLocationTextView.setText("Lets take a deep dive: " + location);
        }
    }