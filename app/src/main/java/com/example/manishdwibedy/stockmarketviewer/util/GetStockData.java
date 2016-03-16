package com.example.manishdwibedy.stockmarketviewer.util;

import com.example.manishdwibedy.stockmarketviewer.model.Stock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by manishdwibedy on 3/15/16.
 */
public class GetStockData {
    public static Stock[] getStocks(String query){
        //List<Stock> stocks = new ArrayList<Stock>();
        Stock[] stocks = null;
        String baseURL = "https://stock-app-csci.appspot.com/api.php?operation=lookup&name=";
        try {
            String response = HandleGETRequests.sendGet(baseURL + query);
            Gson gson = new GsonBuilder().create();
            stocks = gson.fromJson(response, Stock[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }
}
