package com.example.manishdwibedy.stockmarketviewer.fragments;

/**
 * Created by manishdwibedy on 3/16/16.
 */

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.adapter.StockDetailsAdapter;
import com.example.manishdwibedy.stockmarketviewer.asynctasks.GetStockDataAsync;
import com.example.manishdwibedy.stockmarketviewer.asynctasks.ImageLoadTask;
import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.model.StockData;
import com.example.manishdwibedy.stockmarketviewer.model.StockDetail;
import com.example.manishdwibedy.stockmarketviewer.util.Utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.manishdwibedy.stockmarketviewer.R.id.progressBar;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String TAG = "PageFragment";

    private static FavoriteStock stock;

    // Need to define the order of the list items
    private String[] listOrder = new String[]{"Name", "Symbol", "LastPrice", "Change", "Timestamp", "MarketCap", "Volume", "ChangeYTD", "High", "Low", "Open"};

    // Values that need to be truncated
    private String[] truncationNeeded = new String[]{"Volume", "MarketCap"};

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

    private void getStockData(final String symbol, final View view) {

        final ListView listView = (ListView) view.findViewById(R.id.stockDetails);

        // Doing the async task on a new thread.
        // Getting the stock data and loading the listview
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    final ProgressBar spinner = (ProgressBar) view.findViewById(progressBar);
                    spinner.setVisibility(ProgressBar.VISIBLE);

                    GetStockDataAsync mTask = new GetStockDataAsync(null);
                    final StockData stockData = mTask.execute(symbol).get();

                    final List<StockDetail> list = new ArrayList<StockDetail>();

                    // Defines the order of the list items
                    List<String> order = Arrays.asList(listOrder);

                    // Need truncation
                    List<String> trunctionNeeded = Arrays.asList(truncationNeeded);

                    Class objClass = stockData.getClass();
                    Method[] methods = objClass.getMethods();
                    for (Method method : methods) {
                        // Only consider the get methods
                        if (method.getName().startsWith("get")) {
                            StockDetail stockDetail = new StockDetail();
                            String property = method.getName().substring(3);

                            if (!property.equalsIgnoreCase("Class")) {
                                String propertyValue;
                                if (trunctionNeeded.contains(property)) {
                                    propertyValue = (String) method.invoke(stockData);
                                    propertyValue = Utility.truncateNumber(propertyValue);
                                } else {
                                    propertyValue = (String) method.invoke(stockData);
                                }

                                stockDetail.setName(property.toUpperCase());
                                stockDetail.setValue(Utility.to2DecimalPlaces(propertyValue));
                                stockDetail.setOrder(order.indexOf(property));
                                // Set the Change Percent
                                if (property.equalsIgnoreCase("Change")) {
//                                    String type = property.substring(property.length() - 3);
//                                    String methodName = "get" + property + "Percent";
//                                    Method m = objClass.getMethod(methodName, StockData.class);

//                                    stockDetail.setValue(stockDetail.getValue() + "(" + m.invoke(stockData) + ")");
                                    String value = Utility.to2DecimalPlaces(stockData.getChangePercent());
                                    stockDetail.setValue(stockDetail.getValue() + "(" + value + ")");
                                }
                                // Set the Change Percent YTD
                                else if (property.equalsIgnoreCase("ChangeYTD")) {
//                                    String type = property.substring(property.length() - 3);
//                                    String methodName = "get" + property + "Percent";
//                                    Method m = objClass.getMethod(methodName, StockData.class);
//
//                                    stockDetail.setValue(stockDetail.getValue() + "(" + m.invoke(stockData) + ")");
                                    String value = Utility.to2DecimalPlaces(stockData.getChangePercentYTD());
                                    stockDetail.setValue(stockDetail.getValue() + "(" + value + ")");
                                } else if (property.equalsIgnoreCase("Timestamp")) {
                                    String timestamp = Utility.parseDateTime(propertyValue);
                                    if (timestamp != null) {
                                        stockDetail.setValue(timestamp);
                                    }
                                }
                                if (stockDetail.getOrder() >= 0) {
                                    list.add(stockDetail);
                                }
                            }

                        }
                        System.out.println("Public method found: " + method.toString());
                    }

                    Collections.sort(list);

                    // Setting the list view's adapter
                    // Need to do it on UI Thread
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            synchronized (this) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StockDetailsAdapter favoritesAdapter = new StockDetailsAdapter(getActivity(), list);
                                        listView.setAdapter(favoritesAdapter);

                                        Utility.getListViewSize(listView);

                                        spinner.setVisibility(ProgressBar.GONE);
                                    }
                                });
                            }
                        }

                        ;
                    };
                    thread.start();
                }
//          catch(NoSuchMethodException e)
//          {
//              Log.e(TAG, e.getMessage());
//          }
                catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                } catch (ExecutionException e) {
                    Log.e(TAG, e.getMessage());
                } catch (InvocationTargetException e) {
                    Log.e(TAG, e.getMessage());
                } catch (IllegalAccessException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        };
        new Thread(runnable).start();

        Runnable loadImage = new Runnable() {
            @Override
            public void run() {
                try {
                    Display display = getActivity().getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;

                    ImageView imageView = (ImageView) view.findViewById(R.id.stockImage);
                    String URL = "http://chart.finance.yahoo.com/t?s=" +
                            stock.getSymbol() +
                            "&amp;lang=en-US&amp;amp;width=" +
                            Utility.convertDpToPixel((float)width, getActivity()) +
                            "&amp;height=" +
                            Utility.convertDpToPixel(150f, getActivity());
                    Bitmap bitmap = new ImageLoadTask(URL, imageView).execute().get();
                    Log.d(TAG, bitmap.toString());

                } catch (Exception e) {

                }
            }
        };
        new Thread(loadImage).start();
    }
}