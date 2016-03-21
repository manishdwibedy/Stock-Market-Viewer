package com.example.manishdwibedy.stockmarketviewer.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.manishdwibedy.stockmarketviewer.R;
import com.example.manishdwibedy.stockmarketviewer.model.StockDetail;

import java.util.List;

/**
 * Created by manishdwibedy on 3/21/16.
 */
public class StockDetailsAdapter extends ArrayAdapter<StockDetail> {
    private Activity context;
    private List<StockDetail> data;

    public StockDetailsAdapter(Activity context, List<StockDetail> data) {
        super(context, R.layout.current_stock_details, data);
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.current_stock_details, null, true);

        TextView nameTextView = (TextView) listViewItem.findViewById(R.id.details_name);
        TextView valueTextView = (TextView) listViewItem.findViewById(R.id.details_value);

        StockDetail stock = data.get(position);
        nameTextView.setText("Name");
        valueTextView.setText("Value");

        return  listViewItem;
    }

    private void getStockInfo(String symbol)
    {

    }

    public List<StockDetail> getData() {
        return data;
    }

    public void setData(List<StockDetail> data) {
        this.data = data;
    }
}
