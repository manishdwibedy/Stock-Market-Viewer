package com.example.manishdwibedy.stockmarketviewer.util;

import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetFavoriteStock {
    public static List<FavoriteStock> getFavoriteStocks(List<String> symbolList){
        List<FavoriteStock> favoriteStocks = new ArrayList<FavoriteStock>();
        FavoriteStock favoriteStock = null;
        for(String symbol : symbolList)
        {
            String baseURL = "https://stock-app-csci.appspot.com/api.php?operation=getstock&symbol=";
            try {
                String response = HandleGETRequests.sendGet(baseURL + symbol);
                Gson gson = new GsonBuilder().create();
                favoriteStock = gson.fromJson(response, FavoriteStock.class);

                favoriteStocks.add(favoriteStock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return favoriteStocks;
    }
}
