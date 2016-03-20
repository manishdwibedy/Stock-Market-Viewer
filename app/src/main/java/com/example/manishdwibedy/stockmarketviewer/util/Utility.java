package com.example.manishdwibedy.stockmarketviewer.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.example.manishdwibedy.stockmarketviewer.model.Stock;
import com.google.gson.Gson;

import java.text.DecimalFormat;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class Utility {
    /**
     * This method would convert the string value of a floating point number to
     * the string representation of a number with only two decimal places
     *
     * For ex. - 0.430000000000007 to 0.43
     * @param data - the input floating point number in string format
     * @return - te output floating point number in string format
     */
    public static String to2DecimalPlaces(String data)
    {
        Float number = 0.0f;

        try{
            number = Float.parseFloat(data);

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            return df.format(number);
        }
        catch(NumberFormatException nfe){
            return null;
        }
    }

    public static boolean removeFromFavorites(Context context, Stock stock)
    {
        SharedPreferences preferences = context.
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {

            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = new Gson().fromJson(favoritesJSON, Favorites.class);

            favorites.setCount(favorites.getCount() - 1);

            // Removing it from the favourite list
            favorites.getFavoriteList().remove(stock);

            // Storing the updated favorites for future usage!
            preferences.edit().putString(Constant.favouritesKey, new Gson().toJson(favorites)).apply();

        }
        return true;
    }
}
