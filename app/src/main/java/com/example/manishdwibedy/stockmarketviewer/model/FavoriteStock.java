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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteStock that = (FavoriteStock) o;

        if (Symbol != null ? !Symbol.equals(that.Symbol) : that.Symbol != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = Symbol != null ? Symbol.hashCode() : 0;
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (LastPrice != null ? LastPrice.hashCode() : 0);
        result = 31 * result + (Change != null ? Change.hashCode() : 0);
        result = 31 * result + (ChangePercent != null ? ChangePercent.hashCode() : 0);
        result = 31 * result + (MarketCap != null ? MarketCap.hashCode() : 0);
        return result;
    }
}
