package com.example.manishdwibedy.stockmarketviewer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
     * @return - the output floating point number in string format
     */
    public static String to2DecimalPlaces(String data)
    {
        Float number = 0.0f;

        try{
            number = Float.parseFloat(data);

            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(2);
            return df.format(number);
        }
        catch(NumberFormatException nfe){
            return data;
        }
    }

    public static boolean removeFromFavorites(Context context, FavoriteStock stock)
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

    public static String truncateNumber(String input) {

        float floatNumber;

        try{
            if(input.contains((CharSequence) ","))
            {
                input = input.replaceAll(",","");
            }
            floatNumber = Float.parseFloat(input);
        }
        catch (NumberFormatException nfe)
        {
            return null;
        }

        return truncateNumber(floatNumber);
    }

    public static String truncateNumber(double floatNumber) {
        long million = 1000000L;
        long billion = 1000000000L;
        long trillion = 1000000000000L;

        long number = Math.round(floatNumber);

        if ((number >= million) && (number < billion)) {
            float fraction = calculateFraction(number, million);
            return to2DecimalPlaces(Float.toString(fraction)) + " M";
        } else if ((number >= billion) && (number < trillion)) {
            float fraction = calculateFraction(number, billion);
            return to2DecimalPlaces(Float.toString(fraction)) + " B";
        }
        return Long.toString(number);
    }

    private static float calculateFraction(long number, long divisor) {
        long truncate = (number * 10L + (divisor / 2L)) / divisor;
        float fraction = (float) truncate * 0.10F;
        return fraction;
    }

    /**
     * Would parse the date into the needed format.
     * Else, would return null incase of ParseException
     * @param input - the input date in String format
     * @return the date in the required format
     */
    public static String parseDateTime(String input)
    {
        input = input.replaceAll("UTC", "GMT");

        DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss Z yyyy", Locale.ENGLISH);
        Format outputFormat = new SimpleDateFormat("dd MMMM yyyy, kk:mm:ss", Locale.ENGLISH);
        try {
            Date inputDate = inputFormat.parse(input);
            return outputFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Setting the listview height dynamically
     * @param listView
     */
    public static void getListViewSize(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < listAdapter.getCount(); size++) {
            View listItem = listAdapter.getView(size, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method re-formats the date time stamp of the news article
     * Converts the date in form dd MMMM yyyy, hh:mm:ss
     * @param input The date time in String form
     * @return The datetime in the needed form
     */
    public static String parseNewsDateTime(String input)
    {
        DateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
        Format outputFormat = new SimpleDateFormat("dd MMMM yyyy, kk:mm:ss", Locale.ENGLISH);
        try {
            Date inputDate = inputFormat.parse(input);
            return outputFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static boolean isNegative(String input)
    {
        try{
            Float number = Float.parseFloat(input);
            if (number < 0)
            {
                return true;
            }
            return false;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }

    }

    public static void main(String[] args)
    {
        String input = "1.12";

        System.out.print(isNegative(input));
    }
}
