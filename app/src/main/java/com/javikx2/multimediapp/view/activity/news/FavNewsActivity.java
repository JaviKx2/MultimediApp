package com.javikx2.multimediapp.view.activity.news;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.db.manager.NewsDbManager;
import com.javikx2.multimediapp.db.entity.NewsItem;
import com.javikx2.multimediapp.view.listener.FavNewsClickListener;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.javikx2.multimediapp.view.adapter.FavNewsAdapter;

import java.util.ArrayList;

import butterknife.Bind;

public class FavNewsActivity extends BaseActivity implements FavNewsClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.favnews_recycler)
    RecyclerView mNewsRecyclerView;

    private FavNewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
        setRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLatestNewsFromDB();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fav_news;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_favs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_favs) {
            deleteFavs();
            getLatestNewsFromDB();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteFavs(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_delete_all_favs)
                .setMessage(R.string.dialog_msg_delete_all_favs)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteAllFavs();
                        getLatestNewsFromDB();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void deleteAllFavs(){
        new NewsDbManager(this).deleteAll();
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar;
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setRecyclerView() {
        mNewsAdapter = new FavNewsAdapter(new ArrayList<NewsItem>(), this, this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FavNewsActivity.class);
    }

    private void getLatestNewsFromDB() {
        mNewsAdapter.updateNews(new NewsDbManager(this).getAllNews());
    }

    @Override
    public void onClickNewsItem(final long itemId) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_delete_fav)
                .setMessage(R.string.dialog_msg_delete_fav)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteNewsItemById(itemId);
                        getLatestNewsFromDB();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void deleteNewsItemById(long id){
        new NewsDbManager(this).delete(id);
    }
}
