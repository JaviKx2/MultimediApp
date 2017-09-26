package com.javikx2.multimediapp.db.manager;

import android.content.Context;
import android.database.Cursor;

import com.javikx2.multimediapp.db.entity.CatalogueItem;
import com.javikx2.multimediapp.db.helper.CatalogueFavoritesHelper;
import com.javikx2.multimediapp.db.helper.NewsFavoritesHelper;

import java.util.List;

public class CatalogueDbManager {
    CatalogueFavoritesHelper helper;

    public CatalogueDbManager(Context context) {
        helper = new CatalogueFavoritesHelper(context);
    }

    public long deleteAll() {
        return helper.deleteAll();
    }

    public long count() {
        return helper.count();
    }

    public List<CatalogueItem> getAllCatalogue() {
        return helper.getAllCatalogue();
    }

    public Cursor getCursor() {
        return helper.getCursor();
    }

    public long insert(int idm, int mediaType, String name, String imgURL) {
        return helper.insert(idm, mediaType, name, imgURL);
    }

    public int delete(long id) {
        return helper.delete(id);
    }
}
