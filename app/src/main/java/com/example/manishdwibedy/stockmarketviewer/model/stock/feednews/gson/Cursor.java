
package com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cursor {

    @SerializedName("pages")
    @Expose
    private List<Page> pages = new ArrayList<Page>();
    @SerializedName("estimatedResultCount")
    @Expose
    private String estimatedResultCount;
    @SerializedName("currentPageIndex")
    @Expose
    private Integer currentPageIndex;
    @SerializedName("moreResultsUrl")
    @Expose
    private String moreResultsUrl;

    /**
     * 
     * @return
     *     The pages
     */
    public List<Page> getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The estimatedResultCount
     */
    public String getEstimatedResultCount() {
        return estimatedResultCount;
    }

    /**
     * 
     * @param estimatedResultCount
     *     The estimatedResultCount
     */
    public void setEstimatedResultCount(String estimatedResultCount) {
        this.estimatedResultCount = estimatedResultCount;
    }

    /**
     * 
     * @return
     *     The currentPageIndex
     */
    public Integer getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * 
     * @param currentPageIndex
     *     The currentPageIndex
     */
    public void setCurrentPageIndex(Integer currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    /**
     * 
     * @return
     *     The moreResultsUrl
     */
    public String getMoreResultsUrl() {
        return moreResultsUrl;
    }

    /**
     * 
     * @param moreResultsUrl
     *     The moreResultsUrl
     */
    public void setMoreResultsUrl(String moreResultsUrl) {
        this.moreResultsUrl = moreResultsUrl;
    }

}
