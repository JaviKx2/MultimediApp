package com.javikx2.multimediapp.view.activity.catalogue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.TvisoAPIUtils;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.Images;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.Result;
import com.javikx2.multimediapp.db.manager.CatalogueDbManager;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.javikx2.multimediapp.view.adapter.CatalogueDetailPagerAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class CatalogueDetailActivity extends BaseActivity {
    private static final String INTENT_EXTRA_ITEM_NAME = "com.javikx2.item_name";
    private static final String INTENT_EXTRA_ITEM_ONAME = "com.javikx2.item_oname";
    private static final String INTENT_EXTRA_ITEM_BACKDROP = "com.javikx2.item_backdrop";
    private static final String INTENT_EXTRA_ITEM_COUNTRY = "com.javikx2.item_country";
    private static final String INTENT_EXTRA_ITEM_PLOT = "com.javikx2.item_plot";
    private static final String INTENT_EXTRA_ITEM_IDM = "com.javikx2.item_idm";
    private static final String INTENT_EXTRA_ITEM_MEDIATYPE = "com.javikx2.item_mediatype";

    @Bind(R.id.view_pager)
    ViewPager mPager;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.app_bar)
    Toolbar mToolbar;
    @Bind(R.id.root_coordinator)
    CoordinatorLayout mCoordinator;

    private Result item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        setCollapsingToolbarLayout();
        setToolbar();
        setTabs();
    }

    private void setTabs() {
        CatalogueDetailPagerAdapter mAdapter = new CatalogueDetailPagerAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_catalogue_details;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catalogue_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_fav:
                Toast tfav = Toast.makeText(getApplication(), R.string.txt_save_into_favs, Toast.LENGTH_LONG);
                tfav.setGravity(Gravity.TOP, 0, 125);
                tfav.show();
                saveAsFavoriteIntoDB();
                break;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAsFavoriteIntoDB() {
        String imgURL = TvisoAPIUtils.getCatalogueItemImgURL(
                item.getImages().getBackdrop(),
                item.getImages().getCountry(),
                "w600");
        new CatalogueDbManager(this).insert(item.getIdm(), item.getMediaType(), item.getName(), imgURL);
    }

    private void setCollapsingToolbarLayout() {
        mCollapsingToolbarLayout.setTitle(item.getName());
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void handleIntent() {
        ImageView item_backdrop = (ImageView) findViewById(R.id.item_backdrop);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        item = new Result();
        item.setName(extras.getString(INTENT_EXTRA_ITEM_NAME));
        item.setOriginalName(extras.getString(INTENT_EXTRA_ITEM_ONAME));
        item.setImages(new Images(
                extras.getString(INTENT_EXTRA_ITEM_BACKDROP)
                , extras.getString(INTENT_EXTRA_ITEM_COUNTRY)
        ));
        item.setShortPlot(extras.getString(INTENT_EXTRA_ITEM_PLOT));
        item.setIdm(extras.getInt(INTENT_EXTRA_ITEM_IDM));
        item.setMediaType(extras.getInt(INTENT_EXTRA_ITEM_MEDIATYPE));

        String img_url = TvisoAPIUtils.getCatalogueItemImgURL(
                item.getImages().getBackdrop(),
                item.getImages().getCountry(),
                "w600");
        Context img_context = item_backdrop.getContext();

        Picasso.with(img_context)
                .load(img_url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(item_backdrop);
    }

    public Result getItem() {
        return item;
    }

    public static Intent getCallingIntent(Context context, Result item) {
        Intent intent = new Intent(context, CatalogueDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_ITEM_NAME, item.getName());
        intent.putExtra(INTENT_EXTRA_ITEM_ONAME, item.getName());
        intent.putExtra(INTENT_EXTRA_ITEM_BACKDROP, item.getImages().getBackdrop());
        intent.putExtra(INTENT_EXTRA_ITEM_COUNTRY, item.getImages().getCountry());
        intent.putExtra(INTENT_EXTRA_ITEM_PLOT, item.getShortPlot());
        intent.putExtra(INTENT_EXTRA_ITEM_IDM, item.getIdm());
        intent.putExtra(INTENT_EXTRA_ITEM_MEDIATYPE, item.getMediaType());
        return intent;
    }
}
