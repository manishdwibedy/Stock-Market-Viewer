package com.example.manishdwibedy.stockmarketviewer.fragments;

/**
 * Created by manishdwibedy on 3/16/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
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
                textView = (TextView) view.findViewById(R.id.current_stock_label);
                textView.setText("Current Stock");
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
}