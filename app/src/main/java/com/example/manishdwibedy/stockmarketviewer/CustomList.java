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

public class CustomList extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Activity context;

    public CustomList(Activity context, String[] names, String[] desc) {
        super(context, R.layout.list_layout, names);
        this.context = context;
        this.names = names;
        this.desc = desc;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);

        textViewName.setText(names[position]);
        textViewDesc.setText(desc[position]);
        return  listViewItem;
    }
}