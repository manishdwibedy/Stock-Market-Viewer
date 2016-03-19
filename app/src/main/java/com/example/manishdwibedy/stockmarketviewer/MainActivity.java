package com.example.manishdwibedy.stockmarketviewer;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.manishdwibedy.stockmarketviewer.model.Favorites;
import com.example.manishdwibedy.stockmarketviewer.util.Constant;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity{

    private final String TAG = "MainActivity";
    SearchView searchView;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gson = new Gson();
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
        }
        // Check if any error message is present
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // This handler would clear all the preferences stored!
    public void resetPreferences(View view)
    {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);

        // Resetting the preference
        preferences.edit().clear().apply();
    }

    public void getFavoriteCount(View view) {
        SharedPreferences preferences = this.getApplicationContext().
                getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);
        // The favorites should have been initialized already!
        if (!preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty)
                .equals(Constant.favoritesEmpty)) {
            // Retrieving the favorites JSON representation
            String favoritesJSON = preferences.getString(Constant.favouritesKey, Constant.favoritesEmpty);

            // Retrieving the favorites object
            Favorites favorites = gson.fromJson(favoritesJSON, Favorites.class);

            int count = favorites.getCount();

            Toast.makeText(this.getApplicationContext(), "Hey!!" + count,
                    Toast.LENGTH_SHORT).show();

        }
    }

}
