package com.moringaschool.wakulima.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.wakulima.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.findAnimalsButton) Button mFindAnimalsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindAnimalsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindAnimalsButton) {
            Intent intent = new Intent(MainActivity.this, FarmingActivity.class);
            startActivity(intent);
        }
    }
}