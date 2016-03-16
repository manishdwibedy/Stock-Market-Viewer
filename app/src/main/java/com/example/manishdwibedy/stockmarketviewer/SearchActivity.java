package com.example.manishdwibedy.stockmarketviewer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //handleIntent(getIntent());

        //
        final Intent queryIntent = getIntent();

        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            this.doSearchQuery(queryIntent);
        } else if (Intent.ACTION_VIEW.equals(queryAction)) {
            this.doView(queryIntent);
        } else {
            Log.d(TAG, "Create intent NOT from search");
        }
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchManager.getSearchablesInGlobalSearch();

        return true;
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
            Toast.makeText(this.getApplicationContext(), "ACTION_SEARCH : " + intent.getDataString(),
                    Toast.LENGTH_SHORT).show();


        }
    }

    //

    @Override
    public void onNewIntent(final Intent queryIntent) {
        super.onNewIntent(queryIntent);
        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            this.doSearchQuery(queryIntent);
        } else if (Intent.ACTION_VIEW.equals(queryAction)) {
            this.doView(queryIntent);
        }
    }

    private void doSearchQuery(final Intent queryIntent) {
        handleIntent(queryIntent);

        String queryString = queryIntent.getDataString(); // from suggestions
        if (queryString == null) {
            queryString = queryIntent.getStringExtra(SearchManager.QUERY); // from search-bar
        }

        // display results here
        //bundle.putString("user_query", queryString);
        queryIntent.setData(Uri.fromParts("", "", queryString));

        queryIntent.setAction(Intent.ACTION_SEARCH);
        //queryIntent.putExtras(bundle);
        //startActivity(queryIntent);
    }

    private void doView(final Intent queryIntent) {
        Uri uri = queryIntent.getData();
        String action = queryIntent.getAction();
        Intent intent = new Intent(action);
        intent.setData(uri);
        startActivity(intent);
        this.finish();
    }
}
