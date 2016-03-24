package com.example.manishdwibedy.stockmarketviewer.model.stock.feednews;

import com.example.manishdwibedy.stockmarketviewer.model.StockDetail;

/**
 * Created by manishdwibedy on 3/24/16.
 */
public class StockNewsAdapterDetails implements Comparable {
    private String name;
    private String value;
    private int order;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        /* For Ascending order*/
        return this.getOrder()-((StockDetail)o).getOrder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
