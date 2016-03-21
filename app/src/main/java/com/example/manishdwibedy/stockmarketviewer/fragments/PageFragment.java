package com.example.manishdwibedy.stockmarketviewer.fragments;

/**
 * Created by manishdwibedy on 3/16/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.adapter.StockDetailsAdapter;
import com.example.manishdwibedy.stockmarketviewer.asynctasks.GetStockDataAsync;
import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.model.StockData;
import com.example.manishdwibedy.stockmarketviewer.model.StockDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String TAG = "PageFragment";

    private static FavoriteStock stock;
    public PageFragment() {
    }

    public static PageFragment newInstance(int page, FavoriteStock stockData) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        PageFragment.stock = stockData;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int page = getArguments().getInt(ARG_PAGE_NUMBER, -1);
        View view = null;
        TextView textView;
        switch (page){
            case 1:
                view = inflater.inflate(R.layout.fragment_current_stock, container, false);
                getStockData(PageFragment.stock.getSymbol(), view);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_historical_chart, container, false);
                textView = (TextView) view.findViewById(R.id.historical_chart_label);
                textView.setText("Historical Charts");
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_news_feed, container, false);
                textView = (TextView) view.findViewById(R.id.news_feed_label);
                textView.setText("News Feed");
                break;

        }
        return view;
    }

    private void getStockData(final String symbol, View view) {

        final ListView listView = (ListView) view.findViewById(R.id.stockDetails);
        // Doing the async task on a new thread.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    GetStockDataAsync mTask = new GetStockDataAsync(null);
                    final StockData stockData = mTask.execute(symbol).get();

                    final List<StockDetail> list = new ArrayList<StockDetail>();
                    StockDetail stockDetail = new StockDetail();
                    stockDetail.setName("name");
                    stockDetail.setValue("value");
                    list.add(stockDetail);

                    // Setting the list view's adapter
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            synchronized (this) {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StockDetailsAdapter favoritesAdapter = new StockDetailsAdapter(getActivity(), list);
                                        listView.setAdapter(favoritesAdapter);
                                    }
                                });
                            }
                        };
                    };
                    thread.start();
                }

                catch(InterruptedException e)
                {
                    Log.e(TAG, e.getMessage());
                }
                catch (ExecutionException e)
                {
                    Log.e(TAG, e.getMessage());
                }
            }
        };
        new Thread(runnable).start();
    }
}