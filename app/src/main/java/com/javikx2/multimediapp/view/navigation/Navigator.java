package com.javikx2.multimediapp.view.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.javikx2.multimediapp.api.tviso.dto.latestnews.Result;
import com.javikx2.multimediapp.view.activity.PostExampleActivity;
import com.javikx2.multimediapp.view.activity.catalogue.CatalogueActivity;
import com.javikx2.multimediapp.view.activity.catalogue.CatalogueDetailActivity;
import com.javikx2.multimediapp.view.activity.catalogue.FavCatalogueActivity;
import com.javikx2.multimediapp.view.activity.news.FavNewsActivity;
import com.javikx2.multimediapp.view.activity.news.NewsActivity;
import com.javikx2.multimediapp.view.activity.news.NewsDetailActivity;

public class Navigator {
    private final static Navigator navigator = new Navigator();

    private Navigator() {
    }

    public static Navigator getInstance() {
        return navigator;
    }

    public void navigateToNews(Context context) {
        if (context != null) {
            Intent intentToLaunch = NewsActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToCatalogue(Context context) {
        if (context != null) {
            Intent intentToLaunch = CatalogueActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToFavNews(Context context) {
        if (context != null) {
            Intent intentToLaunch = FavNewsActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToPostExample(Context context) {
        if (context != null) {
            Intent intentToLaunch = PostExampleActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToFavCatalogue(Context context) {
        if (context != null) {
            Intent intentToLaunch = FavCatalogueActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToNewsDetail(Context context, Result newsItem) {
        if (context != null) {
            Intent intentToLaunch = NewsDetailActivity.getCallingIntent(context, newsItem);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToCatalogueDetail(FragmentActivity context
            , com.javikx2.multimediapp.api.tviso.dto.catalogue.Result catalogueItem) {
        if (context != null) {
            Intent intentToLaunch = CatalogueDetailActivity.getCallingIntent(context, catalogueItem);
            context.startActivity(intentToLaunch);
        }
    }
}
