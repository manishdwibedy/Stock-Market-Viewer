package com.example.manishdwibedy.stockmarketviewer.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        StockDetail detail = data.get(position);
        nameTextView.setText(detail.getName());
        valueTextView.setText(detail.getValue());

        if(detail.getName().equals("CHANGE") || detail.getName().equals("CHANGEYTD"))
        {
            LinearLayout stockValueLayout = (LinearLayout)listViewItem.findViewById(R.id.stockValueLayout);

            ImageView changeImage = new ImageView(context);

            if(detail.getValue().contains("-"))
            {
                changeImage.setImageResource(R.drawable.down);
            }
            else
            {
                changeImage.setImageResource(R.drawable.up);
            }

            changeImage.setForegroundGravity(Gravity.LEFT);
            changeImage.setMaxWidth(20);
            changeImage.setMaxHeight(20);
            stockValueLayout.addView(changeImage);
            //stockValueLayout.setMinimumHeight(20);
        }
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
