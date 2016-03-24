package com.example.manishdwibedy.stockmarketviewer.adapter;

import android.app.Activity;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.StockNewsAdapterDetails;

import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

/**
 * Created by manishdwibedy on 3/21/16.
 */
public class StockNewsFeedAdapter extends ArrayAdapter<StockNewsAdapterDetails> {
    private Activity context;
    private List<StockNewsAdapterDetails> data;
    private Linkify.MatchFilter transformFilter;
    private Pattern pattern = Pattern.compile("\\[a-zA-Z]+");

    public StockNewsFeedAdapter(Activity context, List<StockNewsAdapterDetails> data) {
        super(context, R.layout.current_stock_news_feed, data);
        this.context = context;
        this.data = data;

        // Convert the text to link
        // starting from index 0
        this.transformFilter = new Linkify.MatchFilter() {
            @Override
            public boolean acceptMatch(CharSequence cs, int start, int end) {
                return start >= 0;
            }
        };


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

        // Set the news title
        newsTitle.setText(detail.getTitle());

        // Set the news link
        String newsLink = "";

        // Adding the link to the news title
        Linkify.addLinks(newsTitle, this.pattern, newsLink, this.transformFilter, null);

        // Set the news content
        newsContent.setText(unescapeHtml4(detail.getContent()));

        // Set the new's publisher
        newsPublisher.setText(detail.getPublisher());

        // Set the new's date
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
