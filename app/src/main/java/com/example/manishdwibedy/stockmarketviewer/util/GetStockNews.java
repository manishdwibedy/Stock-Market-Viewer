package com.example.manishdwibedy.stockmarketviewer.util;

import com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.StockNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetStockNews {
    public static StockNews getStockData(String symbol){

        StockNews stockData = null;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String baseURL = "https://stock-app-csci.appspot.com/api.php?operation=news&symbol=";

        String response;
        try
        {
            response = HandleGETRequests.sendGet(baseURL + symbol);

            stockData = gson.fromJson(response, StockNews.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stockData;
    }

    public static void main(String[] args){

        while(true)
        {
            StockNews data = getStockData("AAPL");
            if(data.getResponseData() != null)
            {
                int x =0;
                System.out.print(data.getResponseData());
                break;
            }
            else
            {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }


    }
}
