package com.example.manishdwibedy.stockmarketviewer.util;

import com.example.manishdwibedy.stockmarketviewer.model.StockData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetStockData {
    public static StockData getStockData(String symbol){
        //List<Stock> stocks = new ArrayList<Stock>();
        StockData stockData = null;
        String baseURL = "https://stock-app-csci.appspot.com/api.php?operation=getstock&symbol=";
        try {
            String response = HandleGETRequests.sendGet(baseURL + symbol);
            Gson gson = new GsonBuilder().create();
            stockData = gson.fromJson(response, StockData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockData;
    }

    public static void main(String[] args){
        StockData data = getStockData("AAPL");
        System.out.print(Utility.to2DecimalPlaces(data.getChange()));

        int x =0;
    }
}
