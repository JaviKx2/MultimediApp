package com.javikx2.multimediapp.db.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import static com.javikx2.multimediapp.db.contract.NewsContract.NewsColumns;
import com.javikx2.multimediapp.db.helper.NewsFavoritesHelper;

public class NewsProvider extends ContentProvider{
    private static final String AUTHORITY =
            NewsProvider.class.getPackage().getName() + ".provider";
    private static final String ENTITY = "news";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTITY;
    private static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int ID_URI_RESULTADOS      = 1;
    private static final int ID_URI_RESULTADO_UNICO = 2;
    private static final UriMatcher uriMatcher;

    // Inicializamos el UriMatcher con las rutas disponibles
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTITY, ID_URI_RESULTADOS);
        uriMatcher.addURI(AUTHORITY, ENTITY + "/#", ID_URI_RESULTADO_UNICO);
    }

    // Base de datos
    private NewsFavoritesHelper newsFavoritesHelper;

    @Override
    public boolean onCreate() {
        newsFavoritesHelper = new NewsFavoritesHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_RESULTADO_UNICO) {
            where = "_id = " + uri.getLastPathSegment();
        }

        SQLiteDatabase db = newsFavoritesHelper.getReadableDatabase();

        return db.query(
                NewsColumns.TABLE_NAME,
                projection,
                where,
                selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_RESULTADO_UNICO) {
            where = "_id = " + uri.getLastPathSegment();
        }

        SQLiteDatabase db = newsFavoritesHelper.getWritableDatabase();

        return db.update(
                NewsColumns.TABLE_NAME,
                values,
                where,
                selectionArgs
        );
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_RESULTADO_UNICO) {
            where = "_id = " + uri.getLastPathSegment();
        }
        SQLiteDatabase db = newsFavoritesHelper.getWritableDatabase();
        return db.delete(
                NewsColumns.TABLE_NAME,
                where,
                selectionArgs
        );
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long regId;

        SQLiteDatabase db = newsFavoritesHelper.getWritableDatabase();
        regId = db.insert(
                NewsColumns.TABLE_NAME,
                null,
                values
        );

        return ContentUris.withAppendedId(CONTENT_URI, regId);
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case ID_URI_RESULTADOS:
                return "vnd.android.cursor.dir/vnd.multimediapp." + ENTITY;
            case ID_URI_RESULTADO_UNICO:
                return "vnd.android.cursor.item/vnd.multimediapp." + ENTITY;
            default:
                return null;
        }
    }
}
