package com.example.manishdwibedy.stockmarketviewer.model.stock.feednews;

import com.example.manishdwibedy.stockmarketviewer.model.StockDetail;

/**
 * Created by manishdwibedy on 3/24/16.
 */
public class StockNewsAdapterDetails implements Comparable {
    private String title;
    private String content;
    private String publisher;
    private String publishedDate;
    private int order;

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public int compareTo(Object o) {
        /* For Ascending order*/
        return this.getOrder()-((StockDetail)o).getOrder();
    }

}
