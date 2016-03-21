package com.example.manishdwibedy.stockmarketviewer.model;

import java.util.List;

/**
 * Created by manishdwibedy on 3/17/16.
 */
public class Favorites {
    private int count;
    private List<FavoriteStock> favoriteList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FavoriteStock> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<FavoriteStock> favoriteList) {
        this.favoriteList = favoriteList;
    }
}
