
package com.example.manishdwibedy.stockmarketviewer.model.stock.feednews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("label")
    @Expose
    private Integer label;

    /**
     * 
     * @return
     *     The start
     */
    public String getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The label
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(Integer label) {
        this.label = label;
    }

}
