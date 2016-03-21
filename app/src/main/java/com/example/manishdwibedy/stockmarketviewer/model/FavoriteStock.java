package com.example.manishdwibedy.stockmarketviewer.model;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class FavoriteStock {
    private String Symbol;
    private String Name;
    private String LastPrice;
    private String Change;
    private String ChangePercent;
    private String MarketCap;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        this.Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLastPrice() {
        return LastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.LastPrice = lastPrice;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        this.Change = change;
    }

    public String getChangePercent() {
        return ChangePercent;
    }

    public void setChangePercent(String changePercent) {
        this.ChangePercent = changePercent;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        this.MarketCap = marketCap;
    }
}
