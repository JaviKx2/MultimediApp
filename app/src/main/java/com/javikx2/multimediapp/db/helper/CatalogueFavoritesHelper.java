package com.javikx2.multimediapp.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.javikx2.multimediapp.db.entity.CatalogueItem;

import java.util.ArrayList;
import java.util.List;

import static com.javikx2.multimediapp.db.contract.CatalogueContract.CatalogueColumns;

public class CatalogueFavoritesHelper extends SQLiteOpenHelper {
    private final static String DB_FILE = CatalogueColumns.TABLE_NAME + ".db";
    private final static int DB_VERSION = 1;

    private final static String SQL_CREATE =
            "CREATE TABLE " + CatalogueColumns.TABLE_NAME
                    + "( " +
                    CatalogueColumns.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CatalogueColumns.COL_IDM + " INTEGER, " +
                    CatalogueColumns.COL_MEDIA_TYPE + " INTEGER, " +
                    CatalogueColumns.COL_NAME + " TEXT, " +
                    CatalogueColumns.COL_IMG_URL + " TEXT" +
                    ");";

    private final static String SQL_DROP =
            "DROP TABLE IF EXISTS " + CatalogueColumns.TABLE_NAME;

    public CatalogueFavoritesHelper(Context context) {
        super(context, DB_FILE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP);
    }

    public long count() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, CatalogueColumns.TABLE_NAME);
    }


    public List<CatalogueItem> getAllCatalogue() {
        String consultaSQL = "SELECT * FROM " + CatalogueColumns.TABLE_NAME;
        List<CatalogueItem> news = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CatalogueItem resultado = new CatalogueItem(
                        cursor.getInt(cursor.getColumnIndex(CatalogueColumns.COL_IDM)),
                        cursor.getInt(cursor.getColumnIndex(CatalogueColumns.COL_MEDIA_TYPE)),
                        cursor.getString(cursor.getColumnIndex(CatalogueColumns.COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(CatalogueColumns.COL_IMG_URL))
                );

                news.add(resultado);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return news;
    }

    public Cursor getCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(CatalogueColumns.TABLE_NAME,
                null, null, null, null, null, null);
    }

    public long insert(int idm, int mediaType, String name, String imgURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CatalogueColumns.COL_IDM, idm);
        values.put(CatalogueColumns.COL_MEDIA_TYPE, mediaType);
        values.put(CatalogueColumns.COL_NAME, name);
        values.put(CatalogueColumns.COL_IMG_URL, imgURL);

        return db.insert(CatalogueColumns.TABLE_NAME, null, values);
    }

    public int delete(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereCondition = CatalogueColumns.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return db.delete(CatalogueColumns.TABLE_NAME, whereCondition, whereArgs);
    }

    public int deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CatalogueColumns.TABLE_NAME, "1", null);
    }
}
