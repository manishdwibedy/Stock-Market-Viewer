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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (Symbol != null ? !Symbol.equals(stock.Symbol) : stock.Symbol != null) return false;
        if (Name != null ? !Name.equals(stock.Name) : stock.Name != null) return false;
        return !(Exchange != null ? !Exchange.equals(stock.Exchange) : stock.Exchange != null);

    }

    @Override
    public int hashCode() {
        int result = Symbol != null ? Symbol.hashCode() : 0;
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Exchange != null ? Exchange.hashCode() : 0);
        return result;
    }
}
