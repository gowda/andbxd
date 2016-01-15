package com.unbxdapi.android.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.unbxdapi.android.R;
import com.unbxdapi.android.search.models.SearchResponse;

public class SearchActivity extends AppCompatActivity {
    private static final String SITE_KEY = "";
    private static final String API_KEY = "";

    private SearchClient mClient;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mList = (RecyclerView) findViewById(R.id.list);

        mClient = SearchClient.getInstance(SITE_KEY, API_KEY);

        if (getIntent() != null) {
            //Log.i("SearchActivity", "onCreate");
            handleIntent(getIntent());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        //Log.i("SearchActivity", "onNewIntent");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String queryString = intent.getStringExtra(SearchManager.QUERY);

            Query query = new Query.Builder().setQueryString("shoes").build();
            SearchResponse response = mClient.search(query);

            ContentViewAdapter adapter = new ContentViewAdapter();
            adapter.setProducts(response.response.products);
            LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mList.setLayoutManager(llm);
            mList.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
}
