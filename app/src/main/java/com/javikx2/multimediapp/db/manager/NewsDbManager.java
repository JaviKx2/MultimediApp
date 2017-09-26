package com.javikx2.multimediapp.db.manager;

import android.content.Context;
import android.database.Cursor;

import com.javikx2.multimediapp.db.entity.NewsItem;
import com.javikx2.multimediapp.db.helper.NewsFavoritesHelper;

import java.util.List;

public class NewsDbManager {
    NewsFavoritesHelper helper;

    public NewsDbManager(Context context) {
        helper = new NewsFavoritesHelper(context);
    }

    public long deleteAll() {
        return helper.deleteAll();
    }

    public long count() {
        return helper.count();
    }

    public List<NewsItem> getAllNews() {
        return helper.getAllNews();
    }

    public Cursor getCursor() {
        return helper.getCursor();
    }

    public long insert(String title, String text, String imgURL, String link) {
        return helper.insert(title, text, imgURL, link);
    }

    public int delete(long id) {
        return helper.delete(id);
    }
}
