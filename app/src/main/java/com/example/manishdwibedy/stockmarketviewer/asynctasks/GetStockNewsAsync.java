package com.example.manishdwibedy.stockmarketviewer.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.gson.StockNews;
import com.example.manishdwibedy.stockmarketviewer.util.GetStockNews;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetStockNewsAsync extends AsyncTask<String, Void, StockNews> {

    private final ProgressBar progressBar;

    private String TAG = "GetStockNews-Async";

    public GetStockNewsAsync(ProgressBar progressBar)
    {
        this.progressBar = progressBar;
    }

    @Override
    protected StockNews doInBackground(String... params) {

        String symbol = params[0];

        StockNews stockData = GetStockNews.getStockNewsFeed(symbol);

        return stockData;
    }

    @Override
    protected void onPostExecute(StockNews result) {
        Log.d(TAG, "Done");
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "Starting");
    }
}