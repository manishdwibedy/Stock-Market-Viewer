package com.example.manishdwibedy.stockmarketviewer.util;

/**
 * Created by manishdwibedy on 3/15/16.
 */

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleGETRequests {
    private static final String TAG = "HandleGETRequests";

    private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HandleGETRequests http = new HandleGETRequests();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet("https://stock-app-csci.appspot.com/api.php?operation=lookup&name=app");


    }

    // HTTP GET request
    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        Log.d(TAG, "Sending 'GET' request to URL : " + url);
        Log.d(TAG, "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Log.d(TAG, "Response : " + response.toString());
        return response.toString();
    }
}