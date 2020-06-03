package com.moringaschool.wakulima.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.wakulima.Farming;
import com.moringaschool.wakulima.ui.AnimalDetailsFragment;

import java.util.List;

public class AnimalPageAdapter extends FragmentPagerAdapter {
    private List<Farming>mFarming;
    public AnimalPageAdapter(FragmentManager fm,int behavour, List<Farming>farming){
        super(fm, behavour);
        mFarming = farming;
    }
    @Override
    public Fragment getItem(int position) {
        return AnimalDetailsFragment.newInstance(mFarming.get(position));
    }

    @Override
    public int getCount() {
        return mFarming.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFarming.get(position).getType();
    }
}
