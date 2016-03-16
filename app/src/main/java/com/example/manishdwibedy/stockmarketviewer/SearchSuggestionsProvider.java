package com.example.manishdwibedy.stockmarketviewer;

/**
 * Created by manishdwibedy on 3/15/16.
 */

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.example.manishdwibedy.stockmarketviewer.model.Stock;
import com.example.manishdwibedy.stockmarketviewer.util.GetStockData;

public class SearchSuggestionsProvider extends SearchRecentSuggestionsProvider {
    static final String TAG = SearchSuggestionsProvider.class.getSimpleName();
    public static final String AUTHORITY = SearchSuggestionsProvider.class
            .getName();
    public static final int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;
    private static final String[] COLUMNS = {
            "_id", // must include this column
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA,
            SearchManager.SUGGEST_COLUMN_INTENT_ACTION,
            SearchManager.SUGGEST_COLUMN_SHORTCUT_ID };

    public SearchSuggestionsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        String query = selectionArgs[0];
        if (query == null || query.length() < 3) {
            return null;
        }

        MatrixCursor cursor = new MatrixCursor(COLUMNS);

        Stock[] dataList = GetStockData.getStocks(query);
        try {
            int n = 0;
            for (Stock data : dataList) {
                // If the Stock name matches
                if(data.getName().toLowerCase().contains(query.toLowerCase()))
                {
                    cursor.addRow(createRow(new Integer(n++), data.getName(), null));
                }
                else if(data.getSymbol().toLowerCase().contains(query.toLowerCase()))
                {
                    cursor.addRow(createRow(new Integer(n++), data.getName(), null));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to lookup " + query, e);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    private Object[] createRow(Integer id, String text1, //String text2,
                               String name) {
        return new Object[] { id, // _id
                text1, // text1
                //text2, // text2
                text1,
                "android.intent.action.SEARCH", // action
                SearchManager.SUGGEST_NEVER_MAKE_SHORTCUT };
    }

}
