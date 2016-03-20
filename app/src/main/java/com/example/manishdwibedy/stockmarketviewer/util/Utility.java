package com.example.manishdwibedy.stockmarketviewer.util;

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
}
