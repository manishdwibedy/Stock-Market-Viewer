package com.example.manishdwibedy.stockmarketviewer.fragments;

/**
 * Created by manishdwibedy on 3/16/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    public final String[] tabNames = new String[]{"Current","Historical","News"};
    private FavoriteStock stock;
    public TabsPagerAdapter(FavoriteStock stock, FragmentManager fm) {
        super(fm);
        this.stock = stock;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1, stock);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabNames[position];
    }
}