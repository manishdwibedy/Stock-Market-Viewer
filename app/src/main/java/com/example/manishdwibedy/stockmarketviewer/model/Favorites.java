package com.example.manishdwibedy.stockmarketviewer.model;

import java.util.List;

/**
 * Created by manishdwibedy on 3/17/16.
 */
public class Favorites {
    private int count;
    private List<Stock> favoriteList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Stock> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Stock> favoriteList) {
        this.favoriteList = favoriteList;
    }
}
