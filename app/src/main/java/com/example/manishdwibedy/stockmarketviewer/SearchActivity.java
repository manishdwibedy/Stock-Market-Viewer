package com.example.manishdwibedy.stockmarketviewer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.manishdwibedy.stockmarketviewer.fragments.TabsPagerAdapter;


public class SearchActivity extends AppCompatActivity {
    private final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Adding a back button to the parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Getting the intent's action
        final Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction))
        {
            this.handleIntent(queryIntent);
        }
        else
        {
            Log.d(TAG, "Create intent NOT from search");
        }

        // == Setting up the ViewPager ==
        setupTabs();
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

        return true;
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            // Getting the stock's name
            String stockName = intent.getDataString();

            // Showing the stock name
            Toast.makeText(this.getApplicationContext(), "ACTION_SEARCH : " + stockName,
                    Toast.LENGTH_SHORT).show();

            // Set the title name to reflect the stock's name
            setTitle(stockName);
        }
    }

    @Override
    public void onNewIntent(final Intent queryIntent) {
        super.onNewIntent(queryIntent);

        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            this.handleIntent(queryIntent);
        }
    }
}
