package com.example.manishdwibedy.stockmarketviewer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.manishdwibedy.stockmarketviewer.adapter.FavoritesAdapter;
import com.example.manishdwibedy.stockmarketviewer.asynctasks.GetFavoriteStockAsync;
import com.example.manishdwibedy.stockmarketviewer.model.FavoriteStock;
import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.example.manishdwibedy.stockmarketviewer.model.Stock;
import com.example.manishdwibedy.stockmarketviewer.util.Constant;
import com.example.manishdwibedy.stockmarketviewer.util.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.manishdwibedy.stockmarketviewer.R.id.progressBar;


public class MainActivity extends AppCompatActivity{

    private final String TAG = "MainActivity";
    SearchView searchView;
    private Gson gson;
    private ListView listView;

    // Represented whether a stock was long presses for options
    private boolean isStockLongPressed = false;

    private Stock selectedStock = null;
    //
    FavoritesAdapter favoritesAdapter;

    String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Checking branch - AutoComplete
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);

        Switch autoRefresh = (Switch) findViewById(R.id.autoRefresh);

        autoRefresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Need to set in the preference, to restore the switch's state
                if(isChecked)
                {
                    autoRefresh();
                }
                else
                {
                    cancelAutoRefresh();
                }
            }
        });


        gson = new Gson();

        setupFavorites();

        final DelayAutoCompleteTextView stockSelected = (DelayAutoCompleteTextView) findViewById(R.id.et_book_title);
        stockSelected.setThreshold(2);

        stockSelected.setAdapter(new StockAutoCompleteAdapter(this)); // 'this' is Activity instance
        stockSelected.setLoadingIndicator(
                (android.widget.ProgressBar) findViewById(R.id.pb_loading_indicator));
        stockSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Stock stock = (Stock) adapterView.getItemAtPosition(position);
                stockSelected.setText(stock.getSymbol());
                selectedStock = stock;
                stockSelected.setSelection(stock.getSymbol().length());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(true);

        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        String errorMessage = getErrorMessage();
        // Error Message is present
        if(!errorMessage.equals(Constant.errorEmpty))
        {
            Toast.makeText(this.getApplicationContext(), errorMessage,
                    Toast.LENGTH_SHORT).show();
            resetErrorMessage();
        }
    }

    private String getErrorMessage() {
        String errorMessage = Constant.errorEmpty;
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.errorStates, Context.MODE_PRIVATE);

        // If the error message has not been initialized, then initialize it here
        if (preferences.getString(Constant.errorKey, Constant.errorEmpty)
                .equals(Constant.errorEmpty))
        {
            preferences.edit().putString(Constant.errorKey, Constant.errorEmpty).apply();
        }
        else
        {
            // Storing the updated error message for future usage!
            errorMessage = preferences.getString(Constant.errorKey, Constant.errorEmpty);
        }
        return errorMessage;
    }

    private void resetErrorMessage()
    {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.errorStates, Context.MODE_PRIVATE);

        // If the error message has not been initialized, then initialize it here
        if (preferences.getString(Constant.errorKey, Constant.errorEmpty)
                .equals(Constant.errorEmpty))
        {
            preferences.edit().putString(Constant.errorKey, Constant.errorEmpty).apply();
        }
        else
        {
            // Storing the updated error message for future usage!
            preferences.edit().putString(Constant.errorKey, Constant.errorEmpty).apply();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void setFavoriteListView(SharedPreferences preferences)
    {
        // Retrieving the favorites JSON representation
        String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

        // Retrieving the favorites object
        final Favorites favorites = gson.fromJson(favoritesJSON, Favorites.class);

        listView = (ListView) findViewById(R.id.favoritesListView);

        final Activity context = this;

        final ArrayList<String> stockSymbols = new ArrayList<String>();
        for(FavoriteStock stock: favorites.getFavoriteList())
            stockSymbols.add(stock.getSymbol());

        // Doing the async task on a new thread.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            try{
                ProgressBar spinner = (ProgressBar) findViewById(progressBar);
                spinner.setVisibility(ProgressBar.VISIBLE);

                GetFavoriteStockAsync mTask = new GetFavoriteStockAsync(spinner);
                final List<FavoriteStock> favoriteStocks = mTask.execute(stockSymbols).get();


                // Setting the list view's adapter
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        synchronized (this) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    favoritesAdapter = new FavoritesAdapter(context, favoriteStocks);
                                    listView.setAdapter(favoritesAdapter);
                                }
                            });
                        }
                    };
                };
                thread.start();
            }

            catch(InterruptedException e)
            {
                Log.e(TAG, e.getMessage());
            }
            catch (ExecutionException e)
            {
                Log.e(TAG, e.getMessage());
            }
            }
        };
        new Thread(runnable).start();
    }

    // Setting up the favorites list view!
    private void setupFavorites()
    {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {

            setFavoriteListView(preferences);

            // Moving to the SearchActivity when a stock is selected from the favorite list
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!isStockLongPressed) {
                    FavoriteStock selectedStock = (FavoriteStock) listView.getItemAtPosition(i);

                    // Moving to show the selected stock
                    Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                    intent.putExtra(Constant.favoriteSelectedKey, Constant.favoriteSelectedValue);
                    intent.putExtra(Constant.stockData, new Gson().toJson(selectedStock));

                    // cancel the auto refresh
                    cancelAutoRefresh();

                    // Start the activity
                    startActivity(intent);

                    isStockLongPressed = false;
                }
                }
            });

            // On Long press,
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                               int position, long id) {

                    FavoriteStock selectedStock = (FavoriteStock) listView.getItemAtPosition(position);
                    isStockLongPressed = true;
                    showDialog(selectedStock);
                    return false;
                }
            });

        }
    }

    // This method would show the dialog allowing the user to delete the stock
    // from the favorites
    private void showDialog(final FavoriteStock selectedStock)
    {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);

        alertDialog.setMessage("Want to delete " + selectedStock.getName() + " from favorites?");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            favoritesAdapter.getData().remove(selectedStock);
            Utility.removeFromFavorites(MainActivity.this, selectedStock);
            favoritesAdapter.notifyDataSetChanged();
            }

        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cancelled deletion!
            }

        });

        alertDialog.show();
    }

    public void refreshStocks(View view)
    {
        Toast.makeText(this.getApplicationContext(), "Refreshing!",
                Toast.LENGTH_SHORT).show();

        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {

            refreshFavoriteListView(preferences);

        }

    }

    private void refreshFavoriteListView(SharedPreferences preferences)
    {
        // Retrieving the favorites JSON representation
        String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

        // Retrieving the favorites object
        final Favorites favorites = gson.fromJson(favoritesJSON, Favorites.class);

        listView = (ListView) findViewById(R.id.favoritesListView);

        final Activity context = this;

        final ArrayList<String> stockSymbols = new ArrayList<String>();
        for(FavoriteStock stock: favorites.getFavoriteList())
            stockSymbols.add(stock.getSymbol());

                final ProgressBar spinner = (ProgressBar) findViewById(progressBar);
                // Creating the progress bar
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        synchronized (this) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    spinner.setVisibility(ProgressBar.VISIBLE);
                                }
                            });
                        }
                    };
                };
                thread.start();

                // Doing the async task on a new thread.
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                    try{
                        GetFavoriteStockAsync mTask = new GetFavoriteStockAsync(spinner);
                        final List<FavoriteStock> favoriteStocks = mTask.execute(stockSymbols).get();

                        // Setting the list view's adapter
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                            synchronized (this) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                    favoritesAdapter = new FavoritesAdapter(context, favoriteStocks);
                                    listView.setAdapter(favoritesAdapter);
                                    }
                                });
                            }
                            };
                        };
                        thread.start();
                    }

                    catch(InterruptedException e)
                    {
                        Log.e(TAG, e.getMessage());
                    }
                    catch (ExecutionException e)
                    {
                        Log.e(TAG, e.getMessage());
                    }
                    }
                };
                new Thread(runnable).start();

    }

    private final static int INTERVAL = 1000 * 10; // 10 seconds
    Handler refreshHandler;
    Runnable refreshStockRunnable;

    private void autoRefresh()
    {
        if(refreshHandler == null)
        {
            refreshHandler = new Handler();
        }

        if (refreshStockRunnable == null)
        {
            refreshStockRunnable = new Runnable() {

                @Override
                public void run() {
                    try{
                        Toast.makeText(MainActivity.this, "Refreshing!",
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Refreshing the stocks");

                        SharedPreferences preferences = MainActivity.this.
                                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

                        // The favorites should have been initialized already!
                        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                                .equals(Constant.favoritesEmpty)) {

                            refreshFavoriteListView(preferences);

                        }
                        refreshHandler.postDelayed(this, INTERVAL);
                    }
                    catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            };
        }

        refreshHandler.postDelayed(refreshStockRunnable, INTERVAL);
    }

    private void cancelAutoRefresh()
    {
        Toast.makeText(MainActivity.this, "Cancelled!",
                Toast.LENGTH_SHORT).show();

        // If the refresh handler is present, try to cancel
        if(refreshHandler != null)
        {
            refreshHandler.removeCallbacks(refreshStockRunnable);
        }
    }

    public void getQuote(View view)
    {
        // Moving to show the selected stock
        Intent intent = new Intent(getBaseContext(), SearchActivity.class);
        intent.putExtra(Constant.favoriteSelectedKey, Constant.favoriteSelectedValue);
        intent.putExtra(Constant.stockData, new Gson().toJson(this.selectedStock));

        // cancel the auto refresh
        cancelAutoRefresh();

        // Start the activity
        startActivity(intent);

    }

    public void clearStock(View view)
    {
        final DelayAutoCompleteTextView stockSelected = (DelayAutoCompleteTextView) findViewById(R.id.et_book_title);
        stockSelected.setText("");

    }
}
