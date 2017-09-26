package com.javikx2.multimediapp.view.activity.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.TvisoAPIClient;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.LatestNewsResponse;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.Result;
import com.javikx2.multimediapp.api.tviso.exception.TvisoApiClientException;
import com.javikx2.multimediapp.view.navigation.Navigator;
import com.javikx2.multimediapp.view.listener.NewsClickListener;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.javikx2.multimediapp.view.adapter.NewsAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NewsClickListener, SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.news_toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.news_nav_view)
    NavigationView navigationView;
    @Bind(R.id.news_recycler)
    RecyclerView mNewsRecyclerView;
    @Bind(R.id.news_swiperefresh)
    SwipeRefreshLayout mNewsSwipeRefreshLayout;

    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setDrawer();
        setRecyclerView();
        setSwipeRefreshLayout();
        getLatestNewsFromAPI();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news;
    }

    private void setRecyclerView(){
        mNewsAdapter = new NewsAdapter(new ArrayList<Result>(), this, this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setSwipeRefreshLayout(){
        mNewsSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.news_swiperefresh);
        mNewsSwipeRefreshLayout.setColorSchemeResources(
                R.color.s1,
                R.color.s2,
                R.color.s3,
                R.color.s4
        );
        mNewsSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getLatestNewsFromAPI(){
        mNewsSwipeRefreshLayout.setRefreshing(true);
        try {
            TvisoAPIClient.getInstance().getLatestNews(new Callback<LatestNewsResponse>() {
                @Override
                public void onResponse(Call<LatestNewsResponse> call, Response<LatestNewsResponse> response) {
                    LatestNewsResponse latestNews = response.body();
                    if (latestNews.getError() == 1){
                        String error_msg = getString(R.string.get_news_fail);
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
                    }else {
                        mNewsAdapter.updateNews(response.body().getResults());
                    }
                    mNewsSwipeRefreshLayout.setRefreshing(false);
                }
                @Override
                public void onFailure(Call<LatestNewsResponse> call, Throwable t) {
                    Toast.makeText(NewsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (TvisoApiClientException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_news:
                break;
            case R.id.nav_catalogue:
                Navigator.getInstance().navigateToCatalogue(this);
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
        return new Intent(context, NewsActivity.class);
    }

    @Override
    public void onClickNewsItem(Result newsItem) {
        Navigator navigator = Navigator.getInstance();
        navigator.navigateToNewsDetail(this, newsItem);
    }

    @Override
    public void onRefresh() {
        getLatestNewsFromAPI();
    }
}
