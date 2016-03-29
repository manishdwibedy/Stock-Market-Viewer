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
import com.example.manishdwibedy.stockmarketviewer.util.GetStock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manishdwibedy on 3/29/16.
 */
public class StockAutoCompleteAdapter extends BaseAdapter implements Filterable {
    private static final String TAG = "StockAutoCompleteAdapter";
    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<Stock> resultList = new ArrayList<Stock>();
    private String previousConstraint = null;

    public StockAutoCompleteAdapter(Context context) {
        mContext = context;
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

        Stock stock = getItem(position);
        ((TextView) convertView.findViewById(R.id.primaryLine)).setText(stock.getSymbol());
        ((TextView) convertView.findViewById(R.id.secondaryLine)).setText(
                            stock.getName() + " (" + stock.getExchange() + ")");
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                // Cancelling the filter if the constraint has not changed.
                if ( constraint.toString().equalsIgnoreCase(previousConstraint))
                {
                    constraint = null;
                }

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
                    previousConstraint = constraint.toString();
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
    private List<Stock> findStocks(Context context, final String stockTitle) {
        // GoogleStocksProtocol is a wrapper for the Google Stocks API
        //Stock protocol = new Stock(context, MAX_RESULTS);

        final List<Stock> list = new ArrayList<Stock>();
        list.addAll(GetStock.getStocksList(stockTitle));
        // Doing the async task on a new thread.
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//        new Thread(runnable).start();



        // Adding a dummy node for testing
//        List<Stock> stocks = new ArrayList<Stock>();
//        Stock stock = new Stock();
//        stock.setName("AAPL");
//        stocks.add(stock);
        this.resultList = list;
        return list;
    }
}