package com.javikx2.multimediapp.view.activity.catalogue;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.db.entity.CatalogueItem;
import com.javikx2.multimediapp.db.manager.CatalogueDbManager;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.javikx2.multimediapp.view.adapter.FavCatalogueAdapter;
import com.javikx2.multimediapp.view.listener.FavCatalogueClickListener;

import java.util.ArrayList;

import butterknife.Bind;

public class FavCatalogueActivity extends BaseActivity implements FavCatalogueClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.favcatalogue_recycler)
    RecyclerView mCatalogueRecyclerView;

    private FavCatalogueAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setRecyclerView();
    }

    private void setRecyclerView() {
        mAdapter = new FavCatalogueAdapter(new ArrayList<CatalogueItem>(), this, this);
        mCatalogueRecyclerView.setAdapter(mAdapter);
        mCatalogueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCatalogueFromDB();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fav_catalogue;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catalogue_favs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_favs) {
            deleteFavs();
            getCatalogueFromDB();
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
                        getCatalogueFromDB();
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
        new CatalogueDbManager(this).deleteAll();
    }

    private void getCatalogueFromDB(){
        mAdapter.updateItems(new CatalogueDbManager(this).getAllCatalogue());
    }

    @Override
    public void onClickCatalogueItem(final long itemId) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_delete_fav)
                .setMessage(R.string.dialog_msg_delete_fav)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteCatalogueItemById(itemId);
                        getCatalogueFromDB();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void deleteCatalogueItemById(long id){
        new CatalogueDbManager(this).delete(id);
    }


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FavCatalogueActivity.class);
    }
}
