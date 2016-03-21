package com.example.manishdwibedy.stockmarketviewer.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.manishdwibedy.stockmarketviewer.model.StockData;
import com.example.manishdwibedy.stockmarketviewer.util.GetStockData;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetStockDataAsync extends AsyncTask<String, Void, StockData> {

    private final ProgressBar progressBar;

    private String TAG = "GetStockData-Async";

    public GetStockDataAsync(ProgressBar progressBar)
    {
        this.progressBar = progressBar;
    }

    @Override
    protected StockData doInBackground(String... params) {

        String symbol = params[0];

        StockData stockData = GetStockData.getStockData(symbol);

        return stockData;
    }

    @Override
    protected void onPostExecute(StockData result) {
        Log.d(TAG, "Done");
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "Starting");
    }
}