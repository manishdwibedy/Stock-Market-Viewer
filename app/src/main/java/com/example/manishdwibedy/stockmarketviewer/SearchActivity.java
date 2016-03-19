package com.example.manishdwibedy.stockmarketviewer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.manishdwibedy.stockmarketviewer.fragments.TabsPagerAdapter;
import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.example.manishdwibedy.stockmarketviewer.model.Stock;
import com.example.manishdwibedy.stockmarketviewer.util.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {
    // The TAG to be used for logging purposes
    private final String TAG = "SearchActivity";

    // The drawable star, which represents where the stock is a favorite or NOT!
    Drawable star = null;

    // Storing the stock object
    private Stock stock;

    // GSON to be used for serializing and deserializing objects
    private Gson gson;

    // Stock Favorite
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Adding a back button to the parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Getting the intent's action
        final Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_VIEW.equals(queryAction))
        {
            this.handleIntent(queryIntent);
        }
        else
        {
            // Go back to the previous activity
            finish();
            Log.d(TAG, "Create intent NOT from search");
        }

        // == Setting up the ViewPager ==
        setupTabs();

        // Initializing GSON
        gson = new Gson();

        // seting up favourites
        setupFavorites();
    }



    private void setupTabs() {
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());


        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_results, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchManager.getSearchablesInGlobalSearch();

        if(isFavorite)
        {
            menu.findItem(R.id.bookmark).setIcon(R.drawable.star_filled);
        }

        return true;
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {

            // Getting the stock's name
            String stockData = intent.getDataString();

            Stock stock = new Gson().fromJson(stockData, Stock.class);

            // Showing the stock name
            Toast.makeText(this.getApplicationContext(), "ACTION_SEARCH : " + stock.getName(),
                    Toast.LENGTH_SHORT).show();

            // Set the title name to reflect the stock's name
            setTitle(stock.getName());

            // Storing the stock object as a variable to be used later!
            this.stock = stock;

            isStockFavorite();
        }
    }

    @Override
    public void onNewIntent(final Intent queryIntent) {
        super.onNewIntent(queryIntent);

        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_VIEW.equals(queryAction)) {
            this.handleIntent(queryIntent);
        }
    }

    // Handle item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Setting the star drawable the first time
        if(star==null)
        {
            star = ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.star);
        }

        switch (item.getItemId()) {
            case R.id.bookmark:

                // Set the stock as a favourite
                if(item.getIcon().getConstantState().equals(star.getConstantState()))
                {
                    addToFavorites();
                    item.setIcon(R.drawable.star_filled);
                    Toast.makeText(this.getApplicationContext(), "Bookmarked " + stock.getName() + "!!",
                            Toast.LENGTH_SHORT).show();
                }

                // Removing the stock as a favourite
                else
                {
                    removeFromFavorites();
                    item.setIcon(R.drawable.star);
                    Toast.makeText(this.getApplicationContext(), "Removed "  + stock.getName() + "!!",
                            Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Setting up favorites for the first time only
    private void setupFavorites(){
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // If the favourites has not been initialized, then initialize it here
        if (preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                                                            .equals(Constant.favoritesEmpty))
        {
            Favorites favorites = new Favorites();
            favorites.setCount(0);
            favorites.setFavoriteList(new ArrayList<Stock>());

            preferences.edit().putString(Constant.favouritesKey, getGson().toJson(favorites)).apply();
        }
        else
        {
            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = getGson().fromJson(favoritesJSON, Favorites.class);

            Toast.makeText(this.getApplicationContext(), "Favorite Count : " + favorites.getCount(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    // Add the stock as a favourite and save in user preferences
    public void addToFavorites(){
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {

            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = getGson().fromJson(favoritesJSON, Favorites.class);

            // Incrementing the favourite count
            favorites.setCount(favorites.getCount() + 1);

            // Adding it to the favourite list
            favorites.getFavoriteList().add(stock);

            // Storing the updated favorites for future usage!
            preferences.edit().putString(Constant.favouritesKey, getGson().toJson(favorites)).apply();
        }
    }

    private void removeFromFavorites() {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {

            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = getGson().fromJson(favoritesJSON, Favorites.class);

            favorites.setCount(favorites.getCount() - 1);

            // Removing it from the favourite list
            favorites.getFavoriteList().remove(stock);

            // Storing the updated favorites for future usage!
            preferences.edit().putString(Constant.favouritesKey, getGson().toJson(favorites)).apply();

        }
    }

    // Check if the current stock is already marked as a favorite
    private void isStockFavorite() {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {
            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = getGson().fromJson(favoritesJSON, Favorites.class);

            // The current stock object
            if (favorites.getFavoriteList().contains(stock))
            {
                Toast.makeText(this.getApplicationContext(), "Already bookmarked!",
                        Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
            else
            {
                isFavorite = false;
            }
        }

    }

    // Would create the gson object, if needed
    private Gson getGson()
    {
        if (gson == null)
        {
            gson = new Gson();
        }
        return this.gson;
    }
}
