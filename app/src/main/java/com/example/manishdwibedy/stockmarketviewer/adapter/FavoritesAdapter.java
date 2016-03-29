package com.example.manishdwibedy.stockmarketviewer.adapter;

/**
 * Created by manishdwibedy on 3/19/16.
 */

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.util.Utility;

import java.util.List;

public class FavoritesAdapter extends ArrayAdapter<FavoriteStock> {
    private Activity context;
    private List<FavoriteStock> data;

    public FavoritesAdapter(Activity context, List<FavoriteStock> data) {
        super(context, R.layout.favorites_layout, data);
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.favorites_layout, null, true);
        TextView stockSymbol = (TextView) listViewItem.findViewById(R.id.stockSymbol);
        TextView stockName = (TextView) listViewItem.findViewById(R.id.stockName);
        TextView stockData = (TextView) listViewItem.findViewById(R.id.stockPrice);
        TextView marketData = (TextView) listViewItem.findViewById(R.id.marketData);
        TextView priceChange = (TextView) listViewItem.findViewById(R.id.stockPriceChange);

        FavoriteStock stock = data.get(position);
        stockName.setText(stock.getName());
        stockSymbol.setText(stock.getSymbol());

        // Getting the stock data
        String stockPrice = "$ " + Utility.to2DecimalPlaces(stock.getLastPrice()) ;
        String marketCap = Utility.to2DecimalPlaces(stock.getMarketCap());
        String percentChange = Utility.to2DecimalPlaces(stock.getChangePercent());

        stockData.setText(stockPrice);
        priceChange.setText(percentChange + "%");
        if(Utility.isNegative(percentChange))
        {
            priceChange.setBackgroundColor(Color.RED);
        }
        else
        {
            priceChange.setBackgroundColor(Color.GREEN);
        }

        marketData.setText("Market : "+Utility.truncateNumber(marketCap));

        return  listViewItem;
    }

    private void getStockInfo(String symbol)
    {

    }

    public List<FavoriteStock> getData() {
        return data;
    }

    public void setData(List<FavoriteStock> data) {
        this.data = data;
    }
}