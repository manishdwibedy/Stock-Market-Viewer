package com.example.manishdwibedy.stockmarketviewer.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.util.GetFavoriteStock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetFavoriteStockAsync extends AsyncTask<ArrayList<String>, Void, List<FavoriteStock>> {

    private final ProgressBar progressBar;

    private String TAG = "GetFavoriteStockAsync-Async";
    private Exception exception;

    public GetFavoriteStockAsync(ProgressBar progressBar)
    {
        this.progressBar =progressBar;
    }

    @Override
    protected List<FavoriteStock> doInBackground(ArrayList<String>... params) {

        ArrayList<String> stockymbols = params[0];

        for(String param : stockymbols)
        {
            Log.d(TAG, "stock symbol is " + param);
        }

        List<FavoriteStock> favoriteStocks = GetFavoriteStock.getFavoriteStocks(stockymbols);

        return favoriteStocks;
    }

    @Override
    protected void onPostExecute(List<FavoriteStock> result) {
        Log.d(TAG, "Done");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "Starting");
        //progressBar.setVisibility(View.VISIBLE);
    }
}