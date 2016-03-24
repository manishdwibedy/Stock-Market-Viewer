package com.example.manishdwibedy.stockmarketviewer.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.StockNewsAdapterDetails;

import java.util.List;

/**
 * Created by manishdwibedy on 3/21/16.
 */
public class StockNewsFeedAdapter extends ArrayAdapter<StockNewsAdapterDetails> {
    private Activity context;
    private List<StockNewsAdapterDetails> data;

    public StockNewsFeedAdapter(Activity context, List<StockNewsAdapterDetails> data) {
        super(context, R.layout.current_stock_news_feed, data);
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.current_stock_news_feed, null, true);

        TextView newsTitle = (TextView) listViewItem.findViewById(R.id.stockNewsTitle);
        TextView newsContent = (TextView) listViewItem.findViewById(R.id.stockNewsContent);
        TextView newsPublisher = (TextView) listViewItem.findViewById(R.id.stockNewsPublisher);
        TextView newsDate = (TextView) listViewItem.findViewById(R.id.stockNewsDate);


        StockNewsAdapterDetails detail = data.get(position);
        newsTitle.setText(detail.getTitle());
        newsContent.setText(detail.getContent());
        newsPublisher.setText(detail.getPublisher());
        newsDate.setText(detail.getPublishedDate());

        return listViewItem;
    }

    private void getStockInfo(String symbol)
    {

    }

    public List<StockNewsAdapterDetails> getData() {
        return data;
    }

    public void setData(List<StockNewsAdapterDetails> data) {
        this.data = data;
    }
}
