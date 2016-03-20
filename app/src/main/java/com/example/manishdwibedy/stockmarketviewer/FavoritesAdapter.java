package com.example.manishdwibedy.stockmarketviewer;

/**
 * Created by manishdwibedy on 3/19/16.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.example.manishdwibedy.stockmarketviewer.model.Stock;

import java.util.List;

public class FavoritesAdapter extends ArrayAdapter<Stock> {
    private Activity context;
    private List<Stock> data;

    public FavoritesAdapter(Activity context, Favorites data) {
        super(context, R.layout.favorites_layout, data.getFavoriteList());
        this.context = context;
        this.data = data.getFavoriteList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.favorites_layout, null, true);
        TextView stockSymbol = (TextView) listViewItem.findViewById(R.id.stockSymbol);
        TextView stockName = (TextView) listViewItem.findViewById(R.id.stockName);
        TextView stockData = (TextView) listViewItem.findViewById(R.id.stockData);
        TextView marketData = (TextView) listViewItem.findViewById(R.id.marketData);

        Stock stock = data.get(position);
        stockName.setText(stock.getName());
        stockSymbol.setText(stock.getSymbol());
        stockData.setText("$ 110.00");
        marketData.setText("M: 100.00 Billion");
        return  listViewItem;
    }
}