package com.moringaschool.wakulima;

import android.content.Context;
import android.widget.ArrayAdapter;

class LearnFarmingArrayAdapter extends ArrayAdapter {
    private String[] mRestaurants;
    private String[] mCuisines;

    public LearnFarmingArrayAdapter(Context mContext, int resource, String[] mRestaurants, String[] mCuisines) {
        super(mContext, resource);
        this.mRestaurants = mRestaurants;
        this.mCuisines = mCuisines;
    }

    @Override
    public Object getItem(int position) {
        String restaurant = mRestaurants[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", restaurant, cuisine);
    }

    @Override
    public int getCount() {
        return mRestaurants.length;
    }
}