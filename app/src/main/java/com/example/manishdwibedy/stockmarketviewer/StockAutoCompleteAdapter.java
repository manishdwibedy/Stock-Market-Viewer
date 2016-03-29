package com.example.manishdwibedy.stockmarketviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manishdwibedy on 3/29/16.
 */
public class StockAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<Stock> resultList = new ArrayList<Stock>();

    public StockAutoCompleteAdapter(Context context) {
        mContext = context;

        // Adding a dummy stock info for testing
        List<Stock> stocks = new ArrayList<Stock>();
        Stock stock = new Stock();
        stock.setName("AAPL");
        stocks.add(stock);

        this.resultList = stocks;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Stock getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.auto_complete_dropdown, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.primaryLine)).setText(getItem(position).getName());
        ((TextView) convertView.findViewById(R.id.secondaryLine)).setText(getItem(position).getName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Stock> Stocks = findStocks(mContext, constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = Stocks;
                    filterResults.count = Stocks.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<Stock>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

    /**
     * Returns a search result for the given Stock title.
     */
    private List<Stock> findStocks(Context context, String StockTitle) {
        // GoogleStocksProtocol is a wrapper for the Google Stocks API
        //Stock protocol = new Stock(context, MAX_RESULTS);

        // Adding a dummy node for testing
        List<Stock> stocks = new ArrayList<Stock>();
        Stock stock = new Stock();
        stock.setName("AAPL");
        stocks.add(stock);

        return stocks;
    }
}