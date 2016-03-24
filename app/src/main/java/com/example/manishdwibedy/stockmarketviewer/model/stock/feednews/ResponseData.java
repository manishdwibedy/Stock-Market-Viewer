
package com.example.manishdwibedy.stockmarketviewer.model.stock.feednews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("cursor")
    @Expose
    private Cursor cursor;

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The cursor
     */
    public Cursor getCursor() {
        return cursor;
    }

    /**
     * 
     * @param cursor
     *     The cursor
     */
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

}
