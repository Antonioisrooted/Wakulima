package com.moringaschool.wakulima.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.R;
import com.moringaschool.wakulima.adapter.AnimalPageAdapter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalDetails extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private AnimalPageAdapter adapterViewPager;
    List<Farming> mFarming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);
        ButterKnife.bind(this);
        mFarming = Parcels.unwrap(getIntent().getParcelableExtra("animals"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        adapterViewPager = new AnimalPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFarming);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
