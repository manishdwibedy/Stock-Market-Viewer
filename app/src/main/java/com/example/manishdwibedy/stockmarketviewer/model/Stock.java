package com.example.manishdwibedy.stockmarketviewer.model;

/**
 * Created by manishdwibedy on 3/15/16.
 */
public class Stock {
    private String Symbol;
    private String Name;
    private String Exchange;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExchange() {
        return Exchange;
    }

    public void setExchange(String exchange) {
        Exchange = exchange;
    }
}
