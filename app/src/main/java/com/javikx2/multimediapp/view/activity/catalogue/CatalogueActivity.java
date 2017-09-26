package com.javikx2.multimediapp.view.activity.catalogue;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.TvisoAPIClient;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.CatalogueResponse;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.Result;
import com.javikx2.multimediapp.db.utils.DefaultSharedPreferencesManager;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.javikx2.multimediapp.view.adapter.CataloguePagerAdapter;
import com.javikx2.multimediapp.view.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogueActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.catalogue_toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.catalogue_nav_view)
    NavigationView navigationView;
    @Bind(R.id.catalogue_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.catalogue_pager)
    ViewPager mPager;
    private String mSearchText;

    private CataloguePagerAdapter mAdapter;
    private List<Result> mItems = new ArrayList<>();
    private final static String SEARCH_KEY = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setDrawer();
        setTabs();
    }

    private void setDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setTabs() {
        mAdapter = new CataloguePagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);
        mPager.setOffscreenPageLimit(0);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_catalogue;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSearchText = new DefaultSharedPreferencesManager(this).getStringPreference(SEARCH_KEY);
        getCatalogueByTitle(mSearchText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        new DefaultSharedPreferencesManager(this).setPreference(SEARCH_KEY, mSearchText);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catalogue, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String text) {
                    return text.trim().length() % 2 == 0 && onQueryText(text);
                }

                @Override
                public boolean onQueryTextSubmit(String text) {
                    return onQueryText(text);
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    private boolean onQueryText(String text) {
        if (text.trim().length() > 1) {
            getCatalogueByTitle(text);
            mSearchText = text;
            mAdapter.notifyDataSetChanged();
        }
        return true;
    }

    private void getCatalogueByTitle(String title) {
        TvisoAPIClient.getInstance()
                .getCatalogueByTitle(title, new Callback<CatalogueResponse>() {
                    @Override
                    public void onResponse(Call<CatalogueResponse> call, Response<CatalogueResponse> response) {
                        if (response.body().getError() == 1) {
                            String error_msg = getString(R.string.get_catalogue_fail);
                            Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
                        } else {
                            mItems.clear();
                            mItems.addAll(response.body().getResults());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Call<CatalogueResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public List<Result> getItemsByMediaType(int mediaType) {
        List<Result> items = new ArrayList<Result>();
        for (Result item : mItems) {
            if (item.getMediaType() == mediaType)
                items.add(item);
        }
        return items;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                Navigator.getInstance().navigateToNews(this);
                break;
            case R.id.nav_catalogue:
                break;
            case R.id.nav_favnews:
                Navigator.getInstance().navigateToFavNews(this);
                break;
            case R.id.nav_favcatalogue:
                Navigator.getInstance().navigateToFavCatalogue(this);
                break;
            case R.id.nav_postexample:
                Navigator.getInstance().navigateToPostExample(this);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CatalogueActivity.class);
    }


}
